package com.Innovacion.Taller.persistence.repository.persona;

import com.Innovacion.Taller.domain.dto.persona.OrganizadorDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.persona.IOrganizadorRepository;
import com.Innovacion.Taller.persistence.crud.persona.OrganizadorCrudRepository;
import com.Innovacion.Taller.persistence.entity.persona.Organizador;
import com.Innovacion.Taller.persistence.mapper.persona.OrganizadorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrganizadorRepositoryImpl implements IOrganizadorRepository {
   @Autowired
   private OrganizadorCrudRepository organizadorCrud;
   @Autowired
   private OrganizadorMapper mapper;

    @Override
    public OrganizadorDto save(OrganizadorDto organizadorDto) {
        Organizador organizador = mapper.toOrganizador(organizadorDto);
        return mapper.toOrganizadorDto(organizadorCrud.save(organizador));

    }

    @Override
    public Optional<OrganizadorDto> findByRuc(String ruc) {
        return organizadorCrud.findByRuc(ruc).map(Organizador->mapper.toOrganizadorDto(Organizador));
    }

    @Override
    public Optional<OrganizadorDto> findById(Long id) {
        return organizadorCrud.findById(id).map(mapper::toOrganizadorDto);
    }

    @Override
    public Optional<OrganizadorDto> findByUsuarioId(Long userId) {
        return organizadorCrud.findByUsuarioUserId(userId).map(mapper::toOrganizadorDto);
    }


}
