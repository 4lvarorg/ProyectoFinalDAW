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
        <td>
          <button @click="actualizarPsicologo(psicologo)">Actualizar</button>
          <button @click="eliminarPsicologo(psicologo.id)">Eliminar</button>
        </td>
      </tr>
      </tbody>
    </table>
    <div>
      <h2>Actualizar Psicólogo</h2>
      <input v-model="psicologo.nombre" placeholder="Nombre">
      <input v-model="psicologo.apellido" placeholder="Apellido">
      <input v-model="psicologo.email" placeholder="Email">
      <input v-model="psicologo.telefono" placeholder="Teléfono">
      <input v-model="psicologo.direccion" placeholder="Dirección">
      <input v-model="psicologo.codigoPostal" placeholder="Código Postal">
      <input v-model="psicologo.codiColegiado" placeholder="Código Colegiado">
      <input v-model="psicologo.precioPorHora" placeholder="Precio por Hora">
      <input v-model="psicologo.fechaDisponible" placeholder="YYYY-MM-DD">
      <input v-model="psicologo.horaDisponible" placeholder="HH:MM">
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
        codiColegiado: '',
        precioPorHora: '',
        fechaDisponible: '',
        horaDisponible: '',
        role_id: 3,
      }
    };
  },
  created() {
    let psicologoEmail = this.$route.params.email;
    PsicologoService.obtenerTodosLosPsicologos().then(response => {
      response.data.forEach(p => {
        if(p.id){
          this.psicologos.push(p);
          if(p.role){
            p.role.psicologos.forEach(psico => {
              if(psico.id){
                this.psicologos.push(psico);
              }
            });
          }
        }
      });
      this.psicologos = this.psicologos.filter(psicologo => psicologo.email === psicologoEmail);
      console.log(this.psicologos)
    });
  },
  methods: {
    actualizarPsicologo(psicologo) {
      this.psicologo = Object.assign({}, psicologo);
    },
    guardarPsicologo() {
      if (this.psicologo.id) {
        let datos ={
          id: this.psicologo.id,
          nombre: this.psicologo.nombre,
          apellido: this.psicologo.apellido,
          email: this.psicologo.email,
          telefono: this.psicologo.telefono,
          direccion: this.psicologo.direccion,
          codigoPostal: this.psicologo.codigoPostal,
          codiColegiado: this.psicologo.codiColegiado,
          precioPorHora: this.psicologo.precioPorHora,
          fechaDisponible: this.psicologo.fechaDisponible,
          horaDisponible: this.psicologo.horaDisponible,
          role_id: 3,
        };
        PsicologoService.actualizarPsicologo(datos).then(response => {
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
        id: '',
        nombre: '',
        apellido: '',
        email: '',
        telefono: '',
        direccion: '',
        codigoPostal: '',
        codColegiado: '',
        precioPorHora: '',
        fechaDisponible: '',
        horaDisponible: '',
        role_id: 3,
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
<style scoped>
.tabla-psicologo {
  width: 100%;
  margin: 0 auto;
  margin-bottom: 60px;
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
  background-color: #0d6efd;
  color: white;
}
bloque2{
  width: 100%;
  margin: 0 auto;
  margin-bottom: 60px;
}
</style>