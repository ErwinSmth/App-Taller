package com.example.tallerandroid.event;

public class UsuarioRegistradoEvent {

    public final long userId, personaId;
    public final String rol, userName, password;


    public UsuarioRegistradoEvent(long userId, long personaId, String rol, String userName, String password) {
        this.userId = userId;
        this.personaId = personaId;
        this.rol = rol;
        this.userName = userName;
        this.password = password;
    }
}
