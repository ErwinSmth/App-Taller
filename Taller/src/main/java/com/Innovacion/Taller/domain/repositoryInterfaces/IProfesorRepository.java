package com.Innovacion.Taller.domain.repositoryInterfaces;

import com.Innovacion.Taller.domain.dto.ProfesorDto;

import java.util.Optional;

public interface IProfesorRepository {

    ProfesorDto save (ProfesorDto profesorDto);
    Optional <ProfesorDto> findByEspecialidades (String especialidades);
}
