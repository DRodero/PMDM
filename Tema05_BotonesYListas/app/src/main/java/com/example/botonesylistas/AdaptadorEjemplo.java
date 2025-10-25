package com.example.botonesylistas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorEjemplo extends RecyclerView.Adapter<AdaptadorEjemplo.HolderEjemplo> {
    public static class HolderEjemplo extends RecyclerView.ViewHolder {
        TextView tEjemplo1, tEjemplo2;

        HolderEjemplo(View itemView) {
            super(itemView);
            tEjemplo1 = itemView.findViewById(R.id.tEjemplo1);
            tEjemplo2 = itemView.findViewById(R.id.tEjemplo2);
        }
    }

    private ArrayList<ElementoLista> elementos;
    private Context contexto;

    public AdaptadorEjemplo(Context contexto, ArrayList<ElementoLista> elementos) {
        this.contexto = contexto;
        this.elementos = elementos;
    }

    public void add(ArrayList<ElementoLista> datos) {
        elementos.clear();
        elementos.addAll(datos);
    }

    public void refrescar() {
        notifyDataSetChanged();
    };

    @Override
    public HolderEjemplo onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento, parent, false);
        HolderEjemplo pvh = new HolderEjemplo(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(HolderEjemplo elementoActual, int posición) {
        // Mostramos los textos en la vista de la lista
        String texto1 = elementos.get(posición).getTexto1();
        String texto2 = elementos.get(posición).getTexto2();

        elementoActual.tEjemplo1.setText(texto1);
        elementoActual.tEjemplo2.setText(texto2);

        int color_oscuro = contexto.getResources().getColor(R.color.fondo_oscuro, contexto.getTheme());
        int color_claro = contexto.getResources().getColor(R.color.fondo_claro, contexto.getTheme());

        if(posición % 2 == 0) {
            elementoActual.itemView.setBackgroundColor(color_oscuro);
        }
        else {
            elementoActual.itemView.setBackgroundColor(color_claro);
        }

    }

    @Override
    public int getItemCount() {
        return elementos.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }









}
