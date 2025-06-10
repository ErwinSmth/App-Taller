package com.example.tallerandroid.utilities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tallerandroid.R;
import com.example.tallerandroid.auth.RegistroPersonaActivity;

public class RolRegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol_registro);

        Button btnEstudiante = findViewById(R.id.btnEstudiante);
        Button btnProfesor = findViewById(R.id.btnProfesor);
        Button btnOrganizador = findViewById(R.id.btnOrganizador);

        btnEstudiante.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegistroPersonaActivity.class);
            intent.putExtra("rol", "ESTUDIANTE");
            startActivity(intent);
        });
        btnProfesor.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegistroPersonaActivity.class);
            intent.putExtra("rol", "PROFESOR");
            startActivity(intent);
        });

        //btnOrganizador.setOnClickListener(v -> {
        //    Intent intent = new Intent(this, RegistroOrganizadorActivity.class);
         //   intent.putExtra("rol", "ORGANIZADOR");
          //  startActivity(intent);
        //});

    }
}