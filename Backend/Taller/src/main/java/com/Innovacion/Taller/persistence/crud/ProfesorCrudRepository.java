package com.Innovacion.Taller.persistence.crud;

import com.Innovacion.Taller.persistence.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorCrudRepository extends JpaRepository<Profesor, Long> {
}
