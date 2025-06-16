package com.Innovacion.Taller.domain.repositoryInterfaces;

import com.Innovacion.Taller.domain.dto.PermisoDto;

import java.util.List;

public interface IPermisoRepository {

    List<PermisoDto> findByRoles_RolIdIn(List<Long> rolIds);

}
