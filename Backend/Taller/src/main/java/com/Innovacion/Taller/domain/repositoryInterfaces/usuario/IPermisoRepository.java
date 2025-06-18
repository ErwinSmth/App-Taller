package com.Innovacion.Taller.domain.repositoryInterfaces.usuario;

import com.Innovacion.Taller.domain.dto.usuario.PermisoDto;

import java.util.List;

public interface IPermisoRepository {

    List<PermisoDto> findByRoles_RolIdIn(List<Long> rolIds);

}
