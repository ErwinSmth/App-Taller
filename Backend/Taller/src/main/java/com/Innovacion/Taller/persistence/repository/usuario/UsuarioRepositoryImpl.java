package com.Innovacion.Taller.persistence.repository.usuario;

import com.Innovacion.Taller.domain.dto.usuario.UsuarioDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.usuario.IUsuarioRepository;
import com.Innovacion.Taller.persistence.crud.usuario.UsuarioCrudRepository;
import com.Innovacion.Taller.persistence.entity.usuario.Usuario;
import com.Innovacion.Taller.persistence.mapper.usuario.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsuarioRepositoryImpl implements IUsuarioRepository {

    @Autowired
    private UsuarioCrudRepository userCrud;
    @Autowired
    private UsuarioMapper mapper;

    @Override
    public UsuarioDto save(UsuarioDto userDto) //Para registrar el usuario
    {
        Usuario usuario=mapper.toUsuario(userDto);

        //para establecer a que persona pertence el usuario
        if (usuario.getPersona() != null){
            usuario.getPersona().setUsuario(usuario);
        }

        Usuario userSaved = userCrud.save(usuario);

        return mapper.toUsuarioDto(userSaved);
    }

    @Override
    public Optional<UsuarioDto> findByNameUser(String name) {

        return userCrud.findByNameUser(name).map(Usuario->mapper.toUsuarioDto(Usuario));
    }

    @Override
    public Optional<UsuarioDto> findByNameUserAndContraseña(String nameUser, String contraseña) {
        return userCrud.findByNameUserAndContraseña(nameUser, contraseña). map(usuario -> mapper.toUsuarioDto(usuario));
    }

    @Override
    public Optional<UsuarioDto> findById(Long id) {
        return userCrud.findById(id).map(usuario -> mapper.toUsuarioDto(usuario));
    }
}
