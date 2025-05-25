package com.Innovacion.Taller.persistence.repository;

import com.Innovacion.Taller.domain.dto.EstudianteDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.IEstudianteRepository;
import com.Innovacion.Taller.persistence.crud.EstudianteCrudRepository;
import com.Innovacion.Taller.persistence.entity.Estudiante;
import com.Innovacion.Taller.persistence.mapper.EstudianteMapper;
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
}
