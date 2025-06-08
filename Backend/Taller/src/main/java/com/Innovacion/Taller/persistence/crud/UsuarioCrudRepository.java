package com.Innovacion.Taller.persistence.crud;

import com.Innovacion.Taller.persistence.entity.Persona;
import com.Innovacion.Taller.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioCrudRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNameUser(String nameUser);
    Optional<Usuario> findByNameUserAndContraseña(String nameUser, String contraseña);

}
