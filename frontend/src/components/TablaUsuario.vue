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
        <th>Role</th>
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
        <td>{{ usuario.role }}</td>
        <td>{{ usuario.cita }}</td>
        <td>
          <button @click="editarUsuario(usuario)">Editar</button>
          <button @click="eliminarUsuario(usuario.id)">Eliminar</button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import UsuarioService from '../services/UsuarioService.js';

export default {
  name: 'TablaUsuario',
  data() {
    return {
      usuarios: []
    };
  },
  created() {
    UsuarioService.obtenerTodosLosUsuarios().then(response => {
      this.usuarios = response.data;
    });
  },
  methods: {
    editarUsuario(usuario) {
      // Aquí irá el código para editar un usuario
    },
    eliminarUsuario(id) {
      UsuarioService.eliminarUsuario(id).then(() => {
        this.usuarios = this.usuarios.filter(usuario => usuario.id !== id);
      });
    }
  }
}
</script>

<style scoped>
/* Aquí puedes añadir cualquier estilo específico para esta tabla */
</style>
