package com.helpmybrain.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helpmybrain.entity.Psicologo;
import com.helpmybrain.entity.Role;
import com.helpmybrain.service.PsicologoService;
import com.helpmybrain.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/psicologos")
@CrossOrigin(origins = "http://localhost:8081")
public class PsicologoController {

    @Autowired
    private PsicologoService psicologoService;
    @Autowired
    private RoleService roleService;

    @PostMapping
    public Psicologo guardarPsicologo(@RequestBody Psicologo psicologo) {
        Integer roleId = psicologo.getRole() != null ? psicologo.getRole().getId() : 3;
        Role role = roleService.obtenerRolePorId(roleId);
        if (role != null) {
            psicologo.setRole(role);
            return psicologoService.guardarPsicologo(psicologo);
        } else {
            throw new RuntimeException("Role not found for id :: " + roleId);
        }
    }

    @PutMapping("/{id}")
    public Psicologo actualizarPsicologo(@PathVariable Integer id,@RequestBody Psicologo psicologo) {
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
    public List<Psicologo> obtenerTodosLosPsicologos()  {


        return psicologoService.obtenerTodosLosPsicologos();

    }
}

