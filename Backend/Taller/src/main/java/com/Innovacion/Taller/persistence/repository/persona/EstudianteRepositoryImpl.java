package com.Innovacion.Taller.persistence.repository.persona;

import com.Innovacion.Taller.domain.dto.persona.EstudianteDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.persona.IEstudianteRepository;
import com.Innovacion.Taller.persistence.crud.persona.EstudianteCrudRepository;
import com.Innovacion.Taller.persistence.entity.persona.Estudiante;
import com.Innovacion.Taller.persistence.mapper.persona.EstudianteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EstudianteRepositoryImpl implements IEstudianteRepository {

    @Autowired
    private EstudianteCrudRepository estudCrud;
    @Autowired
    private EstudianteMapper mapper;


    @Override
    public EstudianteDto save(EstudianteDto estudianteDto) {
        Estudiante estudiante= mapper.toEstudiante(estudianteDto);
        return mapper.toEstudianteDto(estudCrud.save(estudiante));
    }

    @Override
    public Optional<EstudianteDto> findById(Long id) {
        return estudCrud.findById(id).map(Estudiante->mapper.toEstudianteDto(Estudiante));
    }

    @Override
    public Optional<EstudianteDto> findByUsuarioId(Long userId) {
        return estudCrud.findByUsuarioUserId(userId).map(Estudiante -> mapper.toEstudianteDto(Estudiante));
    }

    @Override
    public Optional<EstudianteDto> findByPersonaId(Long personaId) {
        return estudCrud.findByUsuarioPersonaPersonaId(personaId).map(Estudiante -> mapper.toEstudianteDto(Estudiante));
    }
}
