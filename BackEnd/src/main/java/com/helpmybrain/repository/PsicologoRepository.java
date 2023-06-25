package com.helpmybrain.repository;

import com.helpmybrain.entity.Psicologo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PsicologoRepository extends CrudRepository<Psicologo, Integer> {
    @Query("SELECT p FROM Psicologo p WHERE p.email = :email")
    Psicologo obtenerPsicologoPorEmail(@Param("email") String email);

    @Query("SELECT p FROM Psicologo p ")
    List<Psicologo> obteneTodosPsicologo();
}
