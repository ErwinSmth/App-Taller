package com.Innovacion.Taller.web.controller;

import com.Innovacion.Taller.domain.dto.UsuarioDto;
import com.Innovacion.Taller.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

        @Autowired
        private UsuarioService userService;

        @PostMapping("/registrar")
        public ResponseEntity<UsuarioDto> registrar(@RequestBody UsuarioDto userDto){
            UsuarioDto user = userService.registrarUsuario(userDto);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
}

