package com.example.tallerandroid.auth;

import android.app.DatePickerDialog;
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

import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegistroPersonaActivity extends AppCompatActivity {

    private EditText etNombres, etApellidos, etDNI, etTelefono, etEmail, etNacimiento;
    private Button btnRegistrar;
    private static final String BASE_URL = "http://10.0.2.2:8090/AppTaller/personas/registrar";


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

        // Deshabilita la edición directa y muestra el DatePicker al tocar
        etNacimiento.setFocusable(false);
        etNacimiento.setOnClickListener(v -> showDatePicker());

        btnRegistrar.setOnClickListener(v -> registrarPersona());

    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    // Formato YYYY-MM-DD
                    String fecha = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
                    etNacimiento.setText(fecha);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void registrarPersona() {
        String nombres = etNombres.getText().toString().trim();
        String apellidos = etApellidos.getText().toString().trim();
        String dni = etDNI.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String fechaNacimiento = etNacimiento.getText().toString().trim();

        // Validaciones amigables para el usuario
        if (nombres.isEmpty() || nombres.length() > 100) {
            Toast.makeText(this, "Ingrese nombres (máx 100 caracteres)", Toast.LENGTH_SHORT).show();
            return;
        }
        if (apellidos.isEmpty() || apellidos.length() > 100) {
            Toast.makeText(this, "Ingrese apellidos (máx 100 caracteres)", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!dni.matches("\\d{8}")) {
            Toast.makeText(this, "DNI debe tener exactamente 8 dígitos", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!telefono.matches("\\d{7,15}")) {
            Toast.makeText(this, "Teléfono debe tener entre 7 y 15 dígitos", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            Toast.makeText(this, "Ingrese un email válido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (fechaNacimiento.isEmpty()) {
            Toast.makeText(this, "Seleccione la fecha de nacimiento", Toast.LENGTH_SHORT).show();
            return;
        }
        // Validación de fecha futura (opcional, si quieres hacerlo en el cliente)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            java.time.LocalDate fecha;
            try {
                fecha = java.time.LocalDate.parse(fechaNacimiento);
                if (fecha.isAfter(java.time.LocalDate.now())) {
                    Toast.makeText(this, "La fecha de nacimiento no puede ser futura", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (Exception e) {
                Toast.makeText(this, "Formato de fecha inválido (YYYY-MM-DD)", Toast.LENGTH_SHORT).show();
                return;
            }

            OkHttpClient client = new OkHttpClient();
            JSONObject json = new JSONObject();
            try {
                json.put("nombres", nombres);
                json.put("apellidos", apellidos);
                json.put("dni", dni);
                json.put("telefono", telefono);
                json.put("email", email);
                json.put("fechaNacimiento", fechaNacimiento);
            } catch (Exception e) {
                Toast.makeText(this, "Error al crear JSON", Toast.LENGTH_SHORT).show();
                return;
            }

            RequestBody body = RequestBody.create(json.toString(), MediaType.parse("application/json"));
            Request request = new Request.Builder().url(BASE_URL).post(body).build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(() -> Toast.makeText(RegistroPersonaActivity.this, "Error de red", Toast.LENGTH_SHORT).show());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    runOnUiThread(() -> {
                        if (response.isSuccessful()) {
                            Toast.makeText(RegistroPersonaActivity.this, "Persona registrada", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            String errorMsg = "Error al registrar";
                            try {
                                errorMsg = response.body().string();
                            } catch (Exception ignored) {
                            }
                            Toast.makeText(RegistroPersonaActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }

    }
}