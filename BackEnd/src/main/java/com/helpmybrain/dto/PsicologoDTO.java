package com.helpmybrain.dto;

import com.helpmybrain.entity.Psicologo;
import com.helpmybrain.entity.Role;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class PsicologoDTO implements Serializable {
    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private Integer telefono;
    private String direccion;
    private Integer codigoPostal;
    private Integer codiColegiado;
    private Double precioPorHora;
    private LocalDate fechaDisponible;
    private LocalTime horaDisponible;
    private Role role;

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Integer getCodiColegiado() {
        return codiColegiado;
    }

    public void setCodiColegiado(Integer codiColegiado) {
        this.codiColegiado = codiColegiado;
    }

    public Double getPrecioPorHora() {
        return precioPorHora;
    }

    public void setPrecioPorHora(Double precioPorHora) {
        this.precioPorHora = precioPorHora;
    }

    public LocalDate getFechaDisponible() {
        return fechaDisponible;
    }

    public void setFechaDisponible(LocalDate fechaDisponible) {
        this.fechaDisponible = fechaDisponible;
    }

    public LocalTime getHoraDisponible() {
        return horaDisponible;
    }

    public void setHoraDisponible(LocalTime horaDisponible) {
        this.horaDisponible = horaDisponible;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // conversion methods
    public Psicologo toEntity() {
        Psicologo psicologo = new Psicologo();
        psicologo.setId(this.id);
        psicologo.setNombre(this.nombre);
        psicologo.setApellido(this.apellido);
        psicologo.setEmail(this.email);
        psicologo.setPassword(this.password);
        psicologo.setTelefono(this.telefono);
        psicologo.setDireccion(this.direccion);
        psicologo.setCodigoPostal(this.codigoPostal);
        psicologo.setCodiColegiado(this.codiColegiado);
        psicologo.setPrecioPorHora(this.precioPorHora);
        psicologo.setFechaDisponible(this.fechaDisponible);
        psicologo.setHoraDisponible(this.horaDisponible);
        psicologo.setRole(this.role);
        return psicologo;
    }

    public static PsicologoDTO fromEntity(Psicologo psicologo) {
        PsicologoDTO psicologoDTO = new PsicologoDTO();
        psicologoDTO.setId(psicologo.getId());
        psicologoDTO.setNombre(psicologo.getNombre());
        psicologoDTO.setApellido(psicologo.getApellido());
        psicologoDTO.setEmail(psicologo.getEmail());
        psicologoDTO.setPassword(psicologo.getPassword());
        psicologoDTO.setTelefono(psicologo.getTelefono());
        psicologoDTO.setDireccion(psicologo.getDireccion());
        psicologoDTO.setCodigoPostal(psicologo.getCodigoPostal());
        psicologoDTO.setCodiColegiado(psicologo.getCodiColegiado());
        psicologoDTO.setPrecioPorHora(psicologo.getPrecioPorHora());
        psicologoDTO.setFechaDisponible(psicologo.getFechaDisponible());
        psicologoDTO.setHoraDisponible(psicologo.getHoraDisponible());
        psicologoDTO.setRole(psicologo.getRole());
        return psicologoDTO;
    }
}

