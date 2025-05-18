package com.Innovacion.Taller.domain.repositoryInterfaces;

import com.Innovacion.Taller.domain.dto.EstudianteDto;

import java.util.Optional;

public interface IEstudianteRepository {

    EstudianteDto save(EstudianteDto estudiante);
    Optional<EstudianteDto>findById(Long id);
}
