package com.example.josepablo.foursquareapp.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.josepablo.foursquareapp.R;
import com.example.josepablo.foursquareapp.objects.Ubicacion;

import java.util.ArrayList;

/**
 * Created by Jose Pablo on 9/6/2017.
 */

public class RecyclerViewCustomAdapter extends RecyclerView.Adapter<RecyclerViewCustomAdapter.CustomViewHolder> {

    // TODO: (1) DECLARAR ESTRUCUTRA DE DATOS
    private ArrayList<Ubicacion> locations;

    // TODO: 11.- Creamos un miembro derivado de la interfaz creada
    private RecyclerViewClickListener listener;

    // TODO: (2) CONSTRUCTOR
    public RecyclerViewCustomAdapter(ArrayList<Ubicacion> locaciones, RecyclerViewClickListener listener){
        this.locations = locaciones;
        this.listener = listener;
    }

    //TODO: (3) DEFINIMOS EL PATRON VIEWHOLDER CREANDO UNA CLASE ESTÁTICA PARA DEFINIR ELEMENTOS
    public static class CustomViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private TextView tvDescription;
        private ImageView ivImage;

        CustomViewHolder(View vista){
            super(vista);

            tvName = (TextView) vista.findViewById(R.id.tvName);
            tvDescription = (TextView) vista.findViewById(R.id.tvDescription);
            ivImage = (ImageView) vista.findViewById(R.id.ivImage);
        }
    }

    //TODO: (4) SE IMPLEMENTAN MÉTODOS REQUERIDOS
    @Override
    public int getItemCount(){
        return locations.size();
    }

    //TODO: (5) EL MÉTODO SIRVE PARA COLOCAR VALORES
    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position){

        holder.ivImage.setImageResource(locations.get(position).getImg());
        holder.tvName.setText(locations.get(position).getName());
        holder.tvDescription.setText(locations.get(position).getDescription());

    }

    // TODO (6) SE USA
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_element, parent, false);

        //CustomViewHolder customViewHolder = new CustomViewHolder(vista);
        //return customViewHolder;
        // TODO: 13.- Agregamos un objeto RowViewHolder y eliminamos las dos líneas anteriores
        return new RowViewHolder(vista, listener);

    }
}