package com.helpmybrain.dao;

import com.helpmybrain.entity.Cita;
import com.helpmybrain.entity.Usuario;
import com.helpmybrain.entity.Psicologo;
import java.util.List;

public interface CitaDAO {
    Cita guardarCita(Cita cita);
    Cita actualizarCita(Cita cita);
    void eliminarCita(Integer id);
    Cita obtenerCitaPorId(Integer id);
    List<Cita> obtenerTodasLasCitas();
    List<Cita> obtenerCitasPorUsuarioYPsicologo(Usuario usuario, Psicologo psicologo);
    List<Cita> obtenerCitasPorEmailUsuario(String email);
}
