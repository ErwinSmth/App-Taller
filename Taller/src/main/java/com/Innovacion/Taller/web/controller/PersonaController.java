package com.Innovacion.Taller.web.controller;

import com.Innovacion.Taller.domain.dto.PersonaDto;
import com.Innovacion.Taller.domain.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //Esta clase sera controlador Rest y devolvera JSON
@RequestMapping("/personas")  //Ruta principal para todos los endpoints de la clase
public class PersonaController {

    @Autowired
    private PersonaService service;

    @PostMapping("/save")
    public ResponseEntity<PersonaDto> registrar(@RequestBody PersonaDto person){ //Indica que los datos vendran en el cuerpo de la solicitud (JSON)
        return new ResponseEntity<>(service.registrarPersona(person), HttpStatus.CREATED);
    }

    @GetMapping("/email") //Ruta: /personas/email
    public ResponseEntity<PersonaDto> buscarEmail(@RequestParam("valor") String email){ //RequestParam porque obtiene el valor del parametro desde la URL
        return service.buscarEmail(email)
                .map(ResponseEntity::ok)//SI encuentra devuelve Ok
                .orElse(ResponseEntity.notFound().build()); // Si no encuentra devolvera 404 Not Found
    }

}
