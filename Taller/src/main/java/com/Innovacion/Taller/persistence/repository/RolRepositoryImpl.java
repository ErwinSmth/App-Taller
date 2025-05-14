package com.Innovacion.Taller.persistence.repository;

import com.Innovacion.Taller.domain.repositoryInterfaces.IRolRepository;
import com.Innovacion.Taller.persistence.crud.RolCrudRepository;
import com.Innovacion.Taller.persistence.entity.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RolRepositoryImpl implements IRolRepository {

    @Autowired
    private RolCrudRepository rolCrud;

    @Override
    public Optional<Rol> findById(Long id) {
        return rolCrud.findById(id);
    }

    @Override
    public List<Rol> findAll() {
        return rolCrud.findAll();
    }
}
