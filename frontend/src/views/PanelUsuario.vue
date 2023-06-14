<template>
  <div class="panel-usuario">
    <h1>Panel de Usuario</h1>
    <TablaUsuario />
    <TablaCita />
  </div>
</template>

<script>
import TablaUsuario from '../components/TablaUsuario.vue'
import TablaCita from '../components/TablaCita.vue'

export default {
  name: 'PanelUsuario',
  components: {
    TablaUsuario,
    TablaCita
  }
}
</script>

<style scoped>
.panel-usuario {
  text-align: center;
  margin-top: 40px;
}
</style>
