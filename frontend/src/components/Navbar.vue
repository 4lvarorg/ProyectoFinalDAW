<!-- src/components/Navbar.vue -->

<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <router-link class="navbar-brand" to="/">HelpMyBrain</router-link>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link class="nav-link" to="/psicologos">Psicólogos</router-link>
        </li>
        <li v-if="nombreUsuario === null" class="nav-item">
          <router-link class="nav-link" to="/login">Iniciar sesión</router-link>
        </li>
        <li v-if="nombreUsuario === null"  class="nav-item">
          <router-link class="nav-link" to="/registro">Registrarse</router-link>
        </li>
        <li v-if="nombreUsuario !== null" class="nav-item">
          <p class="nav-link" >Nombre : {{ nombreUsuario }}</p>
        </li>
        <li v-if="nombreUsuario !== null"  class="nav-item">
          <p class="nav-link" >Email : {{ emailUsuario }}</p>
        </li>
        <li v-if="nombreUsuario !== null" class="nav-item">
          <p @click="logout()" class="nav-link" >Cerrar Session</p>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
export default {
  name: 'Navbar',
  methods: {
    logout(){
      sessionStorage.clear();
      this.emailUsuario=null;
      this.nombreUsuario=null;
      this.$router.push('/login');
    }
  },
  data: function(){
    return{
      nombreUsuario:null,
      emailUsuario:null
    }
  },
  created() {
    this.$watch(
        () => this.$route.params,
        (toParams, previousParams) => {
          if(toParams.nombre){
            this.nombreUsuario = toParams.nombre;
            this.emailUsuario = toParams.email;
          }else {
            this.nombreUsuario = null;
            this.emailUsuario = null;
          }
        }
    )
  }
}

</script>

<style scoped>
.navbar {
  border-bottom: 1px solid #e7e7e7;
}

.navbar-brand {
  color: #007bff;
}

.nav-link {
  color: #343a40;
}

.nav-link:hover {
  color: #007bff;
}
</style>
