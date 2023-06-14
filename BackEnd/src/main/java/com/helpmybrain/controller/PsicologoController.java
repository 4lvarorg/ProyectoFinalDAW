package com.helpmybrain.controller;

import com.helpmybrain.entity.Psicologo;
import com.helpmybrain.service.PsicologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/psicologos")
public class PsicologoController {

    @Autowired
    private PsicologoService psicologoService;

    @PostMapping
    public Psicologo guardarPsicologo(@RequestBody Psicologo psicologo) {
        return psicologoService.guardarPsicologo(psicologo);
    }

    @PutMapping
    public Psicologo actualizarPsicologo(@RequestBody Psicologo psicologo) {
        return psicologoService.actualizarPsicologo(psicologo);
    }

    @DeleteMapping("/{id}")
    public void eliminarPsicologo(@PathVariable Integer id) {
        psicologoService.eliminarPsicologo(id);
    }

    @GetMapping("/{id}")
    public Psicologo obtenerPsicologoPorId(@PathVariable Integer id) {
        return psicologoService.obtenerPsicologoPorId(id);
    }

    @GetMapping("/email/{email}")
    public Psicologo obtenerPsicologoPorEmail(@PathVariable String email) {
        return psicologoService.obtenerPsicologoPorEmail(email);
    }

    @GetMapping
    public List<Psicologo> obtenerTodosLosPsicologos() {
        return psicologoService.obtenerTodosLosPsicologos();
    }
}

