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
import UsuarioService from "../services/UsuarioService";

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
      rolLogeado:0,
      usuId : 0
    };

  },
  created() {

    this.rolLogeado = this.$route.params.rol;
    this.obtenerPsicologos();
    if(this.rolLogeado ==="2"){
      this.obtenerUsuario();
    }else{
      this.obtenerCitasPsicologo();
    }
  },
  methods: {
    obtenerUsuario() {
      UsuarioService.obtenerUsuarioPorEmail(this.$route.params.email).then(response => {
        this.usuId = response.data.id;
        this.nombreUsuario = response.data.nombre;
        if (response.data.role.id === 1) {
          this.rolLogeado = 1;
          this.fetchCitas();
        }else{
          this.fetchCitas();
        }
      });
    },
    fetchCitas() {
      let usuarioEmail = this.$route.params.email;
      if(this.rolLogeado === 1){
        CitaService.obtenerTodasLasCitas().then(response => {
          let citaPsi = {
            fechaReservada:'',
            horaReservada:'',
            nombrePsicologo:'',
            precioFinal:0,
            id:0,
            usuario:0,
            psicologo:''
          }
          if(response.data.length !==0){
            this.nombreUsuario = response.data[0].usuario.nombre;
            this.cita.usuario_id =response.data[0].usuario.id;
            this.nombrePsicologo = response.data[0].psicologo.nombre;
            response.data[0].usuario.role.usuarios.forEach(el =>{
              if(el.id){
                el.cita.forEach(element=>{
                  if(element.id){
                    element.psicologo.role.psicologos.forEach(psi =>{
                      if(psi.id){
                        psi.cita.forEach(cita =>{
                          if(cita.id){
                            citaPsi = {
                              fechaReservada:cita.fechaReservada,
                              horaReservada:cita.horaReservada,
                              nombrePsicologo:psi.nombre,
                              precioFinal:cita.precioFinal,
                              id:cita.id,
                              usuario:cita.usuario,
                              psicologo:cita.psicologo,
                              nombreUsuario:this.nombreUsuario
                            }
                            this.citas.push(citaPsi);
                          }
                        })
                      }
                    })
                  }
                })
              }
              if(el.id && el.cita.length !== 0){
                el.cita.forEach(element =>{
                  if(element.id){
                    element.psicologo.cita.forEach(cita =>{
                      if(cita.id){
                        citaPsi = {
                          fechaReservada:cita.fechaReservada,
                          horaReservada:cita.horaReservada,
                          nombrePsicologo:element.psicologo.nombre,
                          precioFinal:cita.precioFinal,
                          id:cita.id,
                          usuario:cita.usuario,
                          psicologo:cita.psicologo,
                          nombreUsuario:this.nombreUsuario
                        }
                        this.citas.push(citaPsi);
                      }
                    })
                  }
                })
              }
            })
            this.citas = this.citas.filter(cita => cita.psicologo);
          }
        });
      }else{
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
          if(response.data.length !==0){
            this.nombreUsuario = response.data[0].usuario.nombre;
            this.cita.usuario_id =response.data[0].usuario.id;
            this.nombrePsicologo = response.data[0].psicologo.nombre;
            response.data[0].usuario.role.usuarios.forEach(el =>{
              if(el.id){
                el.cita.forEach(element=>{
                  if(element.id){
                    element.psicologo.role.psicologos.forEach(psi =>{
                      if(psi.id){
                        psi.cita.forEach(cita =>{
                          if(cita.id){
                            citaPsi = {
                              fechaReservada:cita.fechaReservada,
                              horaReservada:cita.horaReservada,
                              nombrePsicologo:psi.nombre,
                              precioFinal:cita.precioFinal,
                              id:cita.id,
                              usuario:cita.usuario,
                              psicologo:cita.psicologo,
                              nombreUsuario:this.nombreUsuario
                            }
                            this.citas.push(citaPsi);
                          }
                        })
                      }
                    })
                  }
                })
              }
              if(el.id && el.cita.length !== 0){
                el.cita.forEach(element =>{
                  if(element.id){
                    element.psicologo.cita.forEach(cita =>{
                      if(cita.id){
                        citaPsi = {
                          fechaReservada:cita.fechaReservada,
                          horaReservada:cita.horaReservada,
                          nombrePsicologo:element.psicologo.nombre,
                          precioFinal:cita.precioFinal,
                          id:cita.id,
                          usuario:cita.usuario,
                          psicologo:cita.psicologo,
                          nombreUsuario:this.nombreUsuario
                        }
                        this.citas.push(citaPsi);
                      }
                    })
                  }
                })
              }
            })
            this.citas = this.citas.filter(cita => cita.psicologo);
          }
        });
      }
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

      this.citas=[];
      const arrayHora = this.cita.horaReservada.split(':');

      let citaData = {
        id: this.cita.id,
        fechaReservada: this.cita.fechaReservada,
        horaReservada: Number(arrayHora[0] + ':' + arrayHora[1]),
        precioFinal: this.cita.precioFinal,
        usuario_id: Number(this.cita.usuario) ? Number(this.cita.usuario) : this.usuId,
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
    obtenerCitasPsicologo() {
      let usuarioEmail = this.$route.params.email;
      let nombreUsuario = this.$route.params.nombre;
      CitaService.obtenerTodasLasCitas().then(response => {
        let citaPsi = {
          fechaReservada:'',
          horaReservada:'',
          nombrePsicologo:'',
          precioFinal:0,
          id:0,
          usuario:0,
          psicologo:''
        }
        if(response.data.length !==0){
          this.nombreUsuario = response.data[0].usuario.nombre;
          this.cita.usuario_id =response.data[0].usuario.id;
          this.nombrePsicologo = response.data[0].psicologo.nombre;
          response.data[0].usuario.role.usuarios.forEach(el =>{
            if(el.id){
              el.cita.forEach(element=>{
                if(element.id){
                  element.psicologo.role.psicologos.forEach(psi =>{
                    if(psi.id){
                      psi.cita.forEach(cita =>{
                        if(cita.id){
                          citaPsi = {
                            fechaReservada:cita.fechaReservada,
                            horaReservada:cita.horaReservada,
                            nombrePsicologo:psi.nombre,
                            precioFinal:cita.precioFinal,
                            id:cita.id,
                            usuario:cita.usuario,
                            psicologo:cita.psicologo,
                            nombreUsuario:this.nombreUsuario
                          }
                          this.citas.push(citaPsi);
                        }
                      })
                    }
                  })
                }
              })
            }
            if(el.id && el.cita.length !== 0){
              el.cita.forEach(element =>{
                if(element.id){
                  element.psicologo.cita.forEach(cita =>{
                    if(cita.id){
                      citaPsi = {
                        fechaReservada:cita.fechaReservada,
                        horaReservada:cita.horaReservada,
                        nombrePsicologo:element.psicologo.nombre,
                        precioFinal:cita.precioFinal,
                        id:cita.id,
                        usuario:cita.usuario,
                        psicologo:cita.psicologo,
                        nombreUsuario:this.nombreUsuario
                      }
                      this.citas.push(citaPsi);
                    }
                  })
                }
              })
            }
          })
          this.citas = this.citas.filter(cita => cita.nombrePsicologo === nombreUsuario);
        }
      });
    },

    eliminarCita(id) {
      this.citas=[];
      console.log(id)
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
  background-color: #0d6efd;
  color: white;
}
</style>
