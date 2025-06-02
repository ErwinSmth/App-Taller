package com.Innovacion.Taller.domain.repositoryInterfaces;

import com.Innovacion.Taller.domain.dto.ProfesorDto;

import java.util.List;
import java.util.Optional;

public interface IProfesorRepository {

    ProfesorDto save(ProfesorDto profesor);
    Optional<ProfesorDto> findById(Long id);
    Optional<ProfesorDto> findByUsuarioId(Long userId);
    List<ProfesorDto> findByEspecialidad(String especialidad);

}
