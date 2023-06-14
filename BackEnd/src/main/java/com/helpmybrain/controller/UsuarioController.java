package com.helpmybrain.controller;

import com.helpmybrain.entity.Usuario;
import com.helpmybrain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Usuario guardarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.guardarUsuario(usuario);
    }

    @PutMapping
    public Usuario actualizarUsuario(@RequestBody Usuario usuario) {
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

