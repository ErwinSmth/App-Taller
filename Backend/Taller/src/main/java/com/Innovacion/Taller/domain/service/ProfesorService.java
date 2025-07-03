package com.Innovacion.Taller.domain.service;

import com.Innovacion.Taller.domain.dto.EspecialidadDto;
import com.Innovacion.Taller.domain.dto.persona.PersonaDto;
import com.Innovacion.Taller.domain.dto.persona.ProfesorDto;
import com.Innovacion.Taller.domain.dto.persona.ProfesorEspecialidadRequestDto;
import com.Innovacion.Taller.domain.dto.usuario.RolesDto;
import com.Innovacion.Taller.domain.dto.usuario.UsuarioDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.IEspecialidadRepository;
import com.Innovacion.Taller.domain.repositoryInterfaces.persona.IEstudianteRepository;
import com.Innovacion.Taller.domain.repositoryInterfaces.persona.IPersonaRepository;
import com.Innovacion.Taller.domain.repositoryInterfaces.persona.IProfesorRepository;
import com.Innovacion.Taller.domain.repositoryInterfaces.usuario.IRolRepository;
import com.Innovacion.Taller.domain.repositoryInterfaces.usuario.IUsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfesorService {

    @Autowired
    private IProfesorRepository profesorRepo;

    @Autowired
    private IEspecialidadRepository especialidadRepo;

    @Autowired
    private IUsuarioRepository userRepo;

    @Autowired
    private IPersonaRepository personaRepo;

    @Autowired
    private IEstudianteRepository estudianteRepo;

    @Autowired
    private IRolRepository rolRepo;

    public Optional<ProfesorDto> buscarPorUsuarioId(Long userId) {
        if (userId == null) throw new IllegalArgumentException("Id de usuario inválido");
        return profesorRepo.findByUsuarioId(userId);
    }

    @Transactional
    public void actualizarEspecialidadesYDescripcion(ProfesorEspecialidadRequestDto dto) {

        ProfesorDto profesor = profesorRepo.findById(dto.getProfesorId())
                .orElseThrow(() -> new IllegalArgumentException("Profesor no encontrado"));

        // Actualiza la descripción
        profesor.setDescripcion(dto.getDescripcion());

        // Actualiza las especialidades
        if (dto.getEspecialidades() != null) {
            List<EspecialidadDto> especialidades = dto.getEspecialidades().stream()
                    .map(id -> especialidadRepo.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Especialidad no encontrada: " + id)))
                    .collect(Collectors.toList());
            profesor.setEspecialidades(especialidades);
        }
        profesorRepo.save(profesor);
    }

    public ProfesorDto crearProfesor(Long userId){
        if (profesorRepo.findByUsuarioId(userId).isPresent()) {
            throw new IllegalArgumentException("Ya existe un profesor para este usuario");
        }
        UsuarioDto userDto = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        ProfesorDto profesor = new ProfesorDto();
        profesor.setUserDto(userDto);
        return profesorRepo.save(profesor);
    }

    public Optional<PersonaDto> obtenerPersonaPorProfesorId(Long profesorId) {
        Optional<Long> personaIdOpt = profesorRepo.findPersonaIdByProfesorId(profesorId);
        return personaIdOpt.flatMap(personaRepo::findById);
    }

    @Transactional
    public ProfesorDto registrarProfesorDesdeUsuario(Long userId, String descripcion, List<Long> especialidadesIds) {
        if (userId == null) throw new IllegalArgumentException("userId requerido");
        if (profesorRepo.findByUsuarioId(userId).isPresent())
            throw new IllegalArgumentException("Ya es profesor");

        // Buscar usuario
        UsuarioDto usuario = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        ProfesorDto profesor = new ProfesorDto();
        profesor.setUserDto(usuario);
        profesor.setDescripcion(descripcion);

        // Asignar especialidades si corresponde
        if (especialidadesIds != null && !especialidadesIds.isEmpty()) {
            List<EspecialidadDto> especialidades = especialidadRepo.findAll().stream()
                    .filter(e -> especialidadesIds.contains(e.getEspecialidadId()))
                    .collect(Collectors.toList());
            profesor.setEspecialidades(especialidades);
        }

        ProfesorDto guardado = profesorRepo.save(profesor);

        // Asignar rol profesor si no lo tiene
        if (usuario.getRoles().stream().noneMatch(r -> r.getRolName().equalsIgnoreCase("profesor"))) {
            RolesDto rolProfesor = rolRepo.findAll().stream()
                    .filter(r -> r.getRolName().equalsIgnoreCase("profesor"))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Rol profesor no existe"));
            usuario.getRoles().add(rolProfesor);
            userRepo.save(usuario);
        }

        return guardado;
    }

}
