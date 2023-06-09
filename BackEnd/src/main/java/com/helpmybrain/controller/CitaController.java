package com.helpmybrain.controller;

import com.helpmybrain.entity.Cita;
import com.helpmybrain.entity.Psicologo;
import com.helpmybrain.entity.Usuario;
import com.helpmybrain.service.CitaService;
import com.helpmybrain.service.PsicologoService;
import com.helpmybrain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/citas")
@CrossOrigin(origins = "http://localhost:8081")
public class CitaController {

    @Autowired
    private CitaService citaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PsicologoService psicologoService;
    @PostMapping
    public Cita guardarCita(@RequestBody Map<String, Object> payload) {
        Integer usuarioId = (Integer) payload.get("usuario_id");
        Integer psicologoId = (Integer) payload.get("psicologo_id");

        Usuario usuario = usuarioService.obtenerUsuarioPorId(usuarioId);
        Psicologo psicologo = psicologoService.obtenerPsicologoPorId(psicologoId);

        if (usuario != null && psicologo != null) {
            Cita cita = new Cita();
            cita.setUsuario(usuario);
            cita.setPsicologo(psicologo);

            Double precioFinal = null;
            if (payload.get("precioFinal") != null && !((String) payload.get("precioFinal")).isEmpty()) {
                precioFinal = Double.valueOf((String) payload.get("precioFinal"));
            }
            if (precioFinal == null) {
                cita.setPrecioFinal(psicologo.getPrecioPorHora());
            } else {
                cita.setPrecioFinal(precioFinal);
            }

            String fechaReservada = (String) payload.get("fechaReservada");
            if (fechaReservada == null || fechaReservada.isEmpty()) {
                cita.setFechaReservada(psicologo.getFechaDisponible());
            } else {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                cita.setFechaReservada(LocalDate.parse(fechaReservada, dateFormatter));
            }

            String horaReservada = (String) payload.get("horaReservada");
            if (horaReservada == null || horaReservada.isEmpty()) {
                cita.setHoraReservada(psicologo.getHoraDisponible());
            } else {
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                cita.setHoraReservada(LocalTime.parse(horaReservada, timeFormatter));
            }

            return citaService.guardarCita(cita);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario o Psicologo no encontrado");
        }
    }


    @PutMapping("/{id}")
    public Cita actualizarCita(@PathVariable Integer id, @RequestBody Map<String, String> payload) {
        Integer usuarioId = Integer.parseInt(payload.get("usuario_id"));
        Integer psicologoId = Integer.parseInt(payload.get("psicologo_id"));

        Usuario usuario = usuarioService.obtenerUsuarioPorId(usuarioId);
        Psicologo psicologo = psicologoService.obtenerPsicologoPorId(psicologoId);

        if (usuario != null && psicologo != null) {
            Cita cita = citaService.obtenerCitaPorId(id);
            if (cita != null) {
                cita.setUsuario(usuario);
                cita.setPsicologo(psicologo);

                // Aquí es donde debes actualizar los campos de la cita con los datos del payload
                if (payload.containsKey("precioFinal")) {
                    cita.setPrecioFinal(Double.valueOf(payload.get("precioFinal")));
                }
                if (payload.containsKey("fechaReservada")) {
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    cita.setFechaReservada(LocalDate.parse(payload.get("fechaReservada"), dateFormatter));
                }
                if (payload.containsKey("horaReservada")) {
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                    cita.setHoraReservada(LocalTime.parse(payload.get("horaReservada"), timeFormatter));
                }

                return citaService.actualizarCita(cita);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cita no encontrada");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario o Psicologo no encontrado");
        }
    }



    @DeleteMapping("/{id}")
    public void eliminarCita(@PathVariable Integer id) {
        citaService.eliminarCita(id);
    }

    @GetMapping("/{id}")
    public Cita obtenerCitaPorId(@PathVariable Integer id) {
        return citaService.obtenerCitaPorId(id);
    }

    @GetMapping
    public List<Cita> obtenerTodasLasCitas() {
        return citaService.obtenerTodasLasCitas();
    }

    @GetMapping("email/{email}")
    public List<Cita> obtenerCitaPorEmail(@PathVariable String email) {
        return citaService.obtenerCitasPorEmailUsuario(email);
    }
}
