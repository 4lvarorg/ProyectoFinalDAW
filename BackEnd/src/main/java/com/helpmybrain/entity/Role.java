package com.helpmybrain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String nombre;

    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Usuario> usuarios;

    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
