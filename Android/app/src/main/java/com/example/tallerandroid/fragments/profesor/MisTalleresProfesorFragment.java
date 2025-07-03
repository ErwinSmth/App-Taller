package com.example.tallerandroid.fragments.profesor;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tallerandroid.R;
import com.example.tallerandroid.model.TallerResumen;
import com.example.tallerandroid.net.RetrofitCliente;
import com.example.tallerandroid.net.apis.ApiTallerService;
import com.example.tallerandroid.profesor.adapters.TallerProfesorAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MisTalleresProfesorFragment extends Fragment {

    private RecyclerView recyclerView;
    private TallerProfesorAdapter adapter;
    private List<TallerResumen> talleres = new ArrayList<>();
    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mis_talleres_profesor, container, false);

        recyclerView = view.findViewById(R.id.recyclerTalleresProfesor);
        progressBar = view.findViewById(R.id.progressBarTalleres);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inicializa el adapter con una lista vacía
        talleres = new ArrayList<>();
        adapter = new TallerProfesorAdapter(talleres, new TallerProfesorAdapter.onTallerActionListener() {
            @Override
            public void onEditar(TallerResumen taller) {
                // Acción editar
            }
            @Override
            public void onCompletar(TallerResumen taller) {
                completarTaller(taller.getTallerId());
            }
        });
        recyclerView.setAdapter(adapter);

        cargarTalleres();

        return view;
    }

    private void cargarTalleres() {
        progressBar.setVisibility(View.VISIBLE); // Mostrar ProgressBar
        recyclerView.setVisibility(View.GONE);

        SharedPreferences prefs = requireContext().getSharedPreferences("user_session", Context.MODE_PRIVATE);
        long profesorId = prefs.getLong("profesorId", -1);

        ApiTallerService api = RetrofitCliente.getCliente().create(ApiTallerService.class);
        api.listarTalleresPorProfesor(profesorId).enqueue(new Callback<List<TallerResumen>>() {
            @Override
            public void onResponse(Call<List<TallerResumen>> call, Response<List<TallerResumen>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    progressBar.setVisibility(View.GONE); // Ocultar ProgressBar
                    recyclerView.setVisibility(View.VISIBLE); // Mostrar lista

                    talleres.clear();
                    talleres.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "No se pudieron cargar los talleres", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TallerResumen>> call, Throwable t) {

                progressBar.setVisibility(View.GONE); // Ocultar ProgressBar
                recyclerView.setVisibility(View.VISIBLE); // Mostrar lista (aunque esté vacía)

                Toast.makeText(getContext(), "Error de red", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void completarTaller(Long tallerId) {
        ApiTallerService api = RetrofitCliente.getCliente().create(ApiTallerService.class);
        api.completarTaller(tallerId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Taller completado correctamente", Toast.LENGTH_SHORT).show();
                    cargarTalleres(); // Refresca la lista
                } else {
                    Toast.makeText(getContext(), "No se pudo completar el taller", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }
}