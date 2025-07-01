package com.example.tallerandroid.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tallerandroid.R;
import com.example.tallerandroid.estudiante.TallerExplorarAdapter;
import com.example.tallerandroid.model.TallerResumen;
import com.example.tallerandroid.net.RetrofitCliente;
import com.example.tallerandroid.net.apis.ApiTallerService;
import com.example.tallerandroid.profesor.adapters.TallerProfesorAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListarTallerCategoriaFragment extends Fragment {

    private Long categoriaId;
    private RecyclerView recyclerView;
    private TallerExplorarAdapter adapter;
    private List<TallerResumen> talleres = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            categoriaId = getArguments().getLong("categoriaId", -1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listar_taller_categoria, container, false);

        recyclerView = view.findViewById(R.id.recyclerTalleresCategoria);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TallerExplorarAdapter(talleres, taller -> {
            //Abrir el fragment detalleTallerFragment pasando el tallerId
            Bundle args = new Bundle();
            args.putLong("tallerId", taller.getTallerId());
            DetalleTallerFragment detalleFragment = new DetalleTallerFragment();
            detalleFragment.setArguments(args);
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,detalleFragment)
                    .addToBackStack(null)
                    .commit();
        });
        recyclerView.setAdapter(adapter);
        cargarTalleresPorCategoria();
        return view;
    }

    private void cargarTalleresPorCategoria(){
        ApiTallerService api = RetrofitCliente.getCliente().create(ApiTallerService.class);
        api.listarTallerPorCategoria(categoriaId).enqueue(new Callback<List<TallerResumen>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<TallerResumen>> call, Response<List<TallerResumen>> response) {
                if (response.isSuccessful() && response.body() != null){
                    talleres.clear();
                    talleres.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "No se pudieron cargar los talleres", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TallerResumen>> call, Throwable t) {
                Toast.makeText(getContext(), "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }
}