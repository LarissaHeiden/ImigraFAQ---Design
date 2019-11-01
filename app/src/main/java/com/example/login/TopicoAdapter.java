package com.example.login;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class TopicoAdapter extends ArrayAdapter<Topico> {

    private final Context context;
    private final ArrayList<Topico> itens;

    public TopicoAdapter(Context context, ArrayList<Topico> itens){

        super(context, R.layout.activity_topico, itens);
        this.context = context;
        this.itens = itens;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_topico, null, true);

        TextView Pergunta = (TextView) rowView.findViewById(R.id.P1);
        TextView Resposta = (TextView) rowView.findViewById(R.id.R1);

        Pergunta.setText(itens.get(position).getPergunta());
        Resposta.setText(itens.get(position).getResposta());

        return rowView;
    }
}
