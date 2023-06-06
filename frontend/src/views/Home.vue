<template>
  <div class="home">
    <h1>Bienvenido a HelpMyBrain</h1>
    <p>
      Aquí puedes encontrar psicólogos y reservar citas de manera fácil y rápida.
    </p>
    <carousel :items-to-show="1">
      <slide v-for="(image, index) in images" :key="index">
        <img :src="image" alt="Psicólogo">
      </slide>

      <template #addons>
        <navigation />
        <pagination />
      </template>
    </carousel>
  </div>
</template>

<script>
import 'vue3-carousel/dist/carousel.css';
import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel';

export default {
  name: 'Home',
  components: {
    Carousel,
    Slide,
    Pagination,
    Navigation,
  },
  data() {
    return {
      images: [
        'https://www.autonomosyemprendedor.es/asset/thumbnail,1280,720,center,center/media/autonomosyemprendedor/images/2019/09/12/2019091219232360513.jpg',
        'https://uploads-ssl.webflow.com/6165ed3d14364c72a597fe4e/6197bb66750848861998d8fd_pexels-photo-4101143-p-1080.jpeg',
      ],
    }
  },
}
</script>

<style scoped>
.home {
  text-align: center;
  margin-top: 40px;
}

.carousel {
  width: 100%;
  margin: auto;
}

.carousel img {
  max-width: 100%;
  height: auto;
}
</style>
