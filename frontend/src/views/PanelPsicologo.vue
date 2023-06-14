<template>
  <div class="panel-psicologo">
    <h1>Panel de Psicólogo</h1>
    <TablaPsicologo />
    <TablaCita />
  </div>
</template>

<script>
import TablaPsicologo from '../components/TablaPsicologo.vue'
import TablaCita from '../components/TablaCita.vue'

export default {
  name: 'PanelPsicologo',
  components: {
    TablaPsicologo,
    TablaCita
  }
}
</script>

<style scoped>
.panel-psicologo {
  text-align: center;
  margin-top: 40px;
}
</style>
