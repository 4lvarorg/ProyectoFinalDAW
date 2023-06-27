<template>
  <div class="registro">
    <h1>Registro</h1>
    <form @submit.prevent="register">
      <div class="form-group">
        <label for="type">Tipo de Registro</label>
        <select v-model="type" id="type" class="form-control" required>
          <option value="usuario">Usuario</option>
          <option value="psicologo">Psicólogo</option>
        </select>
      </div>
      <div class="form-group">
        <label for="email">Correo Electrónico</label>
        <input type="email" v-model="email" class="form-control" id="email" required>
      </div>
      <div class="form-group">
        <label for="password">Contraseña</label>
        <input type="password" v-model="password" class="form-control" id="password" required>
      </div>
      <div class="form-group">
        <label for="nombre">Nombre</label>
        <input type="text" v-model="nombre" class="form-control" id="nombre" required>
      </div>
      <div class="form-group">
        <label for="apellidos">Apellido</label>
        <input type="text" v-model="apellido" class="form-control" id="apellido" required>
      </div>
      <div class="form-group">
        <label for="telefono">Teléfono</label>
        <input type="text" v-model="telefono" class="form-control" id="telefono" >
      </div>
      <div class="form-group">
        <label for="direccion">Dirección</label>
        <input type="text" v-model="direccion" class="form-control" id="direccion" >
      </div>
      <div class="form-group">
        <label for="codigo_postal">Código Postal</label>
        <input type="text" v-model="codigo_postal" class="form-control" id="codigo_postal" >
      </div>
      <div v-if="type==='psicologo'" class="form-group">
        <label for="codColegiado">Código Colegiado</label>
        <input type="text" v-model="codi_colegiado" class="form-control" id="codColegiado" >
      </div><br>
      <button type="submit" class="btn btn-primary">Registrar</button>
    </form>
  </div>
</template>

<script>
import UsuarioService from '../services/UsuarioService';
import PsicologoService from '../services/PsicologoService';

export default {
  name: 'Registro',
  data() {
    return {
      type: 'usuario',
      email: '',
      password: '',
      nombre:'',
      apellido:'',
      telefono:'',
      direccion:'',
      codi_colegiado:'',
      codigo_postal:''
    };
  },
  methods: {
    async register() {
      const data = {
        email: this.email,
        password: this.password,
        role_id: this.type === 'usuario' ? 2 : 3,
        nombre:this.nombre,
        apellido:this.apellido,
        telefono:Number(this.telefono),
        direccion:this.direccion,
        codigo_postal:Number(this.codigo_postal)
      };

      try {
        if (this.type === 'usuario') {
          await UsuarioService.insertarUsuario(data);
        } else {
          data.codi_colegiado = Number(this.codi_colegiado);
          await PsicologoService.insertarPsicologo(data);
        }

        alert('Registro exitoso!');
      } catch (error) {
        alert('Hubo un error en el registro. Por favor, vuelve a introducir los datos.');
      }
    },
  },
};
</script>

<style scoped>
.registro {
  width: 300px;
  margin: auto;
  margin-top: 40px;
}
</style>
