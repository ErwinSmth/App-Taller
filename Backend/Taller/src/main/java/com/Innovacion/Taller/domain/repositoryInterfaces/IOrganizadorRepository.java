package com.Innovacion.Taller.domain.repositoryInterfaces;

import com.Innovacion.Taller.domain.dto.OrganizadorDto;

import java.util.List;
import java.util.Optional;

public interface IOrganizadorRepository {

    OrganizadorDto save(OrganizadorDto organizadorDto);
    Optional <OrganizadorDto> findByRuc (String ruc);
    Optional<OrganizadorDto> findById(Long id);
    Optional<OrganizadorDto> findByUsuarioId(Long userId);

}
