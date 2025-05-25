package com.Innovacion.Taller.persistence.mapper;

import com.Innovacion.Taller.domain.dto.RolesDto;
import com.Innovacion.Taller.persistence.entity.Rol;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-17T21:26:58-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class RolMapperImpl implements RolMapper {

    @Override
    public RolesDto toRolesDto(Rol rol) {
        if ( rol == null ) {
            return null;
        }

        RolesDto rolesDto = new RolesDto();

        rolesDto.setRolId( rol.getRolId() );
        rolesDto.setRolName( rol.getRolName() );
        rolesDto.setDescripcion( rol.getDescripcion() );

        return rolesDto;
    }

    @Override
    public Rol toRol(RolesDto rolesDto) {
        if ( rolesDto == null ) {
            return null;
        }

        Rol rol = new Rol();

        rol.setRolId( rolesDto.getRolId() );
        rol.setRolName( rolesDto.getRolName() );
        rol.setDescripcion( rolesDto.getDescripcion() );

        return rol;
    }
}
