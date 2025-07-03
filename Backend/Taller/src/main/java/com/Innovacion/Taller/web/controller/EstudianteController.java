package com.Innovacion.Taller.web.controller;

import com.Innovacion.Taller.domain.dto.persona.EstudianteDto;
import com.Innovacion.Taller.domain.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

        @PostMapping("/registrar-desde-usuario")
        public ResponseEntity<?> registrarEstudianteDesdeUsuario(@RequestBody Map<String, Long> body) {
            Long userId = body.get("userId");
            try {
                EstudianteDto estudiante = estudianteService.registrarEstudianteDesdeUsuario(userId);
                return ResponseEntity.ok(estudiante);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }
}


