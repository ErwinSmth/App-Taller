package com.example.tallerandroid.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tallerandroid.R;
import com.example.tallerandroid.net.RetrofitCliente;
import com.example.tallerandroid.net.apis.ApiUserService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroUsuarioActivity extends AppCompatActivity {

    private EditText etUserName, etPassword;
    private Button btnRegistrarUsuario, btnVolverPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_usuario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        btnRegistrarUsuario = findViewById(R.id.btnRegistrarUsuario);
        btnVolverPersona = findViewById(R.id.btnVolverPersona);

        btnRegistrarUsuario.setOnClickListener(v -> registrarUsuario());
        btnVolverPersona.setOnClickListener(v -> volverARegistroPersona());
    }

    private void registrarUsuario() {
        String userName = etUserName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (userName.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        long personaId = getIntent().getLongExtra("personaId", -1);
        if (personaId == -1) {
            Toast.makeText(this, "Error interno: personaId no encontrado", Toast.LENGTH_SHORT).show();
            return;
        }

        JsonObject json = new JsonObject();
        json.addProperty("personaId", personaId);
        json.addProperty("nameUser", userName);
        json.addProperty("contraseña", password);

        // Rol estudiante por defecto
        JsonArray roles = new JsonArray();
        JsonObject rolEstudiante = new JsonObject();
        rolEstudiante.addProperty("rolId", 1); // 1 = estudiante
        rolEstudiante.addProperty("rolName", "ESTUDIANTE");
        roles.add(rolEstudiante);
        json.add("roles", roles);

        ApiUserService apiUserService = RetrofitCliente.getCliente().create(ApiUserService.class);
        Call<JsonObject> call = apiUserService.registrarUsuario(json);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegistroUsuarioActivity.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                    finish(); // O navega a login o main
                } else {
                    Toast.makeText(RegistroUsuarioActivity.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(RegistroUsuarioActivity.this, "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void volverARegistroPersona() {
        Intent intent = new Intent();
        // Devuelve los datos de persona al activity anterior
        intent.putExtra("nombres", getIntent().getStringExtra("nombres"));
        intent.putExtra("apellidos", getIntent().getStringExtra("apellidos"));
        intent.putExtra("dni", getIntent().getStringExtra("dni"));
        intent.putExtra("telefono", getIntent().getStringExtra("telefono"));
        intent.putExtra("email", getIntent().getStringExtra("email"));
        intent.putExtra("fechaNacimiento", getIntent().getStringExtra("fechaNacimiento"));
        setResult(RESULT_OK, intent);
        finish();
    }
}