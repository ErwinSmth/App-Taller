package com.example.tallerandroid.auth;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tallerandroid.R;
import com.example.tallerandroid.net.apis.ApiUserService;
import com.example.tallerandroid.net.RetrofitCliente;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity {

    private EditText etUserName, etPassword;
    private Button btnLogin;

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

        ApiUserService apiUserService = RetrofitCliente.getCliente().create(ApiUserService.class);

        //Crear el cuerpo de la solicitud JSON
        JsonObject json = new JsonObject();
        json.addProperty("nameUser" ,username);
        json.addProperty("contraseña", password);

        Call<JsonObject> call = apiUserService.login(json);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(loginActivity.this, "Inicio de Sesión Exitoso", Toast.LENGTH_SHORT).show();
                    Log.d("loginActivity", "Respuesta: " + response.body());
                    // Aquí puedes navegar a otra pantalla
                } else {
                    Toast.makeText(loginActivity.this, "Credenciales Incorrectas", Toast.LENGTH_SHORT).show();
                    Log.e("loginActivity", "Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(loginActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                Log.e("loginActivity", "Error: " + t.getMessage(), t);

            }
        });

    }

}
