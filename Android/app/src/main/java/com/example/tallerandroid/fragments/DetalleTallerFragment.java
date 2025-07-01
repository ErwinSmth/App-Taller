package com.example.tallerandroid.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tallerandroid.R;
import com.example.tallerandroid.model.Persona;
import com.example.tallerandroid.model.TallerDetalle;
import com.example.tallerandroid.model.TallerResumen;
import com.example.tallerandroid.net.RetrofitCliente;
import com.example.tallerandroid.net.apis.ApiInscripcionService;
import com.example.tallerandroid.net.apis.ApiProfesorService;
import com.example.tallerandroid.net.apis.ApiTallerService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleTallerFragment extends Fragment {

    private Long tallerId;
    private ImageView ivImagenTaller;
    private TextView tvTituloTaller, tvCategoriaTaller, tvDescripcionTaller, tvCapacidadPrecio, tvProfesor;
    private Button btnInscribirse;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tallerId = getArguments().getLong("tallerId", -1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_taller, container, false);

        ivImagenTaller = view.findViewById(R.id.ivImagenTaller);
        tvTituloTaller = view.findViewById(R.id.tvTituloTaller);
        tvCategoriaTaller = view.findViewById(R.id.tvCategoriaTaller);
        tvDescripcionTaller = view.findViewById(R.id.tvDescripcionTaller);
        tvCapacidadPrecio = view.findViewById(R.id.tvCapacidadPrecio);
        tvProfesor = view.findViewById(R.id.tvProfesor);
        btnInscribirse = view.findViewById(R.id.btnInscribirse);

        cargarDetalleTaller();

        btnInscribirse.setOnClickListener(v -> inscribirseEnTaller());

        return view;
    }

    private void cargarDetalleTaller(){
        ApiTallerService api = RetrofitCliente.getCliente().create(ApiTallerService.class);
        api.getTallerById(tallerId).enqueue(new Callback<TallerDetalle>() {
            @Override
            public void onResponse(Call<TallerDetalle> call, Response<TallerDetalle> response) {
                if (response.isSuccessful() && response.body() != null) {
                    TallerDetalle taller = response.body();

                    tvTituloTaller.setText(taller.getTitulo());
                    tvCategoriaTaller.setText("Categoría: " + taller.getCategoria().getNombre());
                    tvDescripcionTaller.setText(taller.getDescripcion());
                    tvCapacidadPrecio.setText("Capacidad: " + taller.getCapacidad() + " | S/ " + taller.getPrecio());

                    // Imagen principal
                    if (taller.getImagenes() != null && !taller.getImagenes().isEmpty()) {
                        String base64 = taller.getImagenes().get(0).getImagenBase64();
                        if (base64 != null && base64.startsWith("data:image")) {
                            base64 = base64.substring(base64.indexOf(",") + 1);
                        }
                        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        ivImagenTaller.setImageBitmap(bitmap);
                    } else {
                        ivImagenTaller.setImageResource(R.drawable.ic_launcher_background);
                    }

                    // Obtener y mostrar nombre completo del profesor
                    if (taller.getProfesor() != null && taller.getProfesor().getProfesorId() != null) {
                        cargarInfoProfesor(taller.getProfesor().getProfesorId());
                    } else {
                        tvProfesor.setText("Profesor: -");
                    }
                } else {
                    Toast.makeText(getContext(), "No se pudo cargar el taller", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TallerDetalle> call, Throwable t) {
                Toast.makeText(getContext(), "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void inscribirseEnTaller(){
        SharedPreferences prefs = requireContext().getSharedPreferences("user_session", Context.MODE_PRIVATE);
        long estudianteId = prefs.getLong("estudianteId", -1);
        if (estudianteId == -1){
            Toast.makeText(getContext(), "No se encontre el estudiante. Vuelva a iniciar sesion", Toast.LENGTH_SHORT).show();
            return;
        }
        ApiInscripcionService api = RetrofitCliente.getCliente().create(ApiInscripcionService.class);
        api.inscribirEstudiante(tallerId, estudianteId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Inscripción exitosa", Toast.LENGTH_SHORT).show();
                    btnInscribirse.setEnabled(false);
                    btnInscribirse.setText("Inscrito");
                } else {
                    Toast.makeText(getContext(), "No se pudo inscribir. ¿Ya estás inscrito?", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Error de red", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void cargarInfoProfesor(Long profesorId){
        ApiProfesorService api = RetrofitCliente.getCliente().create(ApiProfesorService.class);
        api.obtenerPersonaPorProfesorId(profesorId).enqueue(new Callback<Persona>() {
            @Override
            public void onResponse(Call<Persona> call, Response<Persona> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Persona persona = response.body();
                    String nombreCompleto = persona.getNombres() + " " + persona.getApellidos();
                    tvProfesor.setText("Profesor: " + nombreCompleto);
                } else {
                    tvProfesor.setText("Profesor: -");
                }
            }

            @Override
            public void onFailure(Call<Persona> call, Throwable t) {
                tvProfesor.setText("Profesor: -");
            }
        });
    }
}