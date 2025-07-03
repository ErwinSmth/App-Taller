package com.Innovacion.Taller.persistence.crud.taller;

import com.Innovacion.Taller.persistence.entity.taller.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InscripcionCrudRepository extends JpaRepository<Inscripcion, Long> {
    Optional<Inscripcion> findByTallerTallerIdAndEstudianteEstudianteId(Long tallerId, Long estudianteId);
    List<Inscripcion> findByEstudianteEstudianteId(Long estudianteId);
    List<Inscripcion> findByTallerTallerIdAndEstadoIgnoreCase(Long tallerId, String estado);
    void saveAll(List<Inscripcion> inscripciones);

}
