package com.Innovacion.Taller.domain.repositoryInterfaces;

import com.Innovacion.Taller.domain.dto.RolesDto;
import com.Innovacion.Taller.persistence.entity.Rol;

import java.util.List;
import java.util.Optional;

public interface IRolRepository {
    RolesDto save (RolesDto roles);
    Optional<RolesDto> findById(Long id);
    List<RolesDto> findAll();

}
