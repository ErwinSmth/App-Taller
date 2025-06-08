package com.Innovacion.Taller.persistence.crud;

import com.Innovacion.Taller.persistence.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfesorCrudRepository extends JpaRepository<Profesor,Long> {

    Optional<Profesor> findByUsuarioUserId(Long userId);
}
