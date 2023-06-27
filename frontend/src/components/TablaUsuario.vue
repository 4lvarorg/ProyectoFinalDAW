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
        <td>{{ usuario.role.id ? usuario.role.nombre : '' }}</td>
        <td>
          <button @click="actualizarUsuario(usuario)">Actualizar</button>
          <button @click="eliminarUsuario(usuario.id)">Eliminar</button>
        </td>
      </tr>
      </tbody>
    </table>
    <div class="bloque2">
      <h2>Insertar/Actualizar Usuario</h2>
      <input v-model="usuario.nombre" placeholder="Nombre">
      <input v-model="usuario.apellido" placeholder="Apellido">
      <input v-model="usuario.email" placeholder="Email">
      <input v-model="usuario.telefono" placeholder="Teléfono">
      <input v-model="usuario.direccion" placeholder="Dirección">
      <input v-model="usuario.codigoPostal" placeholder="Código Postal">
      <select v-model="usuario.role_id">
        <option v-for="role in roles" :key="role.id" :value="role.id">{{ role.nombre }}</option>
     </select>
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
      },
      roles: [
        { id: 1, nombre: 'Administrador' },
        { id: 2, nombre: 'Usuario' },
        { id: 3, nombre: 'Psicologo'}
      ],
    };
  },
  created() {
    let usuarioEmail = this.$route.params.email;
    this.rolLogeado = this.$route.params.rol;
    let esAdmin = false;
    UsuarioService.obtenerTodosLosUsuarios().then(response => {
      response.data.forEach(usuario => {
        if (usuario.email === usuarioEmail && usuario.role.id ===1) {
          esAdmin = true;
        }
      });

      if (!esAdmin) {
        this.usuarios = response.data.filter(usuario => usuario.email === usuarioEmail);
        if(this.rolLogeado === '2'){
          esAdmin = false;
          response.data[1].role.usuarios.forEach(usu => {
            if (usu.id && usu.email === usuarioEmail) {
              const usuario = {
                id: usu.id,
                nombre: usu.nombre,
                apellido: usu.apellido,
                email: usu.email,
                telefono: usu.telefono,
                direccion: usu.direccion,
                codigoPostal: usu.codigoPostal,
                role:{
                  id:usu.role,
                  nombre: 'USUARIO',
                },
              };
              this.usuarios.push(usuario);
            }
          });
        }
      }else{
        this.usuarios = response.data.filter(user => user.id );
      }
      console.log(this.usuarios);
      console.log(this.rolLogeado)
    });
  },
  methods: {
    actualizarUsuario(usuario) {
      this.usuario = Object.assign({}, usuario);
    },
    guardarUsuario() {
      if (this.usuario.id != null && this.usuario.id != "" && this.usuario.id != undefined) {
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
        let datos ={
          nombre: this.usuario.nombre,
          apellido: this.usuario.apellido,
          email: this.usuario.email,
          telefono: this.usuario.telefono,
          direccion: this.usuario.direccion,
          codigoPostal: this.usuario.codigoPostal,
          role_id: this.usuario.role_id,
        };
       UsuarioService.insertarUsuario(datos).then(response => {
          this.usuarios.push(response.data);
        });
      }

    },
    eliminarUsuario(id) {
      UsuarioService.eliminarUsuario(id).then(() => {
        this.usuarios = this.usuarios.filter(usuario => usuario.id !== id);
      });
    }
  }
};
</script>
<style scoped>
.tabla-usuario {
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