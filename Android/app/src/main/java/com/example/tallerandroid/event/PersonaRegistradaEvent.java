package com.example.tallerandroid.event;

public class PersonaRegistradaEvent {

    public final long personaId;
    public final String nombres, apellidos, dni, telefono,email,  fechaNacimiento, rol;

    public PersonaRegistradaEvent(long personaId, String nombres, String apellidos, String dni, String telefono, String email, String fechaNacimiento, String rol) {
        this.personaId = personaId;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.rol = rol;
    }
}
