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
import com.example.tallerandroid.net.apis.ApiProfesorService;
import com.example.tallerandroid.net.apis.ApiUserService;
import com.example.tallerandroid.profesor.ProfesorEspecialidadActivity;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroUsuarioActivity extends AppCompatActivity {

    private EditText etUserName, etPassword;
    private Button btnRegistrarUsuario, btnVolverPersona;

    private String rol;

    private Long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        rol = getIntent().getStringExtra("rol");

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

        JsonArray roles = new JsonArray();
        JsonObject rolObj = new JsonObject();
        if ("ESTUDIANTE".equals(rol)) {
            rolObj.addProperty("rolId", 1);
            rolObj.addProperty("rolName", "ESTUDIANTE");
        } else if ("PROFESOR".equals(rol)) {
            rolObj.addProperty("rolId", 2);
            rolObj.addProperty("rolName", "PROFESOR");
        } else if ("ORGANIZADOR".equals(rol)) {
            rolObj.addProperty("rolId", 3);
            rolObj.addProperty("rolName", "ORGANIZADOR");
        }
        roles.add(rolObj);
        json.add("roles", roles);

        ApiUserService apiUserService = RetrofitCliente.getCliente().create(ApiUserService.class);
        Call<JsonObject> call = apiUserService.registrarUsuario(json);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Long userId = response.body().get("userId").getAsLong();
                    if ("PROFESOR".equals(rol)) {
                        ApiProfesorService apiProfesor = RetrofitCliente.getCliente().create(ApiProfesorService.class);
                        // Verifica si ya existe profesor para este usuario
                        apiProfesor.obtenerUsuario(userId).enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                if (response.isSuccessful() && response.body() != null && response.body().has("profesorId")) {
                                    // Ya existe profesor, continúa el flujo
                                    Long profesorId = response.body().get("profesorId").getAsLong();
                                    irAEspecialidad(userId, profesorId);
                                } else {
                                    // No existe, intenta crearlo
                                    JsonObject json = new JsonObject();
                                    json.addProperty("userId", userId);
                                    apiProfesor.crearProfesor(json).enqueue(new Callback<JsonObject>() {
                                        @Override
                                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                            if (response.isSuccessful() && response.body() != null) {
                                                Long profesorId = response.body().get("profesorId").getAsLong();
                                                irAEspecialidad(userId, profesorId);
                                            } else if (response.code() == 409) { // 409 Conflict por duplicidad
                                                Toast.makeText(RegistroUsuarioActivity.this, "El profesor ya existe para este usuario.", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(RegistroUsuarioActivity.this, "Error al crear profesor", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<JsonObject> call, Throwable t) {
                                            Toast.makeText(RegistroUsuarioActivity.this, "Error de red al crear profesor", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {
                                Toast.makeText(RegistroUsuarioActivity.this, "Error de red al verificar profesor", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(RegistroUsuarioActivity.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                        finish();
                    }
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
        intent.putExtra("personaId", getIntent().getLongExtra("personaId", -1));
        intent.putExtra("rol", getIntent().getStringExtra("rol"));
        intent.putExtra("nombres", getIntent().getStringExtra("nombres"));
        intent.putExtra("apellidos", getIntent().getStringExtra("apellidos"));
        intent.putExtra("dni", getIntent().getStringExtra("dni"));
        intent.putExtra("telefono", getIntent().getStringExtra("telefono"));
        intent.putExtra("email", getIntent().getStringExtra("email"));
        intent.putExtra("fechaNacimiento", getIntent().getStringExtra("fechaNacimiento"));
        setResult(RESULT_OK, intent);
        finish();
    }

    private void irAEspecialidad(Long userId, Long profesorId) {
        Intent intent = new Intent(RegistroUsuarioActivity.this, ProfesorEspecialidadActivity.class);
        intent.putExtra("userId", userId);
        intent.putExtra("profesorId", profesorId);
        startActivity(intent);
    }
}