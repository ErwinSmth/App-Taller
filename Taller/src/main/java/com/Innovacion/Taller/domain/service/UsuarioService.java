package com.Innovacion.Taller.domain.service;

import com.Innovacion.Taller.domain.dto.PersonaDto;
import com.Innovacion.Taller.domain.dto.RolesDto;
import com.Innovacion.Taller.domain.dto.UsuarioDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.IPersonaRepository;
import com.Innovacion.Taller.domain.repositoryInterfaces.IRolRepository;
import com.Innovacion.Taller.domain.repositoryInterfaces.IUsuarioRepository;
import com.Innovacion.Taller.domain.repositoryInterfaces.IEstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository userRepo;

    @Autowired
    private IPersonaRepository personRepo;

    @Autowired
    private IRolRepository rolRepo;

    @Autowired
    private IEstudianteRepository estudianteRepo;
    
    
    public UsuarioDto registrarUsuario(UsuarioDto userDto){

        //Validar datos de entrada
        if(userDto == null || userDto.getPersonDto() == null || userDto.getRoles() == null || userDto.getRoles().isEmpty()){
            throw new IllegalArgumentException("Los datos del usuario Incompletos");
        }

        //Guardar los datos de la persona
        PersonaDto personSave = personRepo.save(userDto.getPersonDto());
        userDto.setPersonDto(personSave);

        //Guardar los datos del usuario
        UsuarioDto userSave = userRepo.save(userDto);

        //Asignar roles al usuario
        List<RolesDto> roles = userDto.getRoles();
        for (RolesDto rol : roles){
            //Validar si el rol existe
            Optional<RolesDto> rolOptional = rolRepo.findById(rol.getId());
            if(rolOptional.isEmpty()){
                throw new IllegalArgumentException("El rol no existe");
            }
            //Guardar el rol
            rolRepo.save(rol);
            if(rol.getId() == 1){ //Estudiante
                estudianteRepo.save(new EstudianteDto(null, null, userSave));
            } else if (rol.getId() == 2){ //Profesor
                
            } else { //Organizador

            }
        }
        return userSave;
        
    }

    public Optional<UsuarioDto> buscarPorUName(String userName){
        return userRepo.findByNameUser(userName);
    }

}
