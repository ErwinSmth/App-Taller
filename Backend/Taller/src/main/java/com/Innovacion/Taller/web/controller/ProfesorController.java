package com.Innovacion.Taller.web.controller;

import com.Innovacion.Taller.domain.dto.ProfesorDto;
import com.Innovacion.Taller.domain.dto.ProfesorEspecialidadRequestDto;
import com.Innovacion.Taller.domain.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<ProfesorDto> obtenerPorUsuarioId(@PathVariable Long userId) {
        return profesorService.buscarPorUsuarioId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/especialidades")
    public ResponseEntity<Void> actualizarEspecialidadesYDescripcion(@RequestBody ProfesorEspecialidadRequestDto dto) {
        profesorService.actualizarEspecialidadesYDescripcion(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/crear")
    public ResponseEntity<ProfesorDto> crearProfesor(@RequestBody Map<String, Long> body){
        Long userId = body.get("userId");
        ProfesorDto profesor = profesorService.crearProfesor(userId);
        return ResponseEntity.ok(profesor);
    }


}
