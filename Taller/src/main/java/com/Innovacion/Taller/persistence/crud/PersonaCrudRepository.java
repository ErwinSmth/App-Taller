package com.Innovacion.Taller.persistence.crud;

import com.Innovacion.Taller.persistence.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaCrudRepository extends JpaRepository<Persona, Long> {

    Optional<Persona> findByEmail(String email);

}
