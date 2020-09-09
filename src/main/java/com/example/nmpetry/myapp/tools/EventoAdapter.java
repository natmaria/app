package com.example.nmpetry.myapp.tools;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nmpetry.myapp.R;
import com.example.nmpetry.myapp.models.Evento;
import com.example.nmpetry.myapp.views.EventoCadastroActivity;

import java.util.ArrayList;

public class EventoAdapter extends ArrayAdapter<Evento> {

    private final Context context;
    private final ArrayList<Evento> elementos;

    public EventoAdapter(Context context, ArrayList<Evento> elementos){
        super(context, R.layout.item_lista_eventos, elementos);
        this.context = context;
        this.elementos = elementos;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        final Evento objEvento = elementos.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //toda vez que passa por um item da lista, os elementos são associados
        View rowView = inflater.inflate(R.layout.item_lista_eventos, parent, false);

        TextView txtData = rowView.findViewById(R.id.txtData);
        TextView txtVagas = rowView.findViewById(R.id.txtVagas);
        TextView txtNomeEvento = rowView.findViewById(R.id.txtNomeEvento);

        //codigo.setText(elementos.get(position).getId() + "");
        txtData.setText("Data:" +objEvento.getData() + "\n" + Ferramentas.obterDiaSemana(objEvento.getData()));
        txtVagas.setText("Vagas:" + objEvento.getVagas());
        txtNomeEvento.setText(objEvento.getTitulo());

        //clique na linha do ListView
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nova_tela = new Intent(context, EventoCadastroActivity.class);
                nova_tela.putExtra("id_evento", String.valueOf(objEvento.getId()));
                nova_tela.putExtra("objeto_evento", objEvento);
                context.startActivity(nova_tela);
            }
        });

        return rowView;
    }
}

