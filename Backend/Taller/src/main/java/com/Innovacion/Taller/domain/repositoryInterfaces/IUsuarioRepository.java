package com.Innovacion.Taller.domain.repositoryInterfaces;

import com.Innovacion.Taller.domain.dto.PersonaDto;
import com.Innovacion.Taller.domain.dto.UsuarioDto;
import com.Innovacion.Taller.persistence.entity.Usuario;

import java.util.Optional;

public interface IUsuarioRepository {

    UsuarioDto save(UsuarioDto user);
    Optional<UsuarioDto> findByNameUser(String nameUser);
    Optional<UsuarioDto> findByNameUserAndContraseña(String nameUser, String contraseña);

}
