package com.Innovacion.Taller.persistence.crud;

import com.Innovacion.Taller.persistence.entity.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecialidadCrudRepository extends JpaRepository<Especialidad, Long> {
}
