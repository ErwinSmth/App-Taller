package com.Innovacion.Taller.web.controller;

import com.Innovacion.Taller.domain.dto.persona.EstudianteDto;
import com.Innovacion.Taller.domain.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<EstudianteDto> obtenerPorUsuarioId(@PathVariable Long userId) {
        return estudianteService.buscarPorUsuarioId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
