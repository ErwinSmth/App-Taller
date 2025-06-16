package com.example.tallerandroid.rol.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tallerandroid.R;
import com.google.gson.JsonObject;


import java.util.List;

public class SeleccionRolAdapter extends RecyclerView.Adapter<SeleccionRolAdapter.ViewHolder> {

    private List<JsonObject> roles;
    private int selectedPos = -1;

    public SeleccionRolAdapter(List<JsonObject> roles) {
        this.roles = roles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rol, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JsonObject rol = roles.get(position);
        holder.radBtn.setText(rol.get("rolName").getAsString());
        holder.radBtn.setChecked(position == selectedPos);
        holder.radBtn.setOnClickListener(v -> {
            int prev = selectedPos;
            selectedPos = holder.getAdapterPosition();
            notifyItemChanged(prev);
            notifyItemChanged(selectedPos);
        });
    }

    @Override
    public int getItemCount() {
        return roles.size();
    }

    public JsonObject getSelectecRol(){
        if (selectedPos >= 0 && selectedPos < roles.size()){
            return roles.get(selectedPos);
        }
        return null;
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        RadioButton radBtn;
        ViewHolder(View itemView){
            super(itemView);
            radBtn = itemView.findViewById(R.id.rbRol);
        }
    }
}
