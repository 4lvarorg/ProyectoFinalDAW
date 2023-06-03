// src/router/index.js

import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Psicologos from '../views/Psicologos.vue'
import Login from '../views/Login.vue'
import Registro from '../views/Registro.vue'

const routes = [
  {
    path: '/',
    name: 'Inicio',
    component: Home
  },
  {
    path: '/psicologos',
    name: 'Psicologos',
    component: Psicologos
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/registro',
    name: 'Registro',
    component: Registro
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
