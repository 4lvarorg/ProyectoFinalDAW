import axios from 'axios';

const API_URL = 'http://localhost:8080/helpmybrain/citas/';

class CitaService {
    obtenerTodasLasCitas() {
        return axios.get(API_URL);
    }

    obtenerCitaPorId(id) {
        return axios.get(API_URL + id);
    }

    insertarCita(cita) {
        return axios.post(API_URL, cita);
    }

    actualizarCita(cita) {
        return axios.put(API_URL + cita.id, cita);
    }

    eliminarCita(id) {
        return axios.delete(API_URL + id);
    }
    obtenerCitaPorEmail(email) {
        return axios.get(API_URL + 'email/' + email);
    }
}

export default new CitaService();
