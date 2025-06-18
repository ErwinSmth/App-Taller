package com.Innovacion.Taller.persistence.crud.persona;

import com.Innovacion.Taller.persistence.entity.persona.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfesorCrudRepository extends JpaRepository<Profesor,Long> {

    Optional<Profesor> findByUsuarioUserId(Long userId);
}
