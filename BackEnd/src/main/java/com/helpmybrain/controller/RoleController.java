package com.helpmybrain.controller;

import com.helpmybrain.entity.Role;
import com.helpmybrain.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public Role guardarRole(@RequestBody Role role) {
        return roleService.guardarRole(role);
    }

    @PutMapping
    public Role actualizarRole(@RequestBody Role role) {
        return roleService.actualizarRole(role);
    }

    @DeleteMapping("/{id}")
    public void eliminarRole(@PathVariable Integer id) {
        roleService.eliminarRole(id);
    }

    @GetMapping("/{id}")
    public Role obtenerRolePorId(@PathVariable Integer id) {
        return roleService.obtenerRolePorId(id);
    }

    @GetMapping
    public List<Role> obtenerTodosLosRoles() {
        return roleService.obtenerTodosLosRoles();
    }
}
