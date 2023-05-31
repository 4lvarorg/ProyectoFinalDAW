package com.helpmybrain.service;

import com.helpmybrain.entity.Psicologo;

import java.util.List;

public interface PsicologoService {
    Psicologo guardarPsicologo(Psicologo psicologo);
    Psicologo actualizarPsicologo(Psicologo psicologo);
    void eliminarPsicologo(Integer id);
    Psicologo obtenerPsicologoPorId(Integer id);
    Psicologo obtenerPsicologoPorEmail(String email);
    List<Psicologo> obtenerTodosLosPsicologos();
}
