package com.Innovacion.Taller.domain.service;

import com.Innovacion.Taller.domain.dto.taller.InscripcionDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.taller.IInscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InscripcionService {

    @Autowired
    private IInscripcionRepository repo;

    public InscripcionDto inscribirEstudiante(Long tallerId, Long estudianteId) {
        // Validar que no exista inscripción previa
        if (repo.findByTallerIdAndEstudianteId(tallerId, estudianteId).isPresent()) {
            throw new IllegalArgumentException("Ya está inscrito en este taller");
        }
        // Crear inscripción
        InscripcionDto dto = new InscripcionDto();
        dto.setTallerId(tallerId);
        dto.setEstudianteId(estudianteId);
        dto.setEstado("activo");
        return repo.save(dto);
    }

}
