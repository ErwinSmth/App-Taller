package com.Innovacion.Taller.persistence.repository;

import com.Innovacion.Taller.domain.dto.OrganizadorDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.IOrganizadorRepository;
import com.Innovacion.Taller.persistence.crud.OrganizadorCrudRepository;
import com.Innovacion.Taller.persistence.entity.Organizador;
import com.Innovacion.Taller.persistence.mapper.OrganizadorMapper;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD:Taller/src/main/java/com/Innovacion/Taller/persistence/repository/OrganizadorRepositoryImpl.java

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
=======
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
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
    public Optional<OrganizadorDto> findById(Long id) {
        return organizadorCrud.findById(id).map(mapper::toOrganizadorDto);
    }

    @Override
    public Optional<OrganizadorDto> findByUsuarioId(Long userId) {
        return organizadorCrud.findByUsuarioUserId(userId).map(mapper::toOrganizadorDto);
>>>>>>> main:Backend/Taller/src/main/java/com/Innovacion/Taller/persistence/repository/OrganizadorRepositoryImpl.java
    }

    @Override
    public Optional<OrganizadorDto> findByRuc(String ruc) {
<<<<<<< HEAD:Taller/src/main/java/com/Innovacion/Taller/persistence/repository/OrganizadorRepositoryImpl.java
        return orgniCrud.findByRuc(ruc).map(Organizador->mapper.toOrganizadorDto(Organizador));
=======
        return organizadorCrud.findByRuc(ruc).map(mapper::toOrganizadorDto);
>>>>>>> main:Backend/Taller/src/main/java/com/Innovacion/Taller/persistence/repository/OrganizadorRepositoryImpl.java
    }
}
