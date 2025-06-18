package com.Innovacion.Taller.domain.repositoryInterfaces.persona;

import com.Innovacion.Taller.domain.dto.persona.OrganizadorDto;

import java.util.Optional;

public interface IOrganizadorRepository {

    OrganizadorDto save(OrganizadorDto organizadorDto);
    Optional <OrganizadorDto> findByRuc (String ruc);
    Optional<OrganizadorDto> findById(Long id);
    Optional<OrganizadorDto> findByUsuarioId(Long userId);

}
