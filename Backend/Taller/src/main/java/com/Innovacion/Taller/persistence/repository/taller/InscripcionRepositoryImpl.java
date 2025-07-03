package com.Innovacion.Taller.persistence.repository.taller;

import com.Innovacion.Taller.domain.dto.taller.InscripcionDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.taller.IInscripcionRepository;
import com.Innovacion.Taller.persistence.crud.persona.EstudianteCrudRepository;
import com.Innovacion.Taller.persistence.crud.taller.InscripcionCrudRepository;
import com.Innovacion.Taller.persistence.crud.taller.TallerCrudRepository;
import com.Innovacion.Taller.persistence.entity.persona.Estudiante;
import com.Innovacion.Taller.persistence.entity.taller.Inscripcion;
import com.Innovacion.Taller.persistence.entity.taller.Taller;
import com.Innovacion.Taller.persistence.mapper.taller.InscripcionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InscripcionRepositoryImpl implements IInscripcionRepository {

    @Autowired
    private InscripcionCrudRepository crud;

    @Autowired
    private TallerCrudRepository tallerCrud;

    @Autowired
    private EstudianteCrudRepository estudianteCrud;

    @Autowired
    private InscripcionMapper mapper;


    @Override
    public Optional<InscripcionDto> findByTallerIdAndEstudianteId(Long tallerId, Long estudianteId) {
        return crud.findByTallerTallerIdAndEstudianteEstudianteId(tallerId, estudianteId)
                .map(mapper::toInscripcionDto);
    }

    @Override
    public InscripcionDto save(InscripcionDto dto) {
        Inscripcion entity = mapper.toInscripcion(dto);
        Taller taller = tallerCrud.findById(dto.getTallerId()).orElseThrow();
        Estudiante estudiante = estudianteCrud.findById(dto.getEstudianteId()).orElseThrow();
        entity.setTaller(taller);
        entity.setEstudiante(estudiante);
        entity.setEstado(dto.getEstado());
        Inscripcion saved = crud.save(entity);
        return mapper.toInscripcionDto(saved);
    }

    @Override
    public List<InscripcionDto> findByEstudianteId(Long estudianteId) {
        return crud.findByEstudianteEstudianteId(estudianteId)
                .stream()
                .map(mapper::toInscripcionDto)
                .toList();
    }

    @Override
    public List<InscripcionDto> findByTallerIdAndEstado(Long tallerId, String estado) {
        return crud.findByTallerTallerIdAndEstadoIgnoreCase(tallerId, estado)
                .stream()
                .map(mapper::toInscripcionDto)
                .toList();
    }

    @Override
    public void saveAll(List<InscripcionDto> inscripciones) {
        List<Inscripcion> entities = inscripciones.stream()
                .map(mapper::toInscripcion)
                .toList();
        crud.saveAll(entities);
    }
}
