package com.example.tallerandroid.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tallerandroid.R;
import com.example.tallerandroid.event.PersonaRegistradaEvent;
import com.example.tallerandroid.event.ProfesorSesionEvent;
import com.example.tallerandroid.event.UsuarioRegistradoEvent;
import com.example.tallerandroid.net.RetrofitCliente;
import com.example.tallerandroid.net.apis.ApiProfesorService;
import com.example.tallerandroid.net.apis.ApiUserService;
import com.example.tallerandroid.profesor.ProfesorEspecialidadActivity;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroUsuarioActivity extends AppCompatActivity {

    private EditText etUserName, etPassword;
    private Button btnRegistrarUsuario, btnVolverPersona;
    private Long personaId;
    private Long userId;
    private String rol, nombres, apellidos, dni, telefono, email, fechaNacimiento, descripcion;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    //Recibe los datos de persona y rol por EventBus
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onPersonaRegistrada(PersonaRegistradaEvent event){
        this.personaId = event.personaId;
        this.rol = event.rol;
        this.nombres = event.nombres;
        this.apellidos = event.apellidos;
        this.dni = event.dni;
        this.telefono = event.telefono;
        this.email = event.email;
        this.fechaNacimiento = event.fechaNacimiento;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);


        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        btnRegistrarUsuario = findViewById(R.id.btnRegistrarUsuario);
        btnVolverPersona = findViewById(R.id.btnVolverPersona);

        // Recuperar manualmente el último evento sticky si existe
        UsuarioRegistradoEvent event = EventBus.getDefault().getStickyEvent(UsuarioRegistradoEvent.class);
        if (event != null) {
            onUsuarioRegistrado(event);
        }

        btnRegistrarUsuario.setOnClickListener(v -> {
            Log.d("RegistroUsuario", "Al presionar registrar: userId=" + userId);
            if (userId == null){
                Log.d("RegistroUsuario", "Entrando a registrarUsuario()");
                registrarUsuario();
            } else {
                Log.d("RegistroUsuario", "Entrando a editarUsuario()");
                editarUsuario();
            }
        });
        btnVolverPersona.setOnClickListener(v -> finish());
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onUsuarioRegistrado(UsuarioRegistradoEvent event) {
        this.userId = event.userId;
        etUserName.setText(event.userName);
        etPassword.setText(event.password);
        this.personaId = event.personaId;
        this.rol = event.rol;
    }

    private void registrarUsuario() {
        String userName = etUserName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (userName.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

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

        Log.d("RegistroUsuario", "Registrando usuario: personaId=" + personaId + ", userName=" + userName + ", rol=" + rol + ", JSON=" + json.toString());

        ApiUserService apiUserService = RetrofitCliente.getCliente().create(ApiUserService.class);
        Call<JsonObject> call = apiUserService.registrarUsuario(json);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("RegistroUsuario", "Respuesta registro: code=" + response.code() + ", body=" + (response.body() != null ? response.body().toString() : "null"));
                if (response.isSuccessful() && response.body() != null) {
                    Long userId = response.body().get("userId").getAsLong();
                    // Después de recibir el userId del backend
                    EventBus.getDefault().postSticky(new UsuarioRegistradoEvent(
                            userId, personaId, rol, userName, password
                    ));
                    if ("PROFESOR".equals(rol)) {
                        manejarFlujoProfesor(userId);
                    } else {
                        Toast.makeText(RegistroUsuarioActivity.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistroUsuarioActivity.this, loginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    String errorMsg = "";
                    try {
                        errorMsg = response.errorBody() != null ? response.errorBody().string() : "Sin errorBody";
                    } catch (Exception e) {
                        errorMsg = "Excepción al leer errorBody: " + e.getMessage();
                    }
                    Toast.makeText(RegistroUsuarioActivity.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(RegistroUsuarioActivity.this, "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void editarUsuario(){
        String userName = etUserName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (userName.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        JsonObject json = new JsonObject();
        json.addProperty("personaId", personaId);
        json.addProperty("nameUser", userName);
        json.addProperty("contraseña", password);

        // Construir el array de roles igual que en registrarUsuario
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

        Log.d("RegistroUsuario", "Editando usuario: userId=" + userId + ", personaId=" + personaId + ", userName=" + userName + ", rol=" + rol + ", JSON=" + json.toString());

        ApiUserService apiUserService = RetrofitCliente.getCliente().create(ApiUserService.class);
        Call<JsonObject> call = apiUserService.editarUsuario(userId, json);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("RegistroUsuario", "Respuesta edición: code=" + response.code() + ", body=" + (response.body() != null ? response.body().toString() : "null"));
                    Toast.makeText(RegistroUsuarioActivity.this, "Usuario actualizado", Toast.LENGTH_SHORT).show();
                    JsonObject userJson = response.body();
                    EventBus.getDefault().postSticky(new UsuarioRegistradoEvent(
                            userJson.get("userId").getAsLong(),
                            personaId,
                            rol,
                            userName,
                            password
                    ));
                    if ("PROFESOR".equals(rol)) {
                        manejarFlujoProfesor(userJson.get("userId").getAsLong());
                    } else {
                        finish();
                    }
                } else {
                    String errorMsg = "Error al editar";
                    try {
                        if (response.errorBody() != null) {
                            errorMsg = response.errorBody().string();
                        }
                    } catch (Exception e) {
                        errorMsg += ": " + e.getMessage();
                    }
                    Log.e("RegistroUsuario", "Error en edición: " + errorMsg);
                    Toast.makeText(RegistroUsuarioActivity.this, errorMsg, Toast.LENGTH_LONG).show();

                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(RegistroUsuarioActivity.this, "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void manejarFlujoProfesor(Long userId){
        ApiProfesorService apiProfesor = RetrofitCliente.getCliente().create(ApiProfesorService.class);
        // Verifica si ya existe profesor para este usuario
        apiProfesor.obtenerporUsuario(userId).enqueue(new Callback<JsonObject>() {
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
                                EventBus.getDefault().postSticky(new ProfesorSesionEvent(profesorId, userId, descripcion));
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
    }


    private void irAEspecialidad(Long userId, Long profesorId) {
        Intent intent = new Intent(RegistroUsuarioActivity.this, ProfesorEspecialidadActivity.class);
        intent.putExtra("userId", userId);
        intent.putExtra("profesorId", profesorId);
        startActivity(intent);
    }
}