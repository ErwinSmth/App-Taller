package com.Innovacion.Taller.persistence.repository;

import com.Innovacion.Taller.domain.dto.RolesDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.IRolRepository;
import com.Innovacion.Taller.persistence.crud.RolCrudRepository;
import com.Innovacion.Taller.persistence.entity.Rol;
import com.Innovacion.Taller.persistence.mapper.RolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Optional;

@Repository
public class RolRepositoryImpl implements IRolRepository {

    @Autowired
    private RolCrudRepository rolCrud;
    @Autowired
    private RolMapper mapper;

    @Override
    public RolesDto save(RolesDto rolesDto) {
        Rol rol =mapper.toRol(rolesDto);
        return mapper.toRolesDto(rolCrud.save(rol));

    }

    @Override
    public Optional<RolesDto> findById(Long id) {
        return rolCrud.findById(id).map(Rol->mapper.toRolesDto(Rol));
    }

    //La clase List NO tiene un mettodo ".map(...) como Optional o Stream."
    @Override
    public List<RolesDto> findAll() {
        return rolCrud.findAll().
                stream().map(Rol->mapper.toRolesDto(Rol))
                .collect(Collectors.toList());
    }
}
//.collect(Collectors.toList()) convierte el stream transformado de nuevo en una List<RolesDto>.