package com.Innovacion.Taller.domain.service;

import com.Innovacion.Taller.domain.dto.persona.*;
import com.Innovacion.Taller.domain.dto.usuario.PermisoDto;
import com.Innovacion.Taller.domain.dto.usuario.RolesDto;
import com.Innovacion.Taller.domain.dto.usuario.UsuarioDto;
import com.Innovacion.Taller.domain.dto.usuario.UsuarioRegistroDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.persona.*;
import com.Innovacion.Taller.domain.repositoryInterfaces.usuario.IPermisoRepository;
import com.Innovacion.Taller.domain.repositoryInterfaces.usuario.IRolRepository;
import com.Innovacion.Taller.domain.repositoryInterfaces.usuario.IUsuarioRepository;
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

    @Transactional
    public UsuarioDto editarUsuario(Long id, UsuarioRegistroDto userDto) {
        Optional<UsuarioDto> existente = userRepo.findById(id);
        if (existente.isEmpty()) throw new IllegalArgumentException("Usuario no encontrado");

        // Validar que el nuevo nombre de usuario no esté en uso por otro usuario
        Optional<UsuarioDto> usuarioConName = userRepo.findByNameUser(userDto.getNameUser());
        if (usuarioConName.isPresent() && !usuarioConName.get().getUserId().equals(id)) {
            throw new IllegalArgumentException("Ya existe un usuario con ese nombre de usuario");
        }

        // Validar que la persona no esté asociada a otro usuario
        Optional<UsuarioDto> usuarioConPersona = userRepo.findByPersonaId(userDto.getPersonaId());
        if (usuarioConPersona.isPresent() && !usuarioConPersona.get().getUserId().equals(id)) {
            throw new IllegalArgumentException("Ya existe un usuario para esa persona");
        }

        UsuarioDto usuario = existente.get();
        usuario.setNameUser(userDto.getNameUser());
        usuario.setContraseña(userDto.getContraseña());

        // Completa los roles con su rolId si solo llega el nombre
        List<RolesDto> rolesCompletos = new ArrayList<>();
        for (RolesDto rol : userDto.getRoles()) {
            if (rol.getRolId() == null && rol.getRolName() != null) {
                rolRepo.findAll().stream()
                        .filter(r -> r.getRolName().equals(rol.getRolName()))
                        .findFirst()
                        .ifPresent(r -> rol.setRolId(r.getRolId()));
            }
            rolesCompletos.add(rol);
        }
        usuario.setRoles(rolesCompletos);

        return userRepo.save(usuario);
    }

}
