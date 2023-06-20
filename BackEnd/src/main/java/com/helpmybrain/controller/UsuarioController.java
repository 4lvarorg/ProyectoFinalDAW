package com.helpmybrain.controller;

import com.helpmybrain.entity.Role;
import com.helpmybrain.entity.Usuario;
import com.helpmybrain.service.RoleService;
import com.helpmybrain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:8081")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RoleService roleService;
    @PostMapping
    public Usuario guardarUsuario(@RequestBody Usuario usuario) {
        Integer roleId = usuario.getRole() != null ? usuario.getRole().getId() : 2;
        Role role = roleService.obtenerRolePorId(roleId);
        if (role != null) {
            usuario.setRole(role);
            return usuarioService.guardarUsuario(usuario);
        } else {
            throw new RuntimeException("Role not found for id :: " + roleId);
        }
    }



    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        return usuarioService.actualizarUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Integer id) {
        usuarioService.eliminarUsuario(id);
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable Integer id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    @GetMapping("/email/{email}")
    public Usuario obtenerUsuarioPorEmail(@PathVariable String email) {
        return usuarioService.obtenerUsuarioPorEmail(email);
    }

    @GetMapping
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioService.obtenerTodosLosUsuarios();
    }
}

