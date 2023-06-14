import axios from 'axios';

const API_URL = 'http://localhost:8080/helpmybrain/usuarios/';

class UsuarioService {
    obtenerTodosLosUsuarios() {
        return axios.get(API_URL);
    }

    obtenerUsuarioPorId(id) {
        return axios.get(API_URL + id);
    }

    crearUsuario(usuario) {
        return axios.post(API_URL, usuario);
    }

    actualizarUsuario(id, usuario) {
        return axios.put(API_URL + id, usuario);
    }

    eliminarUsuario(id) {
        return axios.delete(API_URL + id);
    }
}

export default new UsuarioService();
