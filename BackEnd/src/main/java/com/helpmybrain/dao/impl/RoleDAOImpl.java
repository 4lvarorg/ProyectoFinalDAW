package com.helpmybrain.dao.impl;

import com.helpmybrain.dao.RoleDAO;
import com.helpmybrain.entity.Role;
import com.helpmybrain.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleDAOImpl implements RoleDAO {
    @Autowired
    private RoleRepository roleRepo;

    @Override
    public Role guardarRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public Role actualizarRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void eliminarRole(Integer id) {
        roleRepo.deleteById(id);
    }

    @Override
    public Role obtenerRolePorId(Integer id) {
        return roleRepo.findById(id).orElse(null);
    }

    @Override
    public List<Role> obtenerTodosLosRoles() {
        return (List<Role>) roleRepo.findAll();
    }
}
