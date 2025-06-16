package com.example.tallerandroid.rol;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tallerandroid.R;
import com.example.tallerandroid.rol.adapters.SeleccionRolAdapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.util.ArrayList;
import java.util.List;

public class SeleccionRolActivity extends AppCompatActivity {

    private SeleccionRolAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_rol);

        RecyclerView rvRoles = findViewById(R.id.rvRoles);
        Button btnConfirmar = findViewById(R.id.btnConfirmarRol);

        //Recuperar los roles de SharedPreferences
        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
        String rolesJson = prefs.getString("roles", "[]");
        List<JsonObject> rolesList = new ArrayList<>();
        try {
            JsonArray arr = JsonParser.parseString(rolesJson).getAsJsonArray();
            for (int i = 0; i < arr.size(); i++){
                rolesList.add(arr.get(i).getAsJsonObject());
            }
        } catch (Exception e){
            Toast.makeText(this, "Error al leer los roles", Toast.LENGTH_SHORT).show();
        }

        adapter = new SeleccionRolAdapter(rolesList);
        rvRoles.setLayoutManager(new LinearLayoutManager(this));
        rvRoles.setAdapter(adapter);

        btnConfirmar.setOnClickListener(v -> {
            JsonObject rol = adapter.getSelectecRol();
            if (rol ==  null){
                Toast.makeText(this, "Selecciona un rol", Toast.LENGTH_SHORT).show();
                return;
            }
            //Guardar el rol selecciona
            SharedPreferences.Editor editor = prefs.edit();
            editor.putLong("rolActualId", rol.get("rolId").getAsLong());
            editor.putString("rolActualName", rol.get("rolName").getAsString());
            editor.apply();

            //Ir al menu Principal
        });

    }
}