package com.example.tallerandroid.event;

public class ProfesorSesionEvent {

    public final long profesorId;
    public final long userId;
    public final String descripcion;
    public ProfesorSesionEvent(long profesorId, long userId, String descripcion) {
        this.profesorId = profesorId;
        this.userId = userId;
        this.descripcion = descripcion;
    }

}
