package com.example.tallerandroid.profesor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tallerandroid.R;
import com.example.tallerandroid.auth.loginActivity;
import com.example.tallerandroid.event.ProfesorSesionEvent;
import com.example.tallerandroid.model.Especialidad;
import com.example.tallerandroid.net.RetrofitCliente;
import com.example.tallerandroid.net.apis.ApiEspecialidadService;
import com.example.tallerandroid.net.apis.ApiProfesorService;
import com.example.tallerandroid.profesor.adapters.EspecialidadAdapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfesorEspecialidadActivity extends AppCompatActivity {

    private RecyclerView rvEspecialidades;
    private EditText etDescripcion;
    private Button btnGuardar;
    private EspecialidadAdapter adapter;
    private Long profesorId;
    private String descripcionAnterior = "";
    private List<Especialidad> especialidadesSeleccionadas = new ArrayList<>();


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor_especialidad);

        rvEspecialidades = findViewById(R.id.rvEspecialidades);
        etDescripcion = findViewById(R.id.etDescripcion);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Obtén el profesorId desde el intent (ajusta según tu flujo)
        profesorId = getIntent().getLongExtra("profesorId", -1);

        // Recupera datos si vuelves atrás (opcional)
        descripcionAnterior = getIntent().getStringExtra("descripcion") != null ?
                getIntent().getStringExtra("descripcion") : "";
        etDescripcion.setText(descripcionAnterior);

        // Configura el RecyclerView
        rvEspecialidades.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EspecialidadAdapter(new ArrayList<>());
        rvEspecialidades.setAdapter(adapter);

        Long userId = getIntent().getLongExtra("userId", -1);
        if(userId != 1){
            obtenerDatosUsuario(userId);
        }


        // Llama al backend para obtener especialidades
        cargarEspecialidades();

        btnGuardar.setOnClickListener(v -> guardarEspecialidadesYDescripcion());

    }

    private void cargarEspecialidades() {
        ApiEspecialidadService api = RetrofitCliente.getCliente().create(ApiEspecialidadService.class);
        api.listarEspecialidades().enqueue(new Callback<List<Especialidad>>() {
            @Override
            public void onResponse(Call<List<Especialidad>> call, Response<List<Especialidad>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setEspecialidades(response.body());
                } else {
                    String errorMsg = "";
                    try {
                        errorMsg = response.errorBody() != null ? response.errorBody().string() : "Sin errorBody";
                    } catch (Exception e) {
                        Log.e("Especialidad", "Error leyendo errorBody", e);
                    }
                    Log.e("Especialidad", "Error al cargar especialidades: " + errorMsg);
                    Toast.makeText(ProfesorEspecialidadActivity.this, "Error al cargar especialidades: " + errorMsg, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Especialidad>> call, Throwable t) {
                Log.e("Especialidad", "Fallo de red al cargar especialidades", t);
                Toast.makeText(ProfesorEspecialidadActivity.this, "Error de red", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void guardarEspecialidadesYDescripcion() {
        especialidadesSeleccionadas = adapter.getEspecialidadesSeleccionadas();
        String descripcion = etDescripcion.getText().toString().trim();

        if (especialidadesSeleccionadas.isEmpty()) {
            Toast.makeText(this, "Selecciona al menos una especialidad", Toast.LENGTH_SHORT).show();
            return;
        }
        if (descripcion.isEmpty()) {
            Toast.makeText(this, "Agrega una descripción", Toast.LENGTH_SHORT).show();
            return;
        }

        JsonObject json = new JsonObject();
        json.addProperty("profesorId", profesorId);
        json.addProperty("descripcion", descripcion);

        JsonArray especialidadesArray = new JsonArray();
        for (Especialidad esp : especialidadesSeleccionadas) {
            especialidadesArray.add(esp.getEspecialidadId());
        }
        json.add("especialidades", especialidadesArray);

        // Log para depuración
        Log.d("ProfesorEspecialidad", "JSON enviado: " + json.toString());

        ApiProfesorService apiProfesor = RetrofitCliente.getCliente().create(ApiProfesorService.class);
        apiProfesor.actualizarEspecialidadesyDescripcion(json).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(ProfesorEspecialidadActivity.this, loginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    Toast.makeText(ProfesorEspecialidadActivity.this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ProfesorEspecialidadActivity.this, "Error al guardar datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ProfesorEspecialidadActivity.this, "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void obtenerDatosUsuario(Long userId) {
        ApiProfesorService api = RetrofitCliente.getCliente().create(ApiProfesorService.class);
        api.obtenerporUsuario(userId).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    JsonObject usuario = response.body();
                    // Si el usuario ya tiene profesor, puedes obtener el profesorId aquí
                    if (usuario.has("profesor") && !usuario.get("profesor").isJsonNull()) {
                        JsonObject profesor = usuario.getAsJsonObject("profesor");
                        profesorId = profesor.get("profesorId").getAsLong();
                        // Si ya tiene descripción, puedes mostrarla
                        if (profesor.has("descripcion")) {
                            etDescripcion.setText(profesor.get("descripcion").getAsString());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(ProfesorEspecialidadActivity.this, "Error al obtener usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }
}