package com.helpmybrain.dao.impl;

import com.helpmybrain.dao.CitaDAO;
import com.helpmybrain.entity.Cita;
import com.helpmybrain.entity.Usuario;
import com.helpmybrain.entity.Psicologo;
import com.helpmybrain.repository.CitaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaDAOImpl implements CitaDAO {
    @Autowired
    private CitaRepository citaRepo;

    @Override
    public Cita guardarCita(Cita cita) {
        return citaRepo.save(cita);
    }

    @Override
    public Cita actualizarCita(Cita cita) {
        return citaRepo.save(cita);
    }

    @Override
    public void eliminarCita(Integer id) {
        citaRepo.deleteById(id);
    }

    @Override
    public Cita obtenerCitaPorId(Integer id) {
        return citaRepo.findById(id).orElse(null);
    }

    @Override
    public List<Cita> obtenerTodasLasCitas() {
        return (List<Cita>) citaRepo.findAll();
    }
    @Override
    public List<Cita> obtenerCitasPorUsuarioYPsicologo(Usuario usuario, Psicologo psicologo) {
        return citaRepo.obtenerCitasPorUsuarioYPsicologo(usuario, psicologo);
    }
}
