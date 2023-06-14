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
        <th>Role</th>
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
        <td>{{ psicologo.codiColegiado }}</td>
        <td>{{ psicologo.precioPorHora }}</td>
        <td>{{ psicologo.fechaDisponible }}</td>
        <td>{{ psicologo.horaDisponible }}</td>
        <td>{{ psicologo.role }}</td>
        <td>{{ psicologo.cita }}</td>
        <td>
          <button @click="editarPsicologo(psicologo)">Editar</button>
          <button @click="eliminarPsicologo(psicologo.id)">Eliminar</button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import PsicologoService from '../services/PsicologoService.js';

export default {
  name: 'TablaPsicologo',
  data() {
    return {
      psicologos: []
    };
  },
  created() {
    PsicologoService.obtenerTodosLosPsicologos().then(response => {
      this.psicologos = response.data;
    });
  },
  methods: {
    editarPsicologo(psicologo) {
      // Aquí irá el código para editar un psicólogo
    },
    eliminarPsicologo(id) {
      PsicologoService.eliminarPsicologo(id).then(() => {
        this.psicologos = this.psicologos.filter(psicologo => psicologo.id !== id);
      });
    }
  }
}
</script>

<style scoped>
/* Aquí puedes añadir cualquier estilo específico para esta tabla */
</style>
