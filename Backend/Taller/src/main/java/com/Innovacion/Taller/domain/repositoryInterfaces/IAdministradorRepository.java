package com.Innovacion.Taller.domain.repositoryInterfaces;

import com.Innovacion.Taller.domain.dto.AdministradorDto;

import java.util.Optional;

public interface IAdministradorRepository {

    AdministradorDto save(AdministradorDto admin);
    Optional<AdministradorDto> findById(Long id);
    Optional<AdministradorDto> findByUsuarioId(Long userId);

}
