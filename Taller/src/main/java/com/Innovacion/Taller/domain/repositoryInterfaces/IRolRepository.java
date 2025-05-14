package com.Innovacion.Taller.domain.repositoryInterfaces;

import com.Innovacion.Taller.persistence.entity.Rol;

import java.util.List;
import java.util.Optional;

public interface IRolRepository {

    Optional<Rol> findById(Long id);
    List<Rol> findAll();

}
