package com.example.tallerandroid.fragments.profesor;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tallerandroid.R;
import com.example.tallerandroid.model.Categoria;
import com.example.tallerandroid.model.request.ProfesorRequest;
import com.example.tallerandroid.model.request.TallerCrearRequest;
import com.example.tallerandroid.model.request.TallerImagenRequest;
import com.example.tallerandroid.net.RetrofitCliente;
import com.example.tallerandroid.net.apis.ApiCategoriaService;
import com.example.tallerandroid.net.apis.ApiTallerService;
import com.google.gson.Gson;


import android.Manifest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CrearTallerFragment extends Fragment {

    private Spinner spinnerCategoria;
    private List<Categoria> listaCategorias = new ArrayList<>();

    private Long profesorId;

    private EditText etTitulo, etDescripcion, etDuracion, etPrecio, etCapacidad;
    private Button btnCrearTaller;
    private ImageView ivPreviewImagen;
    private EditText etFechaFinalizacion;
    private String fechaFinalizacionSeleccionada = null;

    private static final int PICK_IMAGE_REQUEST = 1;
    private String imagenBase64 = "";
    private ActivityResultLauncher<String> seleccionarImagenLauncher;
    private ActivityResultLauncher<String> permisosGaleriaLauncher;
    private ProgressBar progressBarCategorias;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Recupera el profesorId de SharedPreferences
        SharedPreferences prefs = requireContext().getSharedPreferences("user_session", Context.MODE_PRIVATE);
        long id = prefs.getLong("profesorId", -1);
        if (id != -1) {
            this.profesorId = id;
            Log.d("CrearTallerFragment", "onCreate: profesorId recuperado de SharedPreferences: " + profesorId);
        } else {
            this.profesorId = null;
            Log.e("CrearTallerFragment", "onCreate: profesorId NO encontrado en SharedPreferences");
        }

        seleccionarImagenLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        mostrarImagenYConvertir(uri);
                    }
                }
        );

        //Permiso para acceder a la galeria
        permisosGaleriaLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted){
                        seleccionarImagenLauncher.launch("image/*");
                    } else {
                        Toast.makeText(getContext(), "Permiso denegado para acceder a la galería", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crear_taller, container, false);

        spinnerCategoria = view.findViewById(R.id.spinnerCategoria);
        progressBarCategorias = view.findViewById(R.id.progressBarCategorias);
        etTitulo = view.findViewById(R.id.etTituloTaller);
        etDescripcion = view.findViewById(R.id.etDescripcionTaller);
        etDuracion = view.findViewById(R.id.etDuracionHoras);
        etPrecio = view.findViewById(R.id.etPrecio);
        etCapacidad = view.findViewById(R.id.etCapacidad);
        btnCrearTaller = view.findViewById(R.id.btnCrearTaller);
        ivPreviewImagen = view.findViewById(R.id.ivPreviewImagen);
        etFechaFinalizacion = view.findViewById(R.id.etFechaFinalizacion);

        etFechaFinalizacion.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    requireContext(),
                    (view1, year, month, dayOfMonth) -> {
                        // Formato yyyy-MM-dd
                        String fecha = String.format(Locale.US, "%04d-%02d-%02d", year, month + 1, dayOfMonth);
                        etFechaFinalizacion.setText(fecha);
                        fechaFinalizacionSeleccionada = fecha;
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        });

        btnCrearTaller.setOnClickListener(v -> crearTaller());

        Button btnSeleccionarImagen = view.findViewById(R.id.btnSeleccionarImagen);
        btnSeleccionarImagen.setOnClickListener(v -> verificarPermisoGaleria());

        cargarCategorias();

        return view;
    }

    private void verificarPermisoGaleria() {
        String permiso;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permiso = Manifest.permission.READ_MEDIA_IMAGES;
        } else {
            permiso = Manifest.permission.READ_EXTERNAL_STORAGE;
        }

        if (ContextCompat.checkSelfPermission(requireContext(), permiso)
                == PackageManager.PERMISSION_GRANTED) {
            seleccionarImagenLauncher.launch("image/*");
        } else {
            permisosGaleriaLauncher.launch(permiso);
        }
    }



    private void mostrarImagenYConvertir(Uri uri) {
        try {
            InputStream inputStream = requireContext().getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ivPreviewImagen.setImageBitmap(bitmap);
            ivPreviewImagen.setVisibility(View.VISIBLE);
            imagenBase64 = convertirBitmapABase64(bitmap);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error al cargar imagen", Toast.LENGTH_SHORT).show();
        }
    }

    private String convertirBitmapABase64(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 30, baos); // Comprime a JPEG calidad 30
        byte[] imageBytes = baos.toByteArray();
        return "data:image/jpeg;base64," + Base64.encodeToString(imageBytes, Base64.NO_WRAP);
    }

    private void crearTaller(){
        String titulo = etTitulo.getText().toString().trim();
        String descripcion = etDescripcion.getText().toString().trim();
        String duracionStr = etDuracion.getText().toString().trim();
        String precioStr = etPrecio.getText().toString().trim();
        String capacidadStr = etCapacidad.getText().toString().trim();

        if (titulo.isEmpty() || descripcion.isEmpty() || duracionStr.isEmpty() ||
                precioStr.isEmpty() || capacidadStr.isEmpty() || listaCategorias.isEmpty()) {
            Toast.makeText(getContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        if (fechaFinalizacionSeleccionada == null || fechaFinalizacionSeleccionada.isEmpty()) {
            Toast.makeText(getContext(), "Selecciona la fecha de finalización", Toast.LENGTH_SHORT).show();
            return;
        }
        // Validación robusta de fecha de finalización
        LocalDate fechaFinal;
        try {
            fechaFinal = LocalDate.parse(fechaFinalizacionSeleccionada);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Formato de fecha inválido (YYYY-MM-DD)", Toast.LENGTH_SHORT).show();
            return;
        }
        LocalDate hoy = LocalDate.now();
        long diasDiferencia = ChronoUnit.DAYS.between(hoy, fechaFinal);

        if (fechaFinal.isBefore(hoy)) {
            Toast.makeText(getContext(), "La fecha de finalización no puede ser anterior a hoy", Toast.LENGTH_SHORT).show();
            return;
        }
        if (diasDiferencia <= 2) {
            Toast.makeText(getContext(), "Advertencia: El margen entre hoy y la fecha de finalización es muy corto (" + diasDiferencia + " día(s))", Toast.LENGTH_LONG).show();
            return;
        }

        int duracion = Integer.parseInt(duracionStr);
        double precio = Double.parseDouble(precioStr);
        int capacidad = Integer.parseInt(capacidadStr);
        int pos = spinnerCategoria.getSelectedItemPosition();
        Categoria categoriaSeleccionada = listaCategorias.get(pos);

        TallerImagenRequest imagen = new TallerImagenRequest();
        imagen.setImagenBase64(imagenBase64);
        imagen.setOrden(1);

        List<TallerImagenRequest> imagenes = new ArrayList<>();
        imagenes.add(imagen);

        TallerCrearRequest request = new TallerCrearRequest();
        request.setTitulo(titulo);
        request.setDescripcion(descripcion);
        request.setDuracionHoras(duracion);
        request.setPrecio(precio);
        request.setCapacidad(capacidad);
        request.setCategoria(categoriaSeleccionada);
        request.setImagenes(imagenes);
        request.setFechaFinalizacion(fechaFinal.toString());


        Log.d("CrearTallerFragment", "crearTaller: profesorId usado para request: " + profesorId);

        if (profesorId != null) {
            request.setProfesor(new ProfesorRequest(profesorId));
        } else {
            Log.e("CrearTallerFragment", "crearTaller: profesorId es NULL, no se enviará el campo profesor");
            Toast.makeText(getContext(), "No se encontró el ID del profesor. Vuelve a iniciar sesión.", Toast.LENGTH_LONG).show();
            return;
        }

        Gson gson = new Gson();
        Log.d("CrearTaller", "JSON enviado: " + gson.toJson(request));

        ApiTallerService api = RetrofitCliente.getCliente().create(ApiTallerService.class);
        api.crearTaller(request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Taller creado correctamente", Toast.LENGTH_SHORT).show();
                    limpiarFormulario();
                } else {
                    Toast.makeText(getContext(), "Error al crear taller", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Error de red", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void limpiarFormulario() {
        etTitulo.setText("");
        etDescripcion.setText("");
        etDuracion.setText("");
        etPrecio.setText("");
        etCapacidad.setText("");
        spinnerCategoria.setSelection(0);
        etFechaFinalizacion.setText("");
        fechaFinalizacionSeleccionada = null;
    }

    private void cargarCategorias() {
        progressBarCategorias.setVisibility(View.VISIBLE);
        spinnerCategoria.setEnabled(false);

        ApiCategoriaService api = RetrofitCliente.getCliente().create(ApiCategoriaService.class);
        api.listarCategorias().enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                progressBarCategorias.setVisibility(View.GONE);
                spinnerCategoria.setEnabled(true);
                if (response.isSuccessful() && response.body() != null) {
                    listaCategorias = response.body();
                    List<String> nombres = new ArrayList<>();
                    for (Categoria cat : listaCategorias) {
                        nombres.add(cat.getNombre());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, nombres);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCategoria.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                progressBarCategorias.setVisibility(View.GONE);
                spinnerCategoria.setEnabled(true);
                Toast.makeText(requireContext(), "Error al Cargar las categorias", Toast.LENGTH_SHORT).show();
            }
        });
    }
}