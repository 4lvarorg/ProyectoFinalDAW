<template>
  <div class="registro">
    <h1>Registro</h1>
    <form>
      <div class="form-group">
        <label for="email">Correo Electrónico</label>
        <input type="email" class="form-control" id="email" required>
      </div>
      <div class="form-group">
        <label for="password">Contraseña</label>
        <input type="password" class="form-control" id="password" required>
      </div>
      <button type="submit" class="btn btn-primary">Registrar</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'Registro',
}
</script>

<style scoped>
.registro {
  width: 300px;
  margin: auto;
  margin-top: 40px;
}
</style>
