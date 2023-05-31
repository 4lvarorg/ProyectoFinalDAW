package com.helpmybrain.repository;

import com.helpmybrain.entity.Cita;
import com.helpmybrain.entity.Psicologo;
import com.helpmybrain.entity.Usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CitaRepository extends CrudRepository<Cita, Integer> {
    @Query("SELECT c FROM Cita c WHERE c.usuario = :usuario AND c.psicologo = :psicologo")
    List<Cita> obtenerCitasPorUsuarioYPsicologo(@Param("usuario") Usuario usuario, @Param("psicologo") Psicologo psicologo);

    @Query("SELECT c FROM Cita c WHERE c.usuario = :usuario")
    List<Cita> obtenerCitasPorUsuario(@Param("usuario") Usuario usuario);

    @Query("SELECT c FROM Cita c WHERE c.psicologo = :psicologo")
    List<Cita> obtenerCitasPorPsicologo(@Param("psicologo") Psicologo psicologo);
}
