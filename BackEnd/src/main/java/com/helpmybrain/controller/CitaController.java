package com.helpmybrain.controller;

import com.helpmybrain.entity.Cita;
import com.helpmybrain.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @PostMapping
    public Cita guardarCita(@RequestBody Cita cita) {
        return citaService.guardarCita(cita);
    }

    @PutMapping
    public Cita actualizarCita(@RequestBody Cita cita) {
        return citaService.actualizarCita(cita);
    }

    @DeleteMapping("/{id}")
    public void eliminarCita(@PathVariable Integer id) {
        citaService.eliminarCita(id);
    }

    @GetMapping("/{id}")
    public Cita obtenerCitaPorId(@PathVariable Integer id) {
        return citaService.obtenerCitaPorId(id);
    }

    @GetMapping
    public List<Cita> obtenerTodasLasCitas() {
        return citaService.obtenerTodasLasCitas();
    }
}
