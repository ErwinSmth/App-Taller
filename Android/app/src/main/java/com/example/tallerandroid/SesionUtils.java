package com.example.tallerandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.tallerandroid.net.RetrofitCliente;
import com.example.tallerandroid.net.apis.ApiUserService;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SesionUtils {

    public static void actualizarRolesYPermisosYRecargarMenu(Context context, long userId, long nuevoRolId, String nuevoRolName) {
        ApiUserService apiUserService = RetrofitCliente.getCliente().create(ApiUserService.class);
        apiUserService.obtenerInfoUsuario(userId).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    JsonObject body = response.body();
                    SharedPreferences prefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putLong("rolActualId", nuevoRolId);
                    editor.putString("rolActualName", nuevoRolName);
                    editor.putString("roles", body.get("roles").toString());
                    editor.putString("permisos", body.get("permisos").toString());
                    editor.apply();

                    Intent intent = new Intent(context, MenuActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "No se pudo actualizar roles/permisos", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(context, "Error de red al actualizar roles/permisos", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
