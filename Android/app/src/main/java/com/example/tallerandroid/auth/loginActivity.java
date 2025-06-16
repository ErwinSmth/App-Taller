package com.example.tallerandroid.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tallerandroid.MenuActivity;
import com.example.tallerandroid.R;
import com.example.tallerandroid.net.apis.ApiUserService;
import com.example.tallerandroid.net.RetrofitCliente;
import com.example.tallerandroid.rol.SeleccionRolActivity;
import com.example.tallerandroid.utilities.RolRegistroActivity;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity {

    private EditText etUserName, etPassword;
    private TextView tvNoCuenta;
    private Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Referencias a los elementos del formulario
        etUserName = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvNoCuenta = findViewById(R.id.tv_no_cuenta);

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

        tvNoCuenta.setOnClickListener(v -> {
            startActivity(new Intent(this, RolRegistroActivity.class));
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
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if (response.isSuccessful()) {

                    JsonObject body = response.body();

                    //SharedPreferences para mantener siempre toda la informacion del usuario
                    SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();

                    editor.putLong("userId", body.get("userId").getAsLong());
                    editor.putString("nameUser", body.get("nameUser").getAsString());

                    //Guardar roles y permisos como JSON string
                    editor.putString("roles", body.get("roles").toString());
                    editor.putString("permisos", body.get("permisos").toString());

                    editor.apply();

                    String rolesJson = body.get("roles").toString();
                    JsonArray rolesArray = body.getAsJsonArray("roles");

                    if (rolesArray.size() == 1) {
                        //Solo un rol
                        JsonObject rol = rolesArray.get(0).getAsJsonObject();
                        editor.putLong("rolActualId", rol.get("rolId").getAsLong());
                        editor.putString("rolActualName", rol.get("rolName").getAsString());
                        editor.apply();
                        startActivity(new Intent(loginActivity.this, MenuActivity.class));
                        Toast.makeText(loginActivity.this, "Inicio de Sesión Exitoso", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        startActivity(new Intent(loginActivity.this, SeleccionRolActivity.class));
                        Toast.makeText(loginActivity.this, "Inicio de Sesión Exitoso", Toast.LENGTH_SHORT).show();
                        finish();
                    }
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
