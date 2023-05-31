package com.helpmybrain.service.impl;

import com.helpmybrain.dao.RoleDAO;
import com.helpmybrain.entity.Role;
import com.helpmybrain.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDAO roleDAO;

    @Override
    public Role guardarRole(Role role) {
        return roleDAO.guardarRole(role);
    }

    @Override
    public Role actualizarRole(Role role) {
        return roleDAO.actualizarRole(role);
    }

    @Override
    public void eliminarRole(Integer id) {
        roleDAO.eliminarRole(id);
    }

    @Override
    public Role obtenerRolePorId(Integer id) {
        return roleDAO.obtenerRolePorId(id);
    }

    @Override
    public List<Role> obtenerTodosLosRoles() {
        return roleDAO.obtenerTodosLosRoles();
    }
}
