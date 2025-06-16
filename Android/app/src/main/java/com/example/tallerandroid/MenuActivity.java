package com.example.tallerandroid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);

        long userId = prefs.getLong("userId", -1);
        String nameUser = prefs.getString("nameUser", "N/A");
        String roles = prefs.getString("roles", "[]");
        String permisos = prefs.getString("permisos", "[]");
        long rolActualId = prefs.getLong("rolActualId", -1);
        String rolActualName = prefs.getString("rolActualName", "N/A");

        String info = "userId: " + userId +
                "\nnameUser: " + nameUser +
                "\nroles: " + roles +
                "\npermisos: " + permisos +
                "\nrolActualId: " + rolActualId +
                "\nrolActualName: " + rolActualName;

        TextView tv = findViewById(R.id.tvMenuInfo);
        if (tv != null) {
            tv.setText(info);
        }

        Log.d("MenuActivity", info);
    }
}