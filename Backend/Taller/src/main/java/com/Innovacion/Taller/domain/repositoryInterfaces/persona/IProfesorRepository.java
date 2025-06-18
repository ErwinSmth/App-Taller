package com.Innovacion.Taller.domain.repositoryInterfaces.persona;

import com.Innovacion.Taller.domain.dto.persona.ProfesorDto;


import java.util.List;
import java.util.Optional;

public interface IProfesorRepository {

    ProfesorDto save (ProfesorDto profesorDto);
    Optional<ProfesorDto> findById(Long id);
    Optional<ProfesorDto> findByUsuarioId(Long userId);
    List<ProfesorDto> findByEspecialidad(String especialidad);

}
