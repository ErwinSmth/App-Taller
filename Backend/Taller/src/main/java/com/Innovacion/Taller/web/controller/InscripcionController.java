package com.Innovacion.Taller.web.controller;

import com.Innovacion.Taller.domain.dto.taller.InscripcionDto;
import com.Innovacion.Taller.domain.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inscripcion")
public class InscripcionController {

    @Autowired
    private InscripcionService service;

    @PostMapping("/registrar")
    public ResponseEntity<InscripcionDto> inscribirEstudiante(@RequestParam Long tallerId, @RequestParam Long estudianteId) {
        try {
            InscripcionDto dto = service.inscribirEstudiante(tallerId, estudianteId);
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
