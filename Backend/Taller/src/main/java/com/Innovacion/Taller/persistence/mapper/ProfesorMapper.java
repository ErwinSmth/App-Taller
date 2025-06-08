package com.Innovacion.Taller.persistence.mapper;

<<<<<<< HEAD:Taller/src/main/java/com/Innovacion/Taller/persistence/mapper/ProfesorMapper.java

=======
>>>>>>> main:Backend/Taller/src/main/java/com/Innovacion/Taller/persistence/mapper/ProfesorMapper.java
import com.Innovacion.Taller.domain.dto.ProfesorDto;
import com.Innovacion.Taller.persistence.entity.Profesor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

<<<<<<< HEAD:Taller/src/main/java/com/Innovacion/Taller/persistence/mapper/ProfesorMapper.java
import java.util.List;

@Mapper(componentModel = "spring",uses = {UsuarioMapper.class})

public interface ProfesorMapper {
    @Mapping(source = "usuario", target = "usuarioDto" )
    ProfesorDto toProfesorDto (Profesor profesor);
    List <ProfesorDto>toProfesorDtoList(List<Profesor>profesors);

    @InheritInverseConfiguration
    @Mapping(source = "usuarioDto", target = "usuario")
    Profesor toProfesor(ProfesorDto profesorDto);
    List <Profesor> toProfesorList(List<ProfesorDto>profesorDtos);

}

=======
@Mapper(componentModel = "spring", uses = {UsuarioMapper.class})
public interface ProfesorMapper {

    @Mapping(source = "usuario", target = "userDto")
    ProfesorDto toProfesorDto(Profesor profesor);

    @InheritInverseConfiguration
    @Mapping(source = "userDto", target = "usuario")
    Profesor toProfesor(ProfesorDto profesorDto);

}
>>>>>>> main:Backend/Taller/src/main/java/com/Innovacion/Taller/persistence/mapper/ProfesorMapper.java
