package com.example.tallerandroid.auth;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tallerandroid.R;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class loginActivity extends AppCompatActivity {

    private EditText etUserName, etPassword;
    private Button btnLogin;

    private static final String BASE_URL = "http://192.168.1.42:8090/AppTaller/usuario/login";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Referencias a los elementos del formulario
        etUserName = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        //Configurar el boton de login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etUserName.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (userName.isEmpty() || password.isEmpty()){
                    Toast.makeText(loginActivity.this, "Por favor completa todos los campso", Toast.LENGTH_SHORT).show();
                } else {
                    login(userName, password);
                }
            }
        });
    }
    private void login(String username, String password){
        //Crear el cliente HTTP
        OkHttpClient cliente = new OkHttpClient();

        //Crear el cuerpo de la solicitud JSON
        JsonObject json = new JsonObject();
        json.addProperty("nameUser" ,username);
        json.addProperty("contraseña", password);

        RequestBody body = RequestBody.create(
                json.toString(),
                MediaType.parse("application/json; charset=utf-8")
        );

        //Creacion solicitud HTTP
        Request request = new Request.Builder()
                .url(BASE_URL)
                .post(body)
                .build();

        //Enviar la solicitud
        cliente.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> {
                    Toast.makeText(loginActivity.this, "Error de conexion", Toast.LENGTH_SHORT).show();
                    Log.e("loginActivity", "Error: " + e.getMessage(), e);
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String responseBody = response.body().string();
                    runOnUiThread(() -> {
                        Toast.makeText(loginActivity.this, "Inicio de Sesion Exitoso", Toast.LENGTH_SHORT).show();
                        Log.d("loginActivity", "Respuesta: " + responseBody);
                        //Aqui se podra dirigir a otra pestaña
                    });
                } else {
                    runOnUiThread(() -> {
                        Toast.makeText(loginActivity.this, "Credenciales Incorrectas", Toast.LENGTH_SHORT).show();
                        Log.e("loginActivity", "Error: " + response.code());
                    });
                }
            }
        });
    }

}
