package com.Innovacion.Taller.persistence.repository;

import com.Innovacion.Taller.domain.dto.OrganizadorDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.IOrganizadorRepository;
import com.Innovacion.Taller.persistence.crud.OrganizadorCrudRepository;
import com.Innovacion.Taller.persistence.entity.Organizador;
import com.Innovacion.Taller.persistence.mapper.OrganizadorMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class OrganizadorRepositoryImpl implements IOrganizadorRepository {

    @Autowired
    private OrganizadorCrudRepository organizadorCrud;

    @Autowired
    private OrganizadorMapper mapper;

    @Override
    public OrganizadorDto save(OrganizadorDto organizador) {
        Organizador org = mapper.toOrganizador(organizador);
        return mapper.toOrganizadorDto(organizadorCrud.save(org));
    }

    @Override
    public Optional<OrganizadorDto> findByUsuarioId(Long userId) {
        return organizadorCrud.findByUsuarioUserId(userId).map(mapper::toOrganizadorDto);
    }

    @Override
    public Optional<OrganizadorDto> findByRuc(String ruc) {
        return organizadorCrud.findByRuc(ruc).map(mapper::toOrganizadorDto);
    }
}
