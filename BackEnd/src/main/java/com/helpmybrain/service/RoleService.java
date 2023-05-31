package com.helpmybrain.service;

import com.helpmybrain.entity.Role;

import java.util.List;

public interface RoleService {
    Role guardarRole(Role role);
    Role actualizarRole(Role role);
    void eliminarRole(Integer id);
    Role obtenerRolePorId(Integer id);
    List<Role> obtenerTodosLosRoles();
}
