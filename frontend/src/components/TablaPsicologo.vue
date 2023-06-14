<template>
  <div class="tabla-psicologo">
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Email</th>
        <th>Teléfono</th>
        <th>Dirección</th>
        <th>Código Postal</th>
        <th>Código Colegiado</th>
        <th>Precio por Hora</th>
        <th>Fecha Disponible</th>
        <th>Hora Disponible</th>
        <th>Rol</th>
        <th>Cita</th>
        <th>Acciones</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="psicologo in psicologos" :key="psicologo.id">
        <td>{{ psicologo.id }}</td>
        <td>{{ psicologo.nombre }}</td>
        <td>{{ psicologo.apellido }}</td>
        <td>{{ psicologo.email }}</td>
        <td>{{ psicologo.telefono }}</td>
        <td>{{ psicologo.direccion }}</td>
        <td>{{ psicologo.codigoPostal }}</td>
        <td>{{ psicologo.codColegiado }}</td>
        <td>{{ psicologo.precioPorHora }}</td>
        <td>{{ psicologo.fechaDisponible }}</td>
        <td>{{ psicologo.horaDisponible }}</td>
        <td>{{ psicologo.rol }}</td>
        <td>{{ psicologo.cita }}</td>
        <td>
          <button @click="actualizarPsicologo(psicologo)">Actualizar</button>
          <button @click="eliminarPsicologo(psicologo.id)">Eliminar</button>
        </td>
      </tr>
      </tbody>
    </table>
    <div>
      <h2>Insertar/Actualizar Psicólogo</h2>
      <input v-model="psicologo.id" placeholder="ID">
      <input v-model="psicologo.nombre" placeholder="Nombre">
      <input v-model="psicologo.apellido" placeholder="Apellido">
      <input v-model="psicologo.email" placeholder="Email">
      <input v-model="psicologo.telefono" placeholder="Teléfono">
      <input v-model="psicologo.direccion" placeholder="Dirección">
      <input v-model="psicologo.codigoPostal" placeholder="Código Postal">
      <input v-model="psicologo.codColegiado" placeholder="Código Colegiado">
      <input v-model="psicologo.precioPorHora" placeholder="Precio por Hora">
      <input v-model="psicologo.fechaDisponible" placeholder="Fecha Disponible">
      <input v-model="psicologo.horaDisponible" placeholder="Hora Disponible">
      <input v-model="psicologo.rol" placeholder="Rol">
      <input v-model="psicologo.cita" placeholder="Cita">
      <button @click="guardarPsicologo()">Guardar</button>
    </div>
  </div>
</template>
<script>
import PsicologoService from '../services/PsicologoService.js';

export default {
  name: 'TablaPsicologo',
  data() {
    return {
      psicologos: [],
      psicologo: {
        id: null,
        nombre: '',
        apellido: '',
        email: '',
        telefono: '',
        direccion: '',
        codigoPostal: '',
        codColegiado: '',
        precioPorHora: null,
        fechaDisponible: '',
        horaDisponible: '',
        rol: '',
        cita: ''
      }
    };
  },
  created() {
    PsicologoService.obtenerTodosLosPsicologos().then(response => {
      this.psicologos = response.data;
    });
  },
  methods: {
    actualizarPsicologo(psicologo) {
      this.psicologo = Object.assign({}, psicologo);
    },
    guardarPsicologo() {
      if (this.psicologo.id) {
        PsicologoService.actualizarPsicologo(this.psicologo).then(response => {
          this.psicologos = this.psicologos.map(psicologo => {
            if (psicologo.id === response.data.id) {
              return response.data;
            } else {
              return psicologo;
            }
          });
        });
      } else {
        PsicologoService.insertarPsicologo(this.psicologo).then(response => {
          this.psicologos.push(response.data);
        });
      }
      this.psicologo = {
        id: null,
        nombre: '',
        apellido: '',
        email: '',
        telefono: '',
        direccion: '',
        codigoPostal: '',
        codColegiado: '',
        precioPorHora: null,
        fechaDisponible: '',
        horaDisponible: '',
        rol: '',
        cita: ''
      };
    },
    eliminarPsicologo(id) {
      PsicologoService.eliminarPsicologo(id).then(() => {
        this.psicologos = this.psicologos.filter(psicologo => psicologo.id !== id);
      });
    }
  }
}
</script>
