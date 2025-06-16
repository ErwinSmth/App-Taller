package com.Innovacion.Taller.persistence.repository;

import com.Innovacion.Taller.domain.dto.PermisoDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.IPermisoRepository;
import com.Innovacion.Taller.persistence.crud.PermisoCrudRepository;
import com.Innovacion.Taller.persistence.entity.Permiso;
import com.Innovacion.Taller.persistence.mapper.PermisoMapper;
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
