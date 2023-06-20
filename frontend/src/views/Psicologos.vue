<template>
  <div class="psicologos">
    <div class="row">
      <div class="col-md-6" v-for="n in 4" :key="n">
        <div class="card">
          <img class="card-img-top" :src="imagenes[n-1]" alt="Psicólogo">
          <div class="card-body">
            <p class="card-text">{{ descripciones[n-1] }}</p>
            <router-link to="/login" class="btn btn-primary">Reserve Ahora</router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Psicologos',
  data() {
    return {
      imagenes: [
        'https://www.autonomosyemprendedor.es/asset/thumbnail,1280,720,center,center/media/autonomosyemprendedor/images/2019/09/12/2019091219232360513.jpg',
        'https://s1.eestatic.com/2019/01/18/actualidad/actualidad_369475125_112625460_1706x960.jpg',
        'https://s1.eestatic.com/2019/01/18/actualidad/actualidad_369475125_112625460_1706x960.jpg',
        'https://www.autonomosyemprendedor.es/asset/thumbnail,1280,720,center,center/media/autonomosyemprendedor/images/2019/09/12/2019091219232360513.jpg'
      ],
      descripciones: [
        'Psicólogos Colegiados 100% verificados',
        'Facilidad de contacto',
        'Multitud de descuentos',
        'La salud mental es nuestro compromiso'
      ]
    }
  }
}
</script>

<style scoped>
.card {
  width: 100%;
}

.card-img-top {
  width: 100%;
  height: auto;
  object-fit: cover;
}
</style>
