<template>
  <div class="tabla-cita">
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Fecha Reservada</th>
        <th>Hora Reservada</th>
        <th>Precio Final</th>
        <th>Psicólogo ID</th>
        <th>Usuario ID</th>
        <th>Acciones</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="cita in citas" :key="cita.id">
        <td>{{ cita.id }}</td>
        <td>{{ cita.fechaReservada }}</td>
        <td>{{ cita.horaReservada }}</td>
        <td>{{ cita.precioFinal }}</td>
        <td>{{ cita.psicologo_id }}</td>
        <td>{{ cita.usuario_id }}</td>
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
      <input v-model="cita.fechaReservada" placeholder="Fecha Reservada">
      <input v-model="cita.horaReservada" placeholder="Hora Reservada">
      <input v-model="cita.precioFinal" placeholder="Precio Final">
      <input v-model="cita.psicologo_id" placeholder="Psicólogo ID">
      <input v-model="cita.usuario_id" placeholder="Usuario ID">
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
        fechaReservada: '',
        horaReservada: '',
        precioFinal: '',
        psicologo_id: '',
        usuario_id: ''
      }
    };
  },
  created() {
    this.fetchCitas();
  },
  methods: {
    fetchCitas() {
      CitaService.obtenerTodasLasCitas().then(response => {
        this.citas = response.data;
      });
    },
    actualizarCita(cita) {
      this.cita = Object.assign({}, cita);
    },
    guardarCita() {
      // Crear un nuevo objeto con sólo los campos que necesitas
      let citaData = {
        id: this.cita.id,
        fechaReservada: this.cita.fechaReservada,
        horaReservada: this.cita.horaReservada,
        precioFinal: this.cita.precioFinal,
        usuario_id: Number(this.cita.usuario_id),
        psicologo_id: Number(this.cita.psicologo_id)
      };

      if (this.cita.id) {
        CitaService.actualizarCita(citaData).then(() => {
          this.fetchCitas();
        });
      } else {
        CitaService.insertarCita(citaData).then(() => {
          this.fetchCitas();
        });
      }
      this.cita = {
        id: '',
        fechaReservada: '',
        horaReservada: '',
        precioFinal: '',
        psicologo_id: '',
        usuario_id: ''
      };
    },

    eliminarCita(id) {
      CitaService.eliminarCita(id).then(() => {
        this.fetchCitas();
      });
    }
  }
};
</script>

<style scoped>
.tabla-cita {
  width: 100%;
  margin: 0 auto;
}
table {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}
tr:nth-child(even) {
  background-color: #f2f2f2;
}
th {
  background-color: #4caf50;
  color: white;
}
</style>
