package com.helpmybrain.dao.impl;

import com.helpmybrain.dao.PsicologoDAO;
import com.helpmybrain.entity.Psicologo;
import com.helpmybrain.repository.PsicologoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PsicologoDAOImpl implements PsicologoDAO {
    @Autowired
    private PsicologoRepository psicologoRepo;

    @Override
    public Psicologo guardarPsicologo(Psicologo psicologo) {
        return psicologoRepo.save(psicologo);
    }

    @Override
    public Psicologo actualizarPsicologo(Psicologo psicologo) {
        return psicologoRepo.save(psicologo);
    }

    @Override
    public void eliminarPsicologo(Integer id) {
        psicologoRepo.deleteById(id);
    }

    @Override
    public Psicologo obtenerPsicologoPorId(Integer id) {
        return psicologoRepo.findById(id).orElse(null);
    }

    @Override
    public List<Psicologo> obtenerTodosLosPsicologos() {
        return (List<Psicologo>) psicologoRepo.findAll();
    }
    @Override
    public Psicologo obtenerPsicologoPorEmail(String email) {
        return psicologoRepo.obtenerPsicologoPorEmail(email);
    }
}
