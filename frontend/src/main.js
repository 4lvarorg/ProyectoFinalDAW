// src/main.js

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js';


const app = createApp(App)

// Configuración de Axios
axios.defaults.baseURL = 'http://localhost:8080/helpmybrain/'

app.use(router).mount('#app')
