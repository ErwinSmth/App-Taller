package com.Innovacion.Taller.domain.service;

import com.Innovacion.Taller.domain.dto.EspecialidadDto;
import com.Innovacion.Taller.domain.dto.ProfesorDto;
import com.Innovacion.Taller.domain.dto.ProfesorEspecialidadRequestDto;
import com.Innovacion.Taller.domain.dto.UsuarioDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.IEspecialidadRepository;
import com.Innovacion.Taller.domain.repositoryInterfaces.IProfesorRepository;
import com.Innovacion.Taller.domain.repositoryInterfaces.IUsuarioRepository;
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

}
