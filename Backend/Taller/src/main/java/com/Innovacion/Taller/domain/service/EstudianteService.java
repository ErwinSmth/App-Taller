package com.Innovacion.Taller.domain.service;

import com.Innovacion.Taller.domain.dto.persona.EstudianteDto;
import com.Innovacion.Taller.domain.dto.usuario.RolesDto;
import com.Innovacion.Taller.domain.dto.usuario.UsuarioDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.persona.IEstudianteRepository;
import com.Innovacion.Taller.domain.repositoryInterfaces.usuario.IRolRepository;
import com.Innovacion.Taller.domain.repositoryInterfaces.usuario.IUsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstudianteService {

    @Autowired
    private IEstudianteRepository estudianteRepo;

    @Autowired
    private IUsuarioRepository userRepo;

    @Autowired
    private IRolRepository rolRepo;

    public Optional<EstudianteDto> buscarPorUsuarioId(Long userId) {
        if (userId == null) throw new IllegalArgumentException("Id de usuario invalido");
        return estudianteRepo.findByUsuarioId(userId);

    }

    @Transactional
    public EstudianteDto registrarEstudianteDesdeUsuario(Long userId) {
        if (userId == null) throw new IllegalArgumentException("userId requerido");
        if (estudianteRepo.findByUsuarioId(userId).isPresent())
            throw new IllegalArgumentException("Ya es estudiante");

        // Buscar usuario
        UsuarioDto usuario = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        EstudianteDto estudiante = new EstudianteDto();
        estudiante.setUsuarioDto(usuario);
        // Puedes setear otros campos si lo deseas

        EstudianteDto guardado = estudianteRepo.save(estudiante);

        // Asignar rol estudiante si no lo tiene
        if (usuario.getRoles().stream().noneMatch(r -> r.getRolName().equalsIgnoreCase("estudiante"))) {
            RolesDto rolEstudiante = rolRepo.findAll().stream()
                    .filter(r -> r.getRolName().equalsIgnoreCase("estudiante"))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Rol estudiante no existe"));
            usuario.getRoles().add(rolEstudiante);
            userRepo.save(usuario);
        }

        return guardado;
    }
}
