package com.example.tallerandroid.fragments.estudiante;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tallerandroid.R;

public class InicioEstudianteFragment extends Fragment {


    private TextView tvNameUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio_estudiante, container, false);
        tvNameUser = view.findViewById(R.id.tvINameUser);
        SharedPreferences prfs = requireContext().getSharedPreferences("user_session", Context.MODE_PRIVATE);
        tvNameUser.setText(prfs.getString("nameUser", "Usuario"));
        return view;

    }
}