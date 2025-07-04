package com.Innovacion.Taller.web.controller;

import com.Innovacion.Taller.domain.dto.UsuarioDto;
import com.Innovacion.Taller.domain.dto.UsuarioRegistroDto;
import com.Innovacion.Taller.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

        @Autowired
        private UsuarioService userService;

        @PostMapping("/registrar")
        public ResponseEntity<UsuarioDto> registrar(@RequestBody UsuarioRegistroDto userDto){
            try {
                UsuarioDto user = userService.registrarUsuario(userDto);
                return new ResponseEntity<>(user, HttpStatus.CREATED);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDto> login(@RequestBody UsuarioDto loginRequest) {
        try {
            // Llamar al servicio para validar las credenciales
            Optional<UsuarioDto> usuario = userService.login(loginRequest.getNameUser(), loginRequest.getContraseña());
            return usuario.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}

