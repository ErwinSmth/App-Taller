package com.Innovacion.Taller.persistence.crud.persona;

import com.Innovacion.Taller.persistence.entity.persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaCrudRepository extends JpaRepository<Persona, Long> {

    Optional<Persona> findByEmail(String email);
    boolean existsByDNI(String Dni);
    boolean existsByEmail(String email);
    Optional<Persona> findByDNI(String dni);
}
