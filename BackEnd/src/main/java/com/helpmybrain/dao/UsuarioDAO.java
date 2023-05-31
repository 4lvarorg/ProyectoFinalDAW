package com.helpmybrain.dao;

import com.helpmybrain.entity.Usuario;

import java.util.List;

public interface UsuarioDAO {
    Usuario guardarUsuario(Usuario usuario);
    Usuario actualizarUsuario(Usuario usuario);
    void eliminarUsuario(Integer id);
    Usuario obtenerUsuarioPorId(Integer id);
    List<Usuario> obtenerTodosLosUsuarios();
    Usuario obtenerUsuarioPorEmail(String email);
}
