package com.Innovacion.Taller.domain.repositoryInterfaces;

import com.Innovacion.Taller.domain.dto.OrganizadorDto;

import java.util.Optional;

public interface IOrganizadorRepository {

    OrganizadorDto save(OrganizadorDto organizadorDto);
    Optional <OrganizadorDto> findByRuc (String ruc);
}
