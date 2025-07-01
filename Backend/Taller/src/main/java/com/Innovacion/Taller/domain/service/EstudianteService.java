package com.Innovacion.Taller.domain.service;

import com.Innovacion.Taller.domain.dto.persona.EstudianteDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.persona.IEstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstudianteService {

    @Autowired
    private IEstudianteRepository estudianteRepo;

    public Optional<EstudianteDto> buscarPorUsuarioId(Long userId) {
        if (userId == null) throw new IllegalArgumentException("Id de usuario invalido");
        return estudianteRepo.findByUsuarioId(userId);

    }
}
