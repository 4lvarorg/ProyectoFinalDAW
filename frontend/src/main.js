// src/main.js

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import 'bootstrap/dist/css/bootstrap.min.css'

const app = createApp(App)

// Configuración de Axios
axios.defaults.baseURL = 'http://localhost:8080/helpmybrain/v1' // Asegúrate de que este es el correcto para tu backend

app.use(router).mount('#app')
