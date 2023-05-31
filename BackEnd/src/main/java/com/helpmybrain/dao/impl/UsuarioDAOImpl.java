package com.helpmybrain.dao.impl;

import com.helpmybrain.dao.UsuarioDAO;
import com.helpmybrain.entity.Usuario;
import com.helpmybrain.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioDAOImpl implements UsuarioDAO {
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    @Override
    public void eliminarUsuario(Integer id) {
        usuarioRepo.deleteById(id);
    }

    @Override
    public Usuario obtenerUsuarioPorId(Integer id) {
        return usuarioRepo.findById(id).orElse(null);
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return (List<Usuario>) usuarioRepo.findAll();
    }
    @Override
    public Usuario obtenerUsuarioPorEmail(String email) {
        return usuarioRepo.obtenerUsuarioPorEmail(email);
    }
}
