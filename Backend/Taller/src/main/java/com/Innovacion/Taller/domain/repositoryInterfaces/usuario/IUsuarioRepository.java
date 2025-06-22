package com.Innovacion.Taller.domain.repositoryInterfaces.usuario;

import com.Innovacion.Taller.domain.dto.usuario.UsuarioDto;

import java.util.Optional;

public interface IUsuarioRepository {

    UsuarioDto save(UsuarioDto user);
    Optional<UsuarioDto> findByNameUser(String nameUser);
    Optional<UsuarioDto> findByNameUserAndContraseña(String nameUser, String contraseña);
    Optional<UsuarioDto> findById(Long id);
    Optional<UsuarioDto> findByPersonaId(Long personaId);

}
