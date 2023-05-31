package com.helpmybrain.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String nombre;

    @OneToMany(mappedBy = "role")
    private Set<Usuario> usuarios;

    @OneToMany(mappedBy = "role")
    private Set<Psicologo> psicologos;

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

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Set<Psicologo> getPsicologos() {
        return psicologos;
    }

    public void setPsicologos(Set<Psicologo> psicologos) {
        this.psicologos = psicologos;
    }
}
