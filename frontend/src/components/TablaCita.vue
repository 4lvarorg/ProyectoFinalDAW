<template>
  <div class="tabla-cita">
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Usuario</th>
        <th>Psicólogo</th>
        <th>Fecha Reservada</th>
        <th>Hora Reservada</th>
        <th>Precio Final</th>
        <th>Acciones</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="cita in citas" :key="cita.id">
        <td>{{ cita.id }}</td>
        <td>{{ cita.usuario.nombre }}</td>
        <td>{{ cita.psicologo.nombre }}</td>
        <td>{{ cita.fechaReservada }}</td>
        <td>{{ cita.horaReservada }}</td>
        <td>{{ cita.precioFinal }}</td>
        <td>
          <button @click="actualizarCita(cita)">Actualizar</button>
          <button @click="eliminarCita(cita.id)">Eliminar</button>
        </td>
      </tr>
      </tbody>
    </table>
    <div>
      <h2>Insertar/Actualizar Cita</h2>
      <input v-model="cita.id" placeholder="ID">
      <select v-model="cita.usuario.id">
        <option v-for="usuario in usuarios" :key="usuario.id" :value="usuario.id">
          {{ usuario.nombre }}
        </option>
      </select>
      <select v-model="cita.psicologo.id">
        <option v-for="psicologo in psicologos" :key="psicologo.id" :value="psicologo.id">
          {{ psicologo.nombre }}
        </option>
      </select>
      <input v-model="cita.fechaReservada" placeholder="Fecha Reservada">
      <input v-model="cita.horaReservada" placeholder="Hora Reservada">
      <input v-model="cita.precioFinal" placeholder="Precio Final">
      <button @click="guardarCita()">Guardar</button>
    </div>
  </div>
</template>

<script>
import CitaService from '../services/CitaService.js';
import UsuarioService from '../services/UsuarioService.js';
import PsicologoService from '../services/PsicologoService.js';

export default {
  name: 'TablaCita',
  data() {
    return {
      citas: [],
      usuarios: [],
      psicologos: [],
      cita: {
        id: '',
        usuario: {
          id: '',
          nombre: ''
        },
        psicologo: {
          id: '',
          nombre: ''
        },
        fechaReservada: '',
        horaReservada: '',
        precioFinal: ''
      }
    };
  },
  created() {
    CitaService.obtenerTodasLasCitas().then(response => {
      this.citas = response.data;
    });
    UsuarioService.obtenerTodosLosUsuarios().then(response => {
      this.usuarios = response.data;
    });
    PsicologoService.obtenerTodosLosPsicologos().then(response => {
      this.psicologos = response.data;
    });
  },
  methods: {
    actualizarCita(cita) {
      this.cita = Object.assign({}, cita);
    },
    guardarCita() {
      if (this.cita.id) {
        CitaService.actualizarCita(this.cita).then(response => {
          this.citas = this.citas.map(cita => {
            if (cita.id === response.data.id) {
              return response.data;
            } else {
              return cita;
            }
          });
        });
      } else {
        CitaService.insertarCita(this.cita).then(response => {
          this.citas.push(response.data);
        });
      }
    },
    eliminarCita(id) {
      CitaService.eliminarCita(id).then(() => {
        this.citas = this.citas.filter(cita => cita.id !== id);
      });
    }
  }
}
</script>
