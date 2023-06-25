<template>
  <div class="tabla-cita">
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Fecha Reservada</th>
        <th>Hora Reservada</th>
        <th>Precio Final</th>
        <th>Psicólogo</th>
        <th>Usuario</th>
        <th>Acciones</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="cita in citas" :key="cita.id">
        <td>{{ cita.id }}</td>
        <td>{{ cita.fechaReservada }}</td>
        <td>{{ cita.horaReservada }}</td>
        <td>{{ cita.precioFinal }}</td>
        <td>{{ cita.nombrePsicologo }}</td>
        <td>{{ cita.nombreUsuario }}</td>
        <td>
          <button v-if="rolLogeado === '2'" @click="actualizarCita(cita)">Actualizar</button>
          <button @click="eliminarCita(cita.id)">Eliminar</button>
        </td>
      </tr>
      </tbody>
    </table>
    <div v-if="rolLogeado === '2'">
      <h2>Insertar/Actualizar Cita</h2>
      <label class="label-default">Fecha Reservada</label>
      <input v-model="cita.fechaReservada" placeholder="YYYY-MM-DD"><br><br>
      <label class="label-default">Hora Reservada</label>
      <input v-model="cita.horaReservada" placeholder="HH:MM"><br><br>
      <label class="label-default">Precio Final</label>
      <input v-model="cita.precioFinal" placeholder="Precio Final"><br><br>
      <label class="label-default">Psicólogo</label>
      <select v-model="cita.psicologo_id">
        <option v-for="psicologo in psicologos" :key="psicologo.id" :value="psicologo.id">{{ psicologo.nombre }}</option>
      </select><br><br>
      <button @click="guardarCita()">Guardar</button>
      <button @click="eliminarCita(cita.id)">Eliminar</button>
    </div>
  </div>
</template>

<script>
import CitaService from '../services/CitaService.js';
import PsicologoService from "../services/PsicologoService";

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
      },
      nombrePsicologo:'',
      nombreUsuario:'',
      psicologos: [],
      rolLogeado:0
    };
  },
  created() {
    this.rolLogeado = this.$route.params.rol;
    this.fetchCitas();
    this.obtenerPsicologos();

  },
  methods: {
    fetchCitas() {
      let usuarioEmail = this.$route.params.email;

      CitaService.obtenerCitaPorEmail(usuarioEmail).then(response => {
        let citaPsi = {
          fechaReservada:'',
          horaReservada:'',
          nombrePsicologo:'',
          precioFinal:0,
          id:0,
          usuario:0,
          psicologo:''
        }
        if(response.data.length == 0){
          CitaService.obtenerTodasLasCitas().then(response => {
            response.data[0].usuario.cita[0].psicologo.cita.forEach(ele =>{
              if(ele.id){
                citaPsi = {
                  fechaReservada:ele.fechaReservada,
                  horaReservada:ele.horaReservada,
                  nombrePsicologo:response.data[0].usuario.cita[0].psicologo.nombre,
                  precioFinal:ele.precioFinal,
                  id:ele.id,
                  usuario:ele.usuario,
                  psicologo:ele.psicologo,
                  nombreUsuario:response.data[0].usuario.nombre
                }
                this.citas.push(citaPsi);
              }
            });
            this.citas = this.citas.filter(cita => cita.psicologo);
          });
        }else{
          this.nombreUsuario = response.data[0].usuario.nombre;
          this.cita.usuario_id =response.data[0].usuario.id;
          this.nombrePsicologo = response.data[0].psicologo.nombre;
          response.data[0].usuario.cita.forEach(ele =>{
            if(ele.id){
              citaPsi = {
                fechaReservada:ele.fechaReservada,
                horaReservada:ele.horaReservada,
                nombrePsicologo:response.data[0].usuario.cita[0].psicologo.nombre,
                precioFinal:ele.precioFinal,
                id:ele.id,
                usuario:ele.usuario,
                psicologo:ele.psicologo,
                nombreUsuario:response.data[0].usuario.nombre
              }
              this.citas.push(citaPsi);
              if(ele.psicologo && ele.psicologo.cita){
                ele.psicologo.cita.forEach(psi => {
                  if(psi.id){
                    citaPsi = {
                      fechaReservada:psi.fechaReservada,
                      horaReservada:psi.horaReservada,
                      nombrePsicologo:response.data[0].usuario.cita[0].psicologo.nombre,
                      precioFinal:psi.precioFinal,
                      id:psi.id,
                      usuario:psi.usuario,
                      psicologo:psi.psicologo,
                      nombreUsuario:response.data[0].usuario.nombre
                    }
                    this.citas.push(citaPsi);
                  }
                });
              }
              if(ele.psicologo && ele.psicologo.role){
                if(ele.psicologo.role.psicologos){
                  ele.psicologo.role.psicologos.forEach(psico => {
                    if(psico.cita){
                      psico.cita.forEach(cit => {
                        if(cit.id){
                          citaPsi = {
                            fechaReservada:cit.fechaReservada,
                            horaReservada:cit.horaReservada,
                            nombrePsicologo:psico.nombre,
                            precioFinal:cit.precioFinal,
                            id:cit.id,
                            usuario:cit.usuario,
                            psicologo:cit.psicologo,
                            nombreUsuario:response.data[0].usuario.nombre
                          }
                          this.citas.push(citaPsi);
                        }
                      });
                    }
                  });
                }
              }
            }
          });
          this.citas = this.citas.filter(cita => cita.psicologo);
        }
        console.log('que citas',this.citas)
      });
    },
    obtenerPsicologos() {
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
        console.log('a ver', this.psicologos);
      });
    },
    actualizarCita(cita) {
      this.cita = Object.assign({}, cita);
    },
    guardarCita() {
      const arrayHora = this.cita.horaReservada.split(':');
      let citaData = {
        id: this.cita.id,
        fechaReservada: this.cita.fechaReservada,
        horaReservada: arrayHora[0] + ':' + arrayHora[1],
        precioFinal: this.cita.precioFinal,
        usuario_id: Number(this.cita.usuario),
        psicologo_id: Number(this.cita.psicologo_id)
      };


      let promise;
      if (this.cita.id !== '') {
        promise = CitaService.actualizarCita(citaData);
      } else {
        promise = CitaService.insertarCita(citaData);
      }

      promise.then(() => {
        this.fetchCitas();
        this.cita = {
          id: '',
          fechaReservada: '',
          horaReservada: '',
          precioFinal: '',
          psicologo_id: '',
          usuario_id: ''
        };
      });
    },

    eliminarCita(id) {
      CitaService.eliminarCita(id).then(() => {
        this.$router.go();
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
  background-color: #0d6efd;
  color: white;
}
</style>
