package com.helpmybrain.dto;

import com.helpmybrain.entity.Role;
import java.io.Serializable;

public class RoleDTO implements Serializable {
    private Integer id;
    private String nombre;

    // getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // conversion methods
    public Role toEntity() {
        Role role = new Role();
        role.setId(this.id);
        role.setNombre(this.nombre);
        return role;
    }

    public static RoleDTO fromEntity(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setNombre(role.getNombre());
        return roleDTO;
    }
}
