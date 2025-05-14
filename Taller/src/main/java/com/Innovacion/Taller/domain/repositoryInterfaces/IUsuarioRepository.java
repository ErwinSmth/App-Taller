package com.Innovacion.Taller.domain.repositoryInterfaces;

import com.Innovacion.Taller.persistence.entity.Usuario;

import java.util.Optional;

public interface IUsuarioRepository {

    Usuario save(Usuario user);
    Optional<Usuario> findByNameUser(String nameUser);

}
