package com.example.tallerandroid.auth;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tallerandroid.R;
import com.example.tallerandroid.event.PersonaRegistradaEvent;
import com.example.tallerandroid.event.RolSeleccionadoEvent;
import com.example.tallerandroid.net.RetrofitCliente;
import com.example.tallerandroid.net.apis.ApiPersonService;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroPersonaActivity extends AppCompatActivity {

    private EditText etNombres, etApellidos, etDNI, etTelefono, etEmail, etNacimiento;
    private Button btnRegistrar;
    private Long personaId = null;

    private String rol;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_persona);

        etNombres = findViewById(R.id.etNombres);
        etApellidos = findViewById(R.id.etApellidos);
        etDNI = findViewById(R.id.etDNI);
        etTelefono = findViewById(R.id.etTelefono);
        etEmail = findViewById(R.id.etEmail);
        etNacimiento = findViewById(R.id.etFechaNacimiento);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        etNacimiento.setFocusable(false);
        etNacimiento.setOnClickListener(v -> showDatePicker());

        btnRegistrar.setOnClickListener(v -> {
            if (validarCampos()) {
                if (personaId == null) {
                    registrarPersona();
                } else {
                    editarPersona();
                }
            }
        });
    }

    //Recibe el rol seleccionado y lo mantiene en memoria
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onRolSeleccionado(RolSeleccionadoEvent event){
        this.rol = event.rol;
    }

    //Restaura datos de persona si existen
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onPersonaRegistrada(PersonaRegistradaEvent event){
        this.personaId = event.personaId;
        etNombres.setText(event.nombres);
        etApellidos.setText(event.apellidos);
        etDNI.setText(event.dni);
        etTelefono.setText(event.telefono);
        etEmail.setText(event.email);
        etNacimiento.setText(event.fechaNacimiento);
        this.rol = event.rol;
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    String fecha = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
                    etNacimiento.setText(fecha);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private boolean validarCampos() {
        String nombres = etNombres.getText().toString().trim();
        String apellidos = etApellidos.getText().toString().trim();
        String dni = etDNI.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String fechaNacimiento = etNacimiento.getText().toString().trim();

        if (nombres.isEmpty() || nombres.length() > 100) {
            Toast.makeText(this, "Ingrese nombres (máx 100 caracteres)", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (apellidos.isEmpty() || apellidos.length() > 100) {
            Toast.makeText(this, "Ingrese apellidos (máx 100 caracteres)", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!dni.matches("\\d{8}")) {
            Toast.makeText(this, "DNI debe tener exactamente 8 dígitos", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!telefono.matches("\\d{7,15}")) {
            Toast.makeText(this, "Teléfono debe tener entre 7 y 15 dígitos", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            Toast.makeText(this, "Ingrese un email válido", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (fechaNacimiento.isEmpty()) {
            Toast.makeText(this, "Seleccione la fecha de nacimiento", Toast.LENGTH_SHORT).show();
            return false;
        }
        try {
            java.time.LocalDate fecha = java.time.LocalDate.parse(fechaNacimiento);
            if (fecha.isAfter(java.time.LocalDate.now())) {
                Toast.makeText(this, "La fecha de nacimiento no puede ser futura", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Formato de fecha inválido (YYYY-MM-DD)", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private JsonObject obtenerJsonPersona() {
        JsonObject json = new JsonObject();
        json.addProperty("nombres", etNombres.getText().toString().trim());
        json.addProperty("apellidos", etApellidos.getText().toString().trim());
        json.addProperty("dni", etDNI.getText().toString().trim());
        json.addProperty("telefono", etTelefono.getText().toString().trim());
        json.addProperty("email", etEmail.getText().toString().trim());
        json.addProperty("fechaNacimiento", etNacimiento.getText().toString().trim());
        return json;
    }

    private void registrarPersona() {
        ApiPersonService apiPersonService = RetrofitCliente.getCliente().create(ApiPersonService.class);
        JsonObject json = obtenerJsonPersona();

        Call<JsonObject> call = apiPersonService.registro(json);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegistroPersonaActivity.this, "Persona registrada", Toast.LENGTH_SHORT).show();
                    JsonObject personJson = response.body();
                    personaId = personJson.get("personaId").getAsLong();

                    //Publicar evento para pasar a la siguiente activity
                    EventBus.getDefault().postSticky(new PersonaRegistradaEvent(
                            personaId,
                            etNombres.getText().toString().trim(),
                            etApellidos.getText().toString().trim(),
                            etDNI.getText().toString().trim(),
                            etTelefono.getText().toString().trim(),
                            etEmail.getText().toString().trim(),
                            etNacimiento.getText().toString().trim(),
                            rol
                    ));
                    startActivity(new Intent(RegistroPersonaActivity.this, RegistroUsuarioActivity.class));
                } else {
                    String errorMsg = "Error al registrar";
                    try {
                        errorMsg = response.errorBody() != null ? response.errorBody().string() : errorMsg;
                    } catch (Exception ignored) {}
                    Toast.makeText(RegistroPersonaActivity.this, errorMsg, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(RegistroPersonaActivity.this, "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void editarPersona() {
        ApiPersonService apiPersonService = RetrofitCliente.getCliente().create(ApiPersonService.class);
        JsonObject json = obtenerJsonPersona();

        Call<JsonObject> call = apiPersonService.editarPersona(personaId, json);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegistroPersonaActivity.this, "Datos actualizados", Toast.LENGTH_SHORT).show();
                    //Publicar evento para pasar a la siguiente activity
                    EventBus.getDefault().postSticky(new PersonaRegistradaEvent(
                            personaId,
                            etNombres.getText().toString().trim(),
                            etApellidos.getText().toString().trim(),
                            etDNI.getText().toString().trim(),
                            etTelefono.getText().toString().trim(),
                            etEmail.getText().toString().trim(),
                            etNacimiento.getText().toString().trim(),
                            rol
                    ));
                    startActivity(new Intent(RegistroPersonaActivity.this, RegistroUsuarioActivity.class));
                } else {
                    String errorMsg = "Error al editar";
                    try {
                        errorMsg = response.errorBody() != null ? response.errorBody().string() : errorMsg;
                    } catch (Exception ignored) {}
                    Toast.makeText(RegistroPersonaActivity.this, errorMsg, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(RegistroPersonaActivity.this, "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }

}