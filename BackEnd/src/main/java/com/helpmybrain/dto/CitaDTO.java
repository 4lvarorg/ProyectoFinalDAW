package com.helpmybrain.dto;

import com.helpmybrain.entity.Cita;
import com.helpmybrain.entity.Psicologo;
import com.helpmybrain.entity.Usuario;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class CitaDTO implements Serializable {
    private Integer id;
    private Usuario usuario;
    private Psicologo psicologo;
    private LocalDate fechaReservada;
    private LocalTime horaReservada;
    private Double precioFinal;

    // getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Psicologo getPsicologo() {
        return psicologo;
    }

    public void setPsicologo(Psicologo psicologo) {
        this.psicologo = psicologo;
    }

    public LocalDate getFechaReservada() {
        return fechaReservada;
    }

    public void setFechaReservada(LocalDate fechaReservada) {
        this.fechaReservada = fechaReservada;
    }

    public LocalTime getHoraReservada() {
        return horaReservada;
    }

    public void setHoraReservada(LocalTime horaReservada) {
        this.horaReservada = horaReservada;
    }

    public Double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(Double precioFinal) {
        this.precioFinal = precioFinal;
    }

    // conversion methods
    public Cita toEntity() {
        Cita cita = new Cita();
        cita.setId(this.id);
        cita.setUsuario(this.usuario);
        cita.setPsicologo(this.psicologo);
        cita.setFechaReservada(this.fechaReservada);
        cita.setHoraReservada(this.horaReservada);
        cita.setPrecioFinal(this.precioFinal);
        return cita;
    }

    public static CitaDTO fromEntity(Cita cita) {
        CitaDTO citaDTO = new CitaDTO();
        citaDTO.setId(cita.getId());
        citaDTO.setUsuario(cita.getUsuario());
        citaDTO.setPsicologo(cita.getPsicologo());
        citaDTO.setFechaReservada(cita.getFechaReservada());
        citaDTO.setHoraReservada(cita.getHoraReservada());
        citaDTO.setPrecioFinal(cita.getPrecioFinal());
        return citaDTO;
    }
}
