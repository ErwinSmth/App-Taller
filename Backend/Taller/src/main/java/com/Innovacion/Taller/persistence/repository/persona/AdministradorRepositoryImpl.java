package com.Innovacion.Taller.persistence.repository.persona;

import com.Innovacion.Taller.domain.dto.persona.AdministradorDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.persona.IAdministradorRepository;
import com.Innovacion.Taller.persistence.crud.persona.AdministradorCrudRepository;
import com.Innovacion.Taller.persistence.entity.persona.Administrador;
import com.Innovacion.Taller.persistence.mapper.persona.AdministradorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AdministradorRepositoryImpl implements IAdministradorRepository {

    @Autowired
    private AdministradorCrudRepository adminCrud;

    @Autowired
    private AdministradorMapper mapper;

    @Override
    public AdministradorDto save(AdministradorDto admin) {
        Administrador entidad = mapper.toAdministrador(admin);
        return mapper.toAdministradorDto(adminCrud.save(entidad));
    }

    @Override
    public Optional<AdministradorDto> findById(Long id) {
        return adminCrud.findById(id).map(mapper::toAdministradorDto);
    }

    @Override
    public Optional<AdministradorDto> findByUsuarioId(Long userId) {
        return adminCrud.findByUsuarioUserId(userId).map(mapper::toAdministradorDto);
    }
}
