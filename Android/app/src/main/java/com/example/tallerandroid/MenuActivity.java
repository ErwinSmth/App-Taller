package com.example.tallerandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


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
        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
        long rolActualId = prefs.getLong("rolActualId", -1);
        long userId = prefs.getLong("userId", -1);

        // PROFESOR: Guarda profesorId si no está en prefs
        if (rolActualId == 2) { // Profesor
            long profesorId = prefs.getLong("profesorId", -1);
            if (profesorId == -1 && userId != -1) {
                ApiProfesorService apiProfesor = RetrofitCliente.getCliente().create(ApiProfesorService.class);
                apiProfesor.obtenerporUsuario(userId).enqueue(new retrofit2.Callback<JsonObject>() {
                    @Override
                    public void onResponse(retrofit2.Call<com.google.gson.JsonObject> call, retrofit2.Response<com.google.gson.JsonObject> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().has("profesorId")) {
                            long profId = response.body().get("profesorId").getAsLong();
                            prefs.edit().putLong("profesorId", profId).apply();
                            Log.d("MenuActivity", "profesorId recuperado y guardado: " + profId);
                        } else {
                            Log.d("MenuActivity", "No se encontró profesorId para este usuario");
                        }
                    }
                    @Override
                    public void onFailure(retrofit2.Call<com.google.gson.JsonObject> call, Throwable t) {
                        Log.e("MenuActivity", "Error al consultar profesorId", t);
                    }
                });
            } else {
                Log.d("MenuActivity", "profesorId recuperado de prefs: " + profesorId);
            }
        }

        // ESTUDIANTE: Guarda estudianteId si no está en prefs
        if (rolActualId == 1) { // Estudiante
            long estudianteId = prefs.getLong("estudianteId", -1);
            if (estudianteId == -1 && userId != -1) {
                com.example.tallerandroid.net.apis.ApiEstudianteService apiEstudiante = RetrofitCliente.getCliente().create(com.example.tallerandroid.net.apis.ApiEstudianteService.class);
                apiEstudiante.obtenerPorUsuario(userId).enqueue(new retrofit2.Callback<JsonObject>() {
                    @Override
                    public void onResponse(retrofit2.Call<com.google.gson.JsonObject> call, retrofit2.Response<com.google.gson.JsonObject> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().has("estudianteId")) {
                            long estId = response.body().get("estudianteId").getAsLong();
                            prefs.edit().putLong("estudianteId", estId).apply();
                            Log.d("MenuActivity", "estudianteId recuperado y guardado: " + estId);
                        } else {
                            Log.d("MenuActivity", "No se encontró estudianteId para este usuario");
                        }
                    }
                    @Override
                    public void onFailure(retrofit2.Call<com.google.gson.JsonObject> call, Throwable t) {
                        Log.e("MenuActivity", "Error al consultar estudianteId", t);
                    }
                });
            } else {
                Log.d("MenuActivity", "estudianteId recuperado de prefs: " + estudianteId);
            }
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
        TextView tvLogout = findViewById(R.id.tvLogout);
        tvLogout.setOnClickListener(v -> logout());

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
        String rolesJson = prefs.getString("roles", "[]");

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

        int cantidadRoles = 0;
        try {
            JsonArray arr = JsonParser.parseString(rolesJson).getAsJsonArray();
            cantidadRoles = arr.size();
        } catch (Exception ignored) {}

        if (cantidadRoles == 1) {
            if (rolActualId == 1) { // Estudiante
                menu.add(Menu.NONE, R.id.menu_registrar_profesor, Menu.NONE, "Registrarse como Profesor")
                        .setIcon(R.drawable.ic_profesor);
            } else if (rolActualId == 2) { // Profesor
                menu.add(Menu.NONE, R.id.menu_registrar_estudiante, Menu.NONE, "Registrarse como Estudiante")
                        .setIcon(R.drawable.ic_estudiante);
            }
        }



        navView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
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

            // Opción registrar como profesor
            if (itemId == R.id.menu_registrar_profesor) {
                Intent intent = new Intent(this, com.example.tallerandroid.profesor.ProfesorEspecialidadActivity.class);
                intent.putExtra("userId", prefs.getLong("userId", -1));
                intent.putExtra("registroDesdeDrawer", true);
                startActivity(intent);
                return true;
            }
            // Opción registrar como estudiante
            if (itemId == R.id.menu_registrar_estudiante) {
                registrarEstudianteDesdeDrawer();
                return true;
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
    private void logout() {
        // Borra SharedPreferences
        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
        prefs.edit().clear().apply();
        Intent intent = new Intent(this, com.example.tallerandroid.auth.loginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void registrarEstudianteDesdeDrawer() {
        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
        long userId = prefs.getLong("userId", -1);

        com.google.gson.JsonObject json = new com.google.gson.JsonObject();
        json.addProperty("userId", userId);

        // Llama al endpoint para registrar estudiante
        com.example.tallerandroid.net.apis.ApiEstudianteService apiEstudiante =
                com.example.tallerandroid.net.RetrofitCliente.getCliente()
                        .create(com.example.tallerandroid.net.apis.ApiEstudianteService.class);

        apiEstudiante.registrarDesdeUsuario(json).enqueue(new retrofit2.Callback<com.google.gson.JsonObject>() {
            @Override
            public void onResponse(retrofit2.Call<com.google.gson.JsonObject> call, retrofit2.Response<com.google.gson.JsonObject> response) {
                if (response.isSuccessful()) {
                    // Cambia el rol actual a estudiante y recarga MenuActivity
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putLong("rolActualId", 1);
                    editor.putString("rolActualName", "ESTUDIANTE");
                    editor.apply();

                    // Opcional: limpiar profesorId/estudianteId si quieres forzar recarga
                    editor.remove("profesorId");
                    editor.remove("estudianteId");
                    editor.apply();

                    Intent intent = new Intent(MenuActivity.this, MenuActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MenuActivity.this, "No se pudo registrar como estudiante", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(retrofit2.Call<com.google.gson.JsonObject> call, Throwable t) {
                Toast.makeText(MenuActivity.this, "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }
}