<template>
  <div class="login">
    <h1>Iniciar sesión</h1>
    <form>
      <div class="form-group">
        <label for="email">Correo Electrónico</label>
        <input type="email" class="form-control" id="email" required>
      </div>
      <div class="form-group">
        <label for="password">Contraseña</label>
        <input type="password" class="form-control" id="password" required>
      </div>
      <button type="submit" class="btn btn-primary">Iniciar sesión</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'Login',
}
</script>

<style scoped>
.login {
  width: 300px;
  margin: auto;
  margin-top: 40px;
}
</style>
