<!-- src/App.vue -->

<template>
  <div id="app" class="d-flex flex-column min-vh-100">
    <Navbar/>
    <div class="container my-4">
      <router-view/>
    </div>
    <Footer class="mt-auto"/>
  </div>
</template>

<script>
import Navbar from './components/Navbar.vue'
import Footer from './components/Footer.vue'

export default {
  name: 'App',
  components: {
    Navbar,
    Footer
  }
}
</script>

<style>
body {
  background-color: #f8f9fa;
  font-family: Arial, sans-serif;
}

.container {
  max-width: 900px;
}

h1, h2, h3, h4, h5, h6 {
  color: #343a40;
}

a {
  color: #007bff;
}

a:hover {
  color: #0056b3;
}
</style>
