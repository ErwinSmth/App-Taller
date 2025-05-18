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
   private OrganizadorCrudRepository orgniCrud;
   @Autowired
   private OrganizadorMapper mapper;

    @Override
    public OrganizadorDto save(OrganizadorDto organizadorDto) {
        Organizador organizador=mapper.toOrganizador(organizadorDto);
        return mapper.toOrganizadorDto(orgniCrud.save(organizador));
    }

    @Override
    public Optional<OrganizadorDto> findByRuc(String ruc) {
        return orgniCrud.findByRuc(ruc).map(Organizador->mapper.toOrganizadorDto(Organizador));
    }
}
