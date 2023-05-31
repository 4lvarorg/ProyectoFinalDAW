package com.helpmybrain.service;

import com.helpmybrain.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario guardarUsuario(Usuario usuario);
    Usuario actualizarUsuario(Usuario usuario);
    void eliminarUsuario(Integer id);
    Usuario obtenerUsuarioPorId(Integer id);
    Usuario obtenerUsuarioPorEmail(String email);
    List<Usuario> obtenerTodosLosUsuarios();
}
