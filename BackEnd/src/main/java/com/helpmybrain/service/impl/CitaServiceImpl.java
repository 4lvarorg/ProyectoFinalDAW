package com.helpmybrain.service.impl;

import com.helpmybrain.dao.CitaDAO;
import com.helpmybrain.entity.Cita;
import com.helpmybrain.service.CitaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {
    @Autowired
    private CitaDAO citaDAO;

    @Override
    public Cita guardarCita(Cita cita) {
        List<Cita> citasAnteriores = citaDAO.obtenerCitasPorUsuarioYPsicologo(cita.getUsuario(), cita.getPsicologo());
        int numeroDeCitas = citasAnteriores.size();
        double precioPorHora = cita.getPsicologo().getPrecioPorHora();
        if (numeroDeCitas >= 5 && numeroDeCitas < 10) {
            // Aplicar un descuento del 20% en la próxima cita
            cita.setPrecioFinal(precioPorHora * 0.8);
        } else if (numeroDeCitas >= 10) {
            // Hacer la próxima cita gratis
            cita.setPrecioFinal(0.0);
        } else {
            // Si no hay descuentos, el precio final es el precio por hora del psicólogo
            cita.setPrecioFinal(precioPorHora);
        }
        return citaDAO.guardarCita(cita);
    }

    @Override
    public Cita actualizarCita(Cita cita) {
        return citaDAO.actualizarCita(cita);
    }

    @Override
    public void eliminarCita(Integer id) {
        citaDAO.eliminarCita(id);
    }

    @Override
    public Cita obtenerCitaPorId(Integer id) {
        return citaDAO.obtenerCitaPorId(id);
    }

    @Override
    public List<Cita> obtenerTodasLasCitas() {
        return citaDAO.obtenerTodasLasCitas();
    }
}

