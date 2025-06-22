package com.example.tallerandroid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class MenuActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        navView = findViewById(R.id.nav_view);
        Menu menu = navView.getMenu();
        menu.clear();

        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
        String nombreUsuario = prefs.getString("nameUser", "Usuario");
        String permisosJson = prefs.getString("permisos", "{}");
        long rolActualId = prefs.getLong("rolActualId", -1);
        String rolActual = prefs.getString("rolActualName", "Rol");

        Log.d("Id del rol actual", String.valueOf(rolActualId));

        try {
            JsonObject permisosObj = JsonParser.parseString(permisosJson).getAsJsonObject();
            String rolKey = String.valueOf(rolActualId);
            if (permisosObj.has(rolKey)) {
                JsonArray permisosRol = permisosObj.getAsJsonArray(rolKey);
                for(JsonElement permisoEl : permisosRol){
                    JsonObject permisoObj = permisoEl.getAsJsonObject();
                    String nombrePermiso = permisoObj.get("nombre").getAsString();
                    String referencia = permisoObj.get("referencia").getAsString(); // Lo puedes usar después para navegación

                    // Agrega el item al menú
                    menu.add(Menu.NONE, View.generateViewId(), Menu.NONE, nombrePermiso);
                    Log.e("MenuActivity", "Permisos" + permisoEl.toString());
                }
            } else {
                // No hay permisos para este rol
            }
        } catch (Exception e) {
            Log.e("MenuActivity", "Error al parsear permisos: " + e.getMessage());
        }

        View headerView = navView.getHeaderView(0);
        TextView tvNombreUsuario = headerView.findViewById(R.id.tvNombreUsuario);
        TextView tvRolActual = headerView.findViewById(R.id.tvRolActual);

        tvNombreUsuario.setText(nombreUsuario);
        tvRolActual.setText(rolActual);

        // Configuración de la Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configuración del Drawer
        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Aquí puedes agregar listeners para los ítems del menú si lo necesitas
        // navView.setNavigationItemSelectedListener(...);
    }
}