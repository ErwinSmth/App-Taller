package com.Innovacion.Taller.domain.repositoryInterfaces.taller;

import com.Innovacion.Taller.domain.dto.taller.InscripcionDto;

import java.util.List;
import java.util.Optional;

public interface IInscripcionRepository {
    Optional<InscripcionDto> findByTallerIdAndEstudianteId(Long tallerId, Long estudianteId);
    InscripcionDto save(InscripcionDto inscripcionDto);
    List<InscripcionDto> findByEstudianteId(Long estudianteId);
    List<InscripcionDto> findByTallerIdAndEstado(Long tallerId, String estado);
    void saveAll(List<InscripcionDto> inscripciones);

}
