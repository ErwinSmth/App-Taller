package com.Innovacion.Taller.domain.service;

import com.Innovacion.Taller.domain.dto.*;
import com.Innovacion.Taller.domain.repositoryInterfaces.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    private IAdministradorRepository adminRepo;

    @Autowired
    private IProfesorRepository profeRepo;

    @Autowired
    private IOrganizadorRepository organizadorRepo;

    @Autowired
    private IPermisoRepository permisoRepo;

    //Metodo a ser usado solo la primera vez que el usuario se registre
    @Transactional
    public UsuarioDto registrarUsuario(UsuarioRegistroDto userDto){

        //Validar datos de entrada
        if(userDto == null || userDto.getPersonaId() == null || userDto.getRoles() == null || userDto.getRoles().isEmpty()){
            throw new IllegalArgumentException("Los datos del usuario Incompletos");
        }

        //Buscar a la persona por su Id
        Optional<PersonaDto> personaDto = personRepo.findById(userDto.getPersonaId());
        if (personaDto.isEmpty()){
            throw new IllegalArgumentException("La persona no existe");
        }

        // Crear el UsuarioDto
        UsuarioDto usuario = new UsuarioDto();
        usuario.setPersonDto(personaDto.get());
        usuario.setNameUser(userDto.getNameUser());
        usuario.setContraseña(userDto.getContraseña());
        usuario.setRoles(userDto.getRoles());
        usuario.setActivo(true);

        // Guardar el usuario
        UsuarioDto userSave = userRepo.save(usuario);

        //Asignar roles al usuario
        List<RolesDto> roles = userDto.getRoles();
        for (RolesDto rol : roles){
            //Validar si el rol existe
            Optional<RolesDto> rolOptional = rolRepo.findById(rol.getRolId());
            if(rolOptional.isEmpty()){
                throw new IllegalArgumentException("El rol no existe");
            }
            //Obtener el rol
            RolesDto rolExistente = rolOptional.get();
            if(rol.getRolId() == 1){ //Estudiante
                userSave.getRoles().add(rolExistente);
                EstudianteDto estudiante = new EstudianteDto();
                estudiante.setUsuarioDto(userSave);
                estudianteRepo.save(estudiante);
            } else if (rol.getRolId() == 2){ //Profesor
                ProfesorDto profe = new ProfesorDto();
                profe.setUserDto(userSave);
                profeRepo.save(profe);

            } else if(rol.getRolId() == 3){ //Organizador
                OrganizadorDto organiz = new OrganizadorDto();
                organiz.setUserDto(userSave);
                organizadorRepo.save(organiz);

            } else if(rol.getRolId() == 4){ //Admin
                AdministradorDto admin = new AdministradorDto();
                admin.setUserDto(userSave);
                adminRepo.save(admin);
            }
        }
        return userSave;
        
    }

    public Optional<UsuarioDto> buscarPorUName(String userName){
        return userRepo.findByNameUser(userName);
    }

    public Optional<UsuarioDto> login(String nameUser, String contraseña){
        //validar datos
        if (nameUser == null || nameUser.isEmpty() || contraseña == null || contraseña.isEmpty()){
            throw  new IllegalArgumentException("Nombre de usuario y Contraseña incompletos");
        }

        System.out.println("Intentando logear con nameUser: " + nameUser + " y contraseña: " + contraseña);

        //Buscar al usuario
        Optional<UsuarioDto> usuario = userRepo.findByNameUserAndContraseña(nameUser, contraseña);

        System.out.println("Resultado de la búsqueda: " + (usuario.isPresent() ? "Usuario encontrado" : "Usuario no encontrado"));

        //Validar si el usuario existe y esta activo
        if(usuario.isPresent() && usuario.get().isActivo()){
            UsuarioDto user = usuario.get();
            Map<Long, List<PermisoDto>> permisosPorRol = new HashMap<>();
            for (RolesDto rol : user.getRoles()) {
                List<Long> rolIdList = List.of(rol.getRolId());
                List<PermisoDto> permisos = permisoRepo.findByRoles_RolIdIn(rolIdList);
                permisosPorRol.put(rol.getRolId(), permisos);
            }
            //Asignar los permisos al DTO
            user.setPermisos(permisosPorRol);
            return Optional.of(user);
        } else {
            throw new IllegalArgumentException("Credenciales invalidas o Usuario Inactivo");
        }
    }

}
