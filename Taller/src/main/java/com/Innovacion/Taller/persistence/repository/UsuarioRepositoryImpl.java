package com.Innovacion.Taller.persistence.repository;

import com.Innovacion.Taller.domain.repositoryInterfaces.IUsuarioRepository;
import com.Innovacion.Taller.persistence.crud.UsuarioCrudRepository;
import com.Innovacion.Taller.persistence.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsuarioRepositoryImpl implements IUsuarioRepository {

    @Autowired
    private UsuarioCrudRepository userCrud;

    @Override
    public Usuario save(Usuario user) {
        return userCrud.save(user);
    }

    @Override
    public Optional<Usuario> findByNameUser(String nameUser) {
        return userCrud.findByNameUser(nameUser);
    }
}
