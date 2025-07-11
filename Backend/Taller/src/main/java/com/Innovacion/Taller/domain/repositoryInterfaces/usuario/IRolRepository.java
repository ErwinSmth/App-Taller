package com.Innovacion.Taller.domain.repositoryInterfaces.usuario;

import com.Innovacion.Taller.domain.dto.usuario.RolesDto;

import java.util.List;
import java.util.Optional;

public interface IRolRepository {
    RolesDto save (RolesDto roles);
    Optional<RolesDto> findById(Long id);
    List<RolesDto> findAll();

}
