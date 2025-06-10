package com.example.tallerandroid.profesor.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tallerandroid.R;
import com.example.tallerandroid.model.Especialidad;

import java.util.ArrayList;
import java.util.List;

public class EspecialidadAdapter extends RecyclerView.Adapter<EspecialidadAdapter.ViewHolder> {

    private List<Especialidad> especialidades;
    private List<Especialidad> seleccionadas = new ArrayList<>();

    public EspecialidadAdapter(List<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_especialidad, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Especialidad esp = especialidades.get(position);
        holder.tvNombre.setText(esp.getNombre());
        holder.cbSeleccionar.setOnCheckedChangeListener(null);
        holder.cbSeleccionar.setChecked(seleccionadas.contains(esp));
        holder.cbSeleccionar.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (!seleccionadas.contains(esp)) seleccionadas.add(esp);
            } else {
                seleccionadas.remove(esp);
            }
        });
    }

    @Override
    public int getItemCount() {
        return especialidades.size();
    }

    public void setEspecialidades(List<Especialidad> especialidades) {
        this.especialidades = especialidades;
        notifyDataSetChanged();
    }

    public List<Especialidad> getEspecialidadesSeleccionadas() {
        return seleccionadas;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        CheckBox cbSeleccionar;

        ViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreEspecialidad);
            cbSeleccionar = itemView.findViewById(R.id.cbSeleccionarEspecialidad);
        }
    }

}
