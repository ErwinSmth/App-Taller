package com.Innovacion.Taller.domain.repositoryInterfaces.persona;

import com.Innovacion.Taller.domain.dto.persona.AdministradorDto;

import java.util.Optional;

public interface IAdministradorRepository {

    AdministradorDto save(AdministradorDto admin);
    Optional<AdministradorDto> findById(Long id);
    Optional<AdministradorDto> findByUsuarioId(Long userId);

}
