<template>
  <div class="login">
    <h1>Iniciar sesión</h1>
    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label for="userType">Tipo de Usuario</label>
        <select v-model="userType" class="form-control" id="userType" required>
          <option value="usuario">Usuario</option>
          <option value="psicologo">Psicólogo</option>
        </select>
      </div>
      <div class="form-group">
        <label for="email">Correo Electrónico</label>
        <input v-model="email" type="email" class="form-control" id="email" required>
      </div>
      <div class="form-group">
        <label for="password">Contraseña</label>
        <input v-model="password" type="password" class="form-control" id="password" required>
      </div>
      <button type="submit" class="btn btn-primary">Iniciar sesión</button>
    </form>
    <p>No te has registrado aún? <router-link to="/Registro">Regístrate</router-link></p>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Login',
  data() {
    return {
      email: '',
      password: '',
      nombre:'',
      userType: 'usuario'
    }
  },
  methods: {
    async submitForm() {
      let url = '/login/loginUsuario';
      if (this.userType === 'psicologo') {
        url = '/login/loginPsicologo';
      }

      const response = await axios.post(url, {
        email: this.email,
        password: this.password,
        nombre:this.nombre
      })

      if (response.data) {
        sessionStorage.setItem('userName',response.data.nombre);
        sessionStorage.setItem('userEmail',response.data.username);
        if (this.userType === 'usuario') {
          this.$router.push({name:'PanelUsuario',params:{email:response.data.username,nombre:response.data.nombre,rol:2}});
        } else {
          this.$router.push({name:'PanelPsicologo',params:{email:response.data.username,nombre:response.data.nombre,rol:3}});
        }
      } else {
        alert('Usuario o contraseña incorrectos');
      }
    }
  },

}
</script>

<style scoped>
.login {
  width: 300px;
  margin: auto;
  margin-top: 40px;
}
</style>
