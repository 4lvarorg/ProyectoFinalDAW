package com.helpmybrain.service;

import com.helpmybrain.entity.Cita;

import java.util.List;

public interface CitaService {
    Cita guardarCita(Cita cita);
    Cita actualizarCita(Cita cita);
    void eliminarCita(Integer id);
    Cita obtenerCitaPorId(Integer id);
    List<Cita> obtenerTodasLasCitas();
    List<Cita> obtenerCitasPorEmailUsuario(String email);
}
