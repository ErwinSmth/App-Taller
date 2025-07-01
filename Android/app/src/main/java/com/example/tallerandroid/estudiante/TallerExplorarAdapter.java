package com.example.tallerandroid.estudiante;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tallerandroid.R;
import com.example.tallerandroid.model.TallerResumen;

import java.util.List;

public class TallerExplorarAdapter extends RecyclerView.Adapter<TallerExplorarAdapter.TallerViewHolder> {

    public interface onTallerClickListener{
        void onTallerClick(TallerResumen taller);
    }

    private List<TallerResumen> talleres;
    private onTallerClickListener listener;

    public TallerExplorarAdapter(List<TallerResumen> talleres, onTallerClickListener listener) {
        this.talleres = talleres;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TallerExplorarAdapter.TallerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_taller_profesor, parent, false);
        return new TallerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TallerExplorarAdapter.TallerViewHolder holder, int position) {
        TallerResumen taller = talleres.get(position);
        holder.tvTitulo.setText(taller.getTitulo());
        holder.tvCategoria.setText(taller.getCategoriaNombre());
        holder.tvCapacidadPrecio.setText("Capacidad: " + taller.getCapacidad() + " | S/ " + taller.getPrecio());

        // Imagen (usa la primera si existe)
        if (taller.getImagenes() != null && !taller.getImagenes().isEmpty()) {
            String base64 = taller.getImagenes().get(0).getImagenBase64();
            if (base64 != null && base64.startsWith("data:image")) {
                base64 = base64.substring(base64.indexOf(",") + 1);
            }
            byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.ivImagen.setImageBitmap(bitmap);
        } else {
            holder.ivImagen.setImageResource(R.drawable.ic_launcher_background);
        }

        // Oculta botones de edición/eliminación si existen en el layout
        if (holder.btnEditar != null) holder.btnEditar.setVisibility(View.GONE);
        if (holder.btnEliminar != null) holder.btnEliminar.setVisibility(View.GONE);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onTallerClick(taller);
        });
    }

    @Override
    public int getItemCount() {
        return talleres.size();
    }

    public static class TallerViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagen;
        TextView tvTitulo, tvCategoria, tvCapacidadPrecio;
        View btnEditar, btnEliminar;

        public TallerViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.ivTallerImagen);
            tvTitulo = itemView.findViewById(R.id.tvTituloTaller);
            tvCategoria = itemView.findViewById(R.id.tvCategoriaTaller);
            tvCapacidadPrecio = itemView.findViewById(R.id.tvCapacidadPrecio);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}
