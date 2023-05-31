package com.helpmybrain.service.impl;

import com.helpmybrain.dao.UsuarioDAO;
import com.helpmybrain.entity.Usuario;
import com.helpmybrain.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioDAO.guardarUsuario(usuario);
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        return usuarioDAO.actualizarUsuario(usuario);
    }

    @Override
    public void eliminarUsuario(Integer id) {
        usuarioDAO.eliminarUsuario(id);
    }

    @Override
    public Usuario obtenerUsuarioPorId(Integer id) {
        return usuarioDAO.obtenerUsuarioPorId(id);
    }

    @Override
    public Usuario obtenerUsuarioPorEmail(String email) {
        return usuarioDAO.obtenerUsuarioPorEmail(email);
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioDAO.obtenerTodosLosUsuarios();
    }
}
