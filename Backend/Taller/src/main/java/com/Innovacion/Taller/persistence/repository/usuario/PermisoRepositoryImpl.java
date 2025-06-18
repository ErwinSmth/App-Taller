package com.Innovacion.Taller.persistence.repository.usuario;

import com.Innovacion.Taller.domain.dto.usuario.PermisoDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.usuario.IPermisoRepository;
import com.Innovacion.Taller.persistence.crud.usuario.PermisoCrudRepository;
import com.Innovacion.Taller.persistence.mapper.usuario.PermisoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PermisoRepositoryImpl implements IPermisoRepository {

    @Autowired
    private PermisoCrudRepository crud;

    @Autowired
    private PermisoMapper mapper;

    @Override
    public List<PermisoDto> findByRoles_RolIdIn(List<Long> rolIds) {
        return crud.findByRoles_RolIdIn(rolIds).stream()
                .map(mapper::toPermisoDto)
                .collect(Collectors.toList());
    }
}
