package com.example.tallerandroid.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tallerandroid.R;
import com.example.tallerandroid.event.RolSeleccionadoEvent;

import org.greenrobot.eventbus.EventBus;

public class RolRegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol_registro);

        Button btnEstudiante = findViewById(R.id.btnEstudiante);
        Button btnProfesor = findViewById(R.id.btnProfesor);
        //Button btnOrganizador = findViewById(R.id.btnOrganizador);

        btnEstudiante.setOnClickListener(v -> {
            EventBus.getDefault().postSticky(new RolSeleccionadoEvent("ESTUDIANTE"));
            startActivity(new Intent(this, RegistroPersonaActivity.class));
        });
        btnProfesor.setOnClickListener(v -> {
            EventBus.getDefault().postSticky(new RolSeleccionadoEvent("PROFESOR"));
            startActivity(new Intent(this, RegistroPersonaActivity.class));
        });

        //btnOrganizador.setOnClickListener(v -> {
        //    EventBus.getDefault().postSticky(new RolSeleccionadoEvent("ORGANIZADOR"));
        //    startActivity(new Intent(this, RegistroPersonaActivity.class));
        //});

    }
}