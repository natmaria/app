package com.example.nmpetry.myapp.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nmpetry.myapp.R;
import com.example.nmpetry.myapp.controller.EventoDao;
import com.example.nmpetry.myapp.models.Evento;
import com.example.nmpetry.myapp.tools.EventoAdapter;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ListView listaEventos;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = ListActivity.this;
        listaEventos = findViewById(R.id.ltvEventos);

        atualizarLista();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddEvento);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tela = new Intent(context, EventoCadastroActivity.class);
                startActivity(tela);
            }
        });
    }

    protected void onResume()
    {
        super.onResume();
        atualizarLista();
    }

    void atualizarLista()
    {
        EventoDao objEventoDao = new EventoDao(context);
        ArrayList<Evento> lista = objEventoDao.buscarTodos();

        ArrayAdapter adapter = new EventoAdapter(this, lista);
        listaEventos.setAdapter(adapter);
    }

}
