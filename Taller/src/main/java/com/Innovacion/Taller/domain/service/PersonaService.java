package com.Innovacion.Taller.domain.service;

import com.Innovacion.Taller.domain.dto.PersonaDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {

    @Autowired
    private IPersonaRepository personRepo;

}
