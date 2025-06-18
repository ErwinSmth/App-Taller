package com.Innovacion.Taller.persistence.crud.persona;

import com.Innovacion.Taller.persistence.entity.persona.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudianteCrudRepository extends JpaRepository <Estudiante, Long> {

    Optional<Estudiante> findByUsuarioUserId(Long userId);
    Optional<Estudiante> findByUsuarioPersonaPersonaId(Long personaId);

}
