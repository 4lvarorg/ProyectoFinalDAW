import axios from 'axios';

const API_URL = 'http://localhost:8080/helpmybrain/psicologos/';

class PsicologoService {
    obtenerTodosLosPsicologos() {
        return axios.get(API_URL);
    }

    obtenerPsicologoPorId(id) {
        return axios.get(API_URL + id);
    }

    obtenerPsicologoPorEmail(email) {
        return axios.get(API_URL + 'email/' + email);
    }

    guardarPsicologo(psicologo) {
        return axios.post(API_URL, psicologo);
    }

    actualizarPsicologo(psicologo) {
        return axios.put(API_URL, psicologo);
    }

    eliminarPsicologo(id) {
        return axios.delete(API_URL + id);
    }
}

export default new PsicologoService();
