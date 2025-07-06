package com.example.tallerandroid.fragments.profesor;

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

public class InicioProfesorFragment extends Fragment {

    private TextView tvNameUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio_profesor, container, false);
        tvNameUser = view.findViewById(R.id.tvIPNameUser);
        SharedPreferences prfs = requireContext().getSharedPreferences("user_session", Context.MODE_PRIVATE);
        tvNameUser.setText(prfs.getString("nameUser", "Usuario"));
        return view;
    }
}