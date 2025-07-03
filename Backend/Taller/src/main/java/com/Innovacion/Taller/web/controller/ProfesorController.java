package com.Innovacion.Taller.web.controller;

import com.Innovacion.Taller.domain.dto.persona.PersonaDto;
import com.Innovacion.Taller.domain.dto.persona.ProfesorDto;
import com.Innovacion.Taller.domain.dto.persona.ProfesorEspecialidadRequestDto;
import com.Innovacion.Taller.domain.dto.persona.ProfesorRegistroDto;
import com.Innovacion.Taller.domain.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{profesorId}/persona/detalle")
    public ResponseEntity<PersonaDto> obtenerPersonaPorProfesorId(@PathVariable Long profesorId) {
        return profesorService.obtenerPersonaPorProfesorId(profesorId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/registrar-desde-usuario")
    public ResponseEntity<?> registrarProfesorDesdeUsuario(@RequestBody ProfesorRegistroDto dto) {
        try {
            ProfesorDto profesor = profesorService.registrarProfesorDesdeUsuario(
                    dto.getUserId(), dto.getDescripcion(), dto.getEspecialidadesR());
            return ResponseEntity.ok(profesor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
