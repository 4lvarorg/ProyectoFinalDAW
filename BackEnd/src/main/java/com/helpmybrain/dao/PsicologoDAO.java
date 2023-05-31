package com.helpmybrain.dao;

import com.helpmybrain.entity.Psicologo;

import java.util.List;

public interface PsicologoDAO {
    Psicologo guardarPsicologo(Psicologo psicologo);
    Psicologo actualizarPsicologo(Psicologo psicologo);
    void eliminarPsicologo(Integer id);
    Psicologo obtenerPsicologoPorId(Integer id);
    List<Psicologo> obtenerTodosLosPsicologos();
    Psicologo obtenerPsicologoPorEmail(String email);
}
