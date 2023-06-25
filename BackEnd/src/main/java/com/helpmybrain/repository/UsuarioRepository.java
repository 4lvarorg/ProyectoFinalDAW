package com.helpmybrain.repository;

import com.helpmybrain.entity.Usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    @Query(value ="SELECT u FROM Usuario u WHERE u.email = :email")
    Usuario obtenerUsuarioPorEmail(@Param("email") String email);

    @Query(value ="SELECT u FROM Usuario u WHERE u.email = :email AND u.password = :password")
    Usuario obtenerUsuarioPorEmailYPassword(@Param("email") String email,String password);
}
