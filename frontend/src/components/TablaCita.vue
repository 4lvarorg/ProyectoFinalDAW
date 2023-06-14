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
          <button @click="editarCita(cita)">Editar</button>
          <button @click="eliminarCita(cita.id)">Eliminar</button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import CitaService from '../services/CitaService.js';

export default {
  name: 'TablaCita',
  data() {
    return {
      citas: []
    };
  },
  created() {
    CitaService.obtenerTodasLasCitas().then(response => {
      this.citas = response.data;
    });
  },
  methods: {
    editarCita(cita) {
      // Aquí irá el código para editar una cita
    },
    eliminarCita(id) {
      CitaService.eliminarCita(id).then(() => {
        this.citas = this.citas.filter(cita => cita.id !== id);
      });
    }
  }
}
</script>

<style scoped>
/* Aquí puedes añadir cualquier estilo específico para esta tabla */
</style>
