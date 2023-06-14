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
        <td>{{ cita.usuario }}</td>
        <td>{{ cita.psicologo }}</td>
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
      <input v-model="cita.usuario" placeholder="Usuario">
      <input v-model="cita.psicologo" placeholder="Psicólogo">
      <input v-model="cita.fechaReservada" placeholder="Fecha Reservada">
      <input v-model="cita.horaReservada" placeholder="Hora Reservada">
      <input v-model="cita.precioFinal" placeholder="Precio Final">
      <button @click="guardarCita()">Guardar</button>
    </div>
  </div>
</template>

<script>
import CitaService from '../services/CitaService.js';

export default {
  name: 'TablaCita',
  data() {
    return {
      citas: [],
      cita: {
        id: '',
        usuario: '',
        psicologo: '',
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
      this.cita = {
        id: null,
        usuario: '',
        psicologo: '',
        fechaReservada: '',
        horaReservada: '',
        precioFinal: null
      };
    },
    eliminarCita(id) {
      CitaService.eliminarCita(id).then(() => {
        this.citas = this.citas.filter(cita => cita.id !== id);
      });
    }
  }
}
</script>

