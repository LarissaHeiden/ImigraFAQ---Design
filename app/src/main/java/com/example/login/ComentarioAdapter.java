package com.example.login;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ComentarioAdapter extends ArrayAdapter<Comentario> {

    private final Context context;
    private final ArrayList<Comentario> itens;

    public ComentarioAdapter (Context context, ArrayList<Comentario> itens){

        super(context, R.layout.activity_comentario_adapter, itens);
        this.context = context;
        this.itens = itens;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_comentario_adapter, null, true);

        TextView txtComentario = (TextView) rowView.findViewById(R.id.txtComentario);

        txtComentario.setText(itens.get(position).getComentario());

        return rowView;
    }
}
