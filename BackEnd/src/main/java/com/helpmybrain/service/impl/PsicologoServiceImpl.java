package com.helpmybrain.service.impl;

import com.helpmybrain.dao.PsicologoDAO;
import com.helpmybrain.entity.Psicologo;
import com.helpmybrain.service.PsicologoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PsicologoServiceImpl implements PsicologoService {
    @Autowired
    private PsicologoDAO psicologoDAO;

    @Override
    public Psicologo guardarPsicologo(Psicologo psicologo) {
        return psicologoDAO.guardarPsicologo(psicologo);
    }

    @Override
    public Psicologo actualizarPsicologo(Psicologo psicologo) {
        return psicologoDAO.actualizarPsicologo(psicologo);
    }

    @Override
    public void eliminarPsicologo(Integer id) {
        psicologoDAO.eliminarPsicologo(id);
    }

    @Override
    public Psicologo obtenerPsicologoPorId(Integer id) {
        return psicologoDAO.obtenerPsicologoPorId(id);
    }

    @Override
    public Psicologo obtenerPsicologoPorEmail(String email) {
        return psicologoDAO.obtenerPsicologoPorEmail(email);
    }

    @Override
    public List<Psicologo> obtenerTodosLosPsicologos() {
        return psicologoDAO.obtenerTodosLosPsicologos();
    }
}
