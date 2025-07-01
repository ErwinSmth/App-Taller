package com.example.tallerandroid.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tallerandroid.R;
import com.example.tallerandroid.categoria.CategoriaAdapter;
import com.example.tallerandroid.model.Categoria;
import com.example.tallerandroid.net.RetrofitCliente;
import com.example.tallerandroid.net.apis.ApiCategoriaService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscarTallerFragment extends Fragment {

    private RecyclerView recyclerView;
    private CategoriaAdapter adapter;
    private EditText etBuscar;
    private ImageButton btnBuscar;
    private List<Categoria> categorias = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buscar_taller, container, false);
        recyclerView = view.findViewById(R.id.recyclerCategorias);
        etBuscar = view.findViewById(R.id.etBuscar);
        btnBuscar = view.findViewById(R.id.btnBuscar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        btnBuscar.setOnClickListener(v -> {
            String query = etBuscar.getText().toString().trim();
            // A implementar mas adelante
            Toast.makeText(getContext(), "Buscar: " + query, Toast.LENGTH_SHORT).show();
        });

        adapter = new CategoriaAdapter(categorias, categoria -> {

            //Navegar al fragment de lista de taller por categoria
            Bundle args = new Bundle();
            args.putLong("categoriaId", categoria.getCategoriaId());
            ListarTallerCategoriaFragment fragment = new ListarTallerCategoriaFragment();
            fragment.setArguments(args);
            requireActivity().getSupportFragmentManager()
                            .beginTransaction()
                                    .replace(R.id.fragment_container, fragment)
                                            .addToBackStack(null)
                                                    .commit();

            Toast.makeText(getContext(), "Seleccionaste: " + categoria.getNombre(), Toast.LENGTH_SHORT).show();
        });
        recyclerView.setAdapter(adapter);
        cargarCategorias();
        return view;
    }

    private void cargarCategorias(){
        ApiCategoriaService api = RetrofitCliente.getCliente().create(ApiCategoriaService.class);
        api.listarCategorias().enqueue(new Callback<List<Categoria>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<List<Categoria>> call, @NonNull Response<List<Categoria>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categorias.clear();
                    categorias.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "No se pudieron cargar las categorías", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Categoria>> call, Throwable t) {
                Toast.makeText(getContext(), "Error de red", Toast.LENGTH_SHORT).show();

            }
        });
    }
}