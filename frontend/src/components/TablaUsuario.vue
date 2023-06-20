<template>
  <div class="tabla-usuario">
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
        <th>Rol</th>
        <th>Cita</th>
        <th>Acciones</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="usuario in usuarios" :key="usuario.id">
        <td>{{ usuario.id }}</td>
        <td>{{ usuario.nombre }}</td>
        <td>{{ usuario.apellido }}</td>
        <td>{{ usuario.email }}</td>
        <td>{{ usuario.telefono }}</td>
        <td>{{ usuario.direccion }}</td>
        <td>{{ usuario.codigoPostal }}</td>
        <td>{{ usuario.role_id ? usuario.role_id.nombre : '' }}</td>
        <td>
          <ul v-if="usuario.cita && usuario.cita.length">
            <li v-for="(cita, index) in usuario.cita" :key="index">
              <template v-if="typeof cita === 'object'">
                ID: {{ cita.id }}, Fecha Reservada: {{ cita.fechaReservada }}, Hora Reservada: {{ cita.horaReservada }}, Precio Final: {{ cita.precioFinal }}
              </template>
              <template v-else>
                {{ cita }}
              </template>
            </li>
          </ul>
          <span v-else>No hay citas</span>
        </td>
        <td>
          <button @click="actualizarUsuario(usuario)">Actualizar</button>
          <button @click="eliminarUsuario(usuario.id)">Eliminar</button>
        </td>
      </tr>
      </tbody>
    </table>
    <div>
      <h2>Insertar/Actualizar Usuario</h2>
      <input v-model="usuario.id" placeholder="ID">
      <input v-model="usuario.nombre" placeholder="Nombre">
      <input v-model="usuario.apellido" placeholder="Apellido">
      <input v-model="usuario.email" placeholder="Email">
      <input v-model="usuario.telefono" placeholder="Teléfono">
      <input v-model="usuario.direccion" placeholder="Dirección">
      <input v-model="usuario.codigoPostal" placeholder="Código Postal">
      <input v-model="usuario.role_id" placeholder="Rol">
      <button @click="guardarUsuario()">Guardar</button>
    </div>
  </div>
</template>



<script>
import UsuarioService from '../services/UsuarioService.js';

export default {
  name: 'TablaUsuario',
  data() {
    return {
      usuarios: [],
      usuario: {
        id: '',
        nombre: '',
        apellido: '',
        email: '',
        telefono: '',
        direccion: '',
        codigoPostal: '',
        role_id: 2,
      }
    };
  },
  created() {
    UsuarioService.obtenerTodosLosUsuarios().then(response => {
      this.usuarios = response.data;
    });
  },
  methods: {
    actualizarUsuario(usuario) {
      this.usuario = Object.assign({}, usuario);
    },
    guardarUsuario() {
      if (this.usuario.id) {
        UsuarioService.actualizarUsuario(this.usuario).then(response => {
          this.usuarios = this.usuarios.map(usuario => {
            if (usuario.id === response.data.id) {
              return response.data;
            } else {
              return usuario;
            }
          });
        });
      } else {
        UsuarioService.insertarUsuario(this.usuario).then(response => {
          this.usuarios.push(response.data);
        });
      }
      this.usuario = {
        id: '',
        nombre: '',
        apellido: '',
        email: '',
        telefono: '',
        direccion: '',
        codigoPostal: '',
        role_id: 2,
      };
    },
    eliminarUsuario(id) {
      UsuarioService.eliminarUsuario(id).then(() => {
        this.usuarios = this.usuarios.filter(usuario => usuario.id !== id);
      });
    }
  }
};
</script>