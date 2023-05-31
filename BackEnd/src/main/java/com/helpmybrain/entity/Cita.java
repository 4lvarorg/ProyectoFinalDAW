package com.helpmybrain.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "psicologo_id")
    private Psicologo psicologo;

    private LocalDate fechaReservada;
    private LocalTime horaReservada;
    private Double precioFinal;

    // getters y setters

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
}
