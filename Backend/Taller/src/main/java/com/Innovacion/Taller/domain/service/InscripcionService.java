package com.Innovacion.Taller.domain.service;

import com.Innovacion.Taller.domain.dto.taller.InscripcionDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.taller.IInscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<InscripcionDto> listarInscripcionesPorEstudiante(Long estudianteId) {
        return repo.findByEstudianteId(estudianteId);
    }

    public int completarInscripcionesPorTaller(Long tallerId) {
        List<InscripcionDto> activas = repo.findByTallerIdAndEstado(tallerId, "ACTIVO");
        if (activas.isEmpty()) return 0;
        for (InscripcionDto insc : activas) {
            insc.setEstado("COMPLETADO");
        }
        repo.saveAll(activas);
        return activas.size();
    }

}
