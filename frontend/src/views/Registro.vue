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

    };
  },
  methods: {
    async register() {
      const data = {
        email: this.email,
        password: this.password,
        role_id: this.type === 'usuario' ? 2 : 3,

      };

      try {
        if (this.type === 'usuario') {
          await UsuarioService.insertarUsuario(data);
        } else {
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
