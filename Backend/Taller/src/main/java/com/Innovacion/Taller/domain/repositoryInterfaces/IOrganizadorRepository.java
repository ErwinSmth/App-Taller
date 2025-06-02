package com.Innovacion.Taller.domain.repositoryInterfaces;

import com.Innovacion.Taller.domain.dto.OrganizadorDto;

import java.util.List;
import java.util.Optional;

public interface IOrganizadorRepository {

    OrganizadorDto save(OrganizadorDto organizador);
    Optional<OrganizadorDto> findByUsuarioId(Long userId);
    Optional<OrganizadorDto> findByRuc(String ruc);

}
