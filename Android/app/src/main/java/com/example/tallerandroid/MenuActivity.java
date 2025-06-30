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
import androidx.fragment.app.Fragment;


import com.example.tallerandroid.event.ProfesorSesionEvent;
import com.example.tallerandroid.fragments.BuscarTallerFragment;
import com.example.tallerandroid.fragments.estudiante.CuentaEstudianteFragment;
import com.example.tallerandroid.fragments.estudiante.InicioEstudianteFragment;
import com.example.tallerandroid.fragments.estudiante.MisTalleresEstudianteFragment;
import com.example.tallerandroid.fragments.profesor.CuentaProfesorFragment;
import com.example.tallerandroid.fragments.profesor.InicioProfesorFragment;
import com.example.tallerandroid.fragments.profesor.MisTalleresProfesorFragment;
import com.example.tallerandroid.net.RetrofitCliente;
import com.example.tallerandroid.net.apis.ApiProfesorService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MenuActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private Toolbar toolbar;

    //Variables del usuario
    private Long profesorId;

    @Override
    protected void onStart() {
        super.onStart();
        // Recupera el profesorId de SharedPreferences o consulta al backend si no existe
        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
        profesorId = prefs.contains("profesorId") ? prefs.getLong("profesorId", -1) : null;
        long userId = prefs.getLong("userId", -1);

        if ((profesorId == null || profesorId == -1) && userId != -1) {
            // Consultar al backend el profesorId usando el userId
            ApiProfesorService apiProfesor = RetrofitCliente.getCliente().create(ApiProfesorService.class);
            apiProfesor.obtenerporUsuario(userId).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().has("profesorId")) {
                        long profId = response.body().get("profesorId").getAsLong();
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putLong("profesorId", profId);
                        editor.apply();
                        profesorId = profId;
                        Log.d("MenuActivity", "profesorId recuperado y guardado: " + profId);
                    } else {
                        Log.d("MenuActivity", "No se encontró profesorId para este usuario");
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.e("MenuActivity", "Error al consultar profesorId", t);

                }
            });
        } else {
            Log.d("MenuActivity", "profesorId recuperado de prefs: " + profesorId);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        // No es necesario EventBus.unregister(this)
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setupToolbarAndDrawer();
        setupUserInfoInDrawer();
        setupDynamicDrawerMenu();
        setupBottomNavigation();
    }

    private void setupToolbarAndDrawer() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setupUserInfoInDrawer() {
        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
        String nombreUsuario = prefs.getString("nameUser", "Usuario");
        String rolActual = prefs.getString("rolActualName", "Rol");

        NavigationView navView = findViewById(R.id.nav_view);
        View headerView = navView.getHeaderView(0);
        TextView tvNombreUsuario = headerView.findViewById(R.id.tvNombreUsuario);
        TextView tvRolActual = headerView.findViewById(R.id.tvRolActual);

        tvNombreUsuario.setText(nombreUsuario);
        tvRolActual.setText(rolActual);
    }

    private void setupDynamicDrawerMenu() {
        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
        long rolActualId = prefs.getLong("rolActualId", -1);
        String permisosJson = prefs.getString("permisos", "{}");

        NavigationView navView = findViewById(R.id.nav_view);
        Menu menu = navView.getMenu();
        menu.clear();

        try {
            JsonObject permisosObj = JsonParser.parseString(permisosJson).getAsJsonObject();
            String rolKey = String.valueOf(rolActualId);
            if (permisosObj.has(rolKey)) {
                JsonArray permisosRol = permisosObj.getAsJsonArray(rolKey);
                for (JsonElement permisoEl : permisosRol) {
                    JsonObject permisoObj = permisoEl.getAsJsonObject();
                    String nombrePermiso = permisoObj.get("nombre").getAsString();
                    menu.add(Menu.NONE, View.generateViewId(), Menu.NONE, nombrePermiso);
                }
            }
        } catch (Exception e) {
            Log.e("MenuActivity", "Error al parsear permisos: " + e.getMessage());
        }

        navView.setNavigationItemSelectedListener(item -> {
            String titulo = item.getTitle().toString();
            Fragment fragment = null;

            // Asocia el nombre del permiso con el fragment correspondiente
            if (titulo.equalsIgnoreCase("Crear Taller")){
                fragment = new com.example.tallerandroid.fragments.profesor.CrearTallerFragment();
            }  else if (titulo.equalsIgnoreCase("Mis Talleres")) {
                fragment = new com.example.tallerandroid.fragments.profesor.MisTalleresProfesorFragment();
            } else if (titulo.equalsIgnoreCase("Inscripcion a Taller")) {
                // fragment = new com.example.tallerandroid.fragments.estudiante.InscripcionTallerFragment();
            }

            if (fragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
                drawerLayout.closeDrawers();
                return true;
            }
            return false;
        });
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
        long rolActualId = prefs.getLong("rolActualId", -1);

        // Solo mostrar "Buscar" si es estudiante (rolActualId == 1)
        if(rolActualId != 1){
            bottomNav.getMenu().findItem(R.id.nav_buscar).setVisible(false);
        } else {
            bottomNav.getMenu().findItem(R.id.nav_buscar).setVisible(true);
        }

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            int itemId = item.getItemId();

            if (rolActualId == 1) { // Estudiante
                if (itemId == R.id.nav_inicio) {
                    fragment = new InicioEstudianteFragment();
                } else if (itemId == R.id.nav_mis_talleres) {
                    fragment = new MisTalleresEstudianteFragment();
                } else if (itemId == R.id.nav_cuenta) {
                    fragment = new CuentaEstudianteFragment();
                } else if (itemId == R.id.nav_buscar){
                    fragment = new BuscarTallerFragment();
                }
            } else if (rolActualId == 2) { // Profesor
                if (itemId == R.id.nav_inicio) {
                    fragment = new InicioProfesorFragment();
                } else if (itemId == R.id.nav_mis_talleres) {
                    fragment = new MisTalleresProfesorFragment();
                } else if (itemId == R.id.nav_cuenta) {
                    fragment = new CuentaProfesorFragment();
                }
            }

            if (fragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
                return true;
            }

            return false;
        });

        // Mostrar fragmento inicial (Inicio)
        bottomNav.setSelectedItemId(R.id.nav_inicio);
    }
}