package com.helpmybrain.controller;

import com.helpmybrain.entity.Psicologo;
import com.helpmybrain.entity.Usuario;
import com.helpmybrain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.helpmybrain.service.security.CustomUserDetailsService;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:8081")
public class LoginController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/loginUsuario")
    public UserDetails loginUsuario(@RequestBody Usuario usuario) {
        return userDetailsService.loadUserByUsername(usuario.getEmail());
    }

    @PostMapping("/loginPsicologo")
    public UserDetails loginPsicologo(@RequestBody Psicologo psicologo) {
        return userDetailsService.loadUserByUsername(psicologo.getEmail());
    }
}
