package com.example.tallerandroid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);

        long userId = prefs.getLong("userId", -1);
        String nameUser = prefs.getString("nameUser", "N/A");
        String rolesJson = prefs.getString("roles", "[]");
        String permisosJson = prefs.getString("permisos", "[]");
        String rolActualName = prefs.getString("rolActualName", "N/A");

        NavigationView navigationView = findViewById(R.id.nav_view);

        //Personalizar el header
        View header = navigationView.getHeaderView(0);
        TextView tvNameUser = header.findViewById(R.id.tvNombreUsuario);
        TextView tvRolActual = header.findViewById(R.id.tvRolActual);
        tvNameUser.setText(nameUser);
        tvRolActual.setText(rolActualName);

        //Generar las opciones del menu

        Menu menu = navigationView.getMenu();
        menu.clear();

        //Cargar los permisos y agregar opciones

        try {

            JsonArray permisos = JsonParser.parseString(permisosJson).getAsJsonArray();
            for (int i = 0; i < permisos.size(); i++){
                JsonObject permiso = permisos.get(i).getAsJsonObject();
                String referencia = permiso.get("referencia").getAsString();
                String nombre = permiso.get("nombre").getAsString();
                menu.add(Menu.NONE, referencia.hashCode(), Menu.NONE, nombre);
            }

        } catch (Exception ignore) { }

        //Si tiene mas de un rol, agregar opcion de cambiar de rol
        try {
            JsonArray rolesArray = JsonParser.parseString(rolesJson).getAsJsonArray();
            if (rolesArray.size() > 1){
                menu.add(Menu.NONE, "Cambiar_rol".hashCode(), Menu.NONE, "Cambiar de rol");
            }
        } catch (Exception ignore) { }

        //Listener para manejar seleccion de menu
        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == "cambiar_rol".hashCode()){
                //Se recargaria la pagina y se cambiaria de opciones
            }
            //Aqui se podria abrir el fragment correspondiente segun el permiso
            return true;
        });

    }
}