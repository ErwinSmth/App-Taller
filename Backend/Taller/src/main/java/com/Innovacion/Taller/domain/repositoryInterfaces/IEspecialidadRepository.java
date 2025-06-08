package com.Innovacion.Taller.domain.repositoryInterfaces;

import com.Innovacion.Taller.domain.dto.EspecialidadDto;

import java.util.List;
import java.util.Optional;

public interface IEspecialidadRepository {
    EspecialidadDto save(EspecialidadDto especialidad);
    Optional<EspecialidadDto> findById(Long id);
    List<EspecialidadDto> findAll();
}
