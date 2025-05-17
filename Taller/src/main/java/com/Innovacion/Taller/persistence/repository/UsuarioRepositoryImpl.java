package com.Innovacion.Taller.persistence.repository;

import com.Innovacion.Taller.domain.dto.UsuarioDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.IUsuarioRepository;
import com.Innovacion.Taller.persistence.crud.UsuarioCrudRepository;
import com.Innovacion.Taller.persistence.entity.Usuario;
import com.Innovacion.Taller.persistence.mapper.UsuarioMapper;
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
    public UsuarioDto save(UsuarioDto userDto)
    {
        Usuario usuario=mapper.toUsuario(userDto);
        return mapper.toUsuarioDto(userCrud.save(usuario));
    }

    @Override
    public Optional<UsuarioDto> findByNameUser(String name) {

        return userCrud.findByNameUser(name).map(Usuario->mapper.toUsuarioDto(Usuario));
    }
}
    //si lees esto te gusta la ganpi