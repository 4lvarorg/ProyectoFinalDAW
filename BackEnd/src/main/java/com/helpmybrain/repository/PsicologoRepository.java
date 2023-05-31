package com.helpmybrain.repository;

import com.helpmybrain.entity.Psicologo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PsicologoRepository extends CrudRepository<Psicologo, Integer> {
    @Query("SELECT p FROM Psicologo p WHERE p.email = :email")
    Psicologo obtenerPsicologoPorEmail(@Param("email") String email);
}
