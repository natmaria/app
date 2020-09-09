package com.example.nmpetry.myapp.views;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nmpetry.myapp.R;
import com.example.nmpetry.myapp.controller.PalestranteDao;
import com.example.nmpetry.myapp.models.Palestrante;

import java.util.ArrayList;

public class ListaPalestrante extends AppCompatActivity {

    ListView listaPalestrantes;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_palestrante);

        context = ListaPalestrante.this;
        listaPalestrantes = findViewById(R.id.ltvPalestrantes);

        atualizarLista();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddPalestrante);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tela = new Intent(context, PalestranteCadastro.class);
                startActivity(tela);
            }
        });

        listaPalestrantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Palestrante objeto = (Palestrante) parent.getAdapter().getItem(position);
                //Aqui podemos chamar a tela de cadastro de palestrantes
                Intent nova_tela = new Intent(context, PalestranteCadastro.class);
                nova_tela.putExtra("id_palestrante", String.valueOf(objeto.getId()));
                nova_tela.putExtra("objeto_palestrante", objeto);
                context.startActivity(nova_tela);
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
        PalestranteDao objPalestranteDao = new PalestranteDao(context);
        ArrayList<Palestrante> lista = objPalestranteDao.buscarTodos();

        ArrayAdapter<Palestrante> adapter = new ArrayAdapter<Palestrante>(this, android.R.layout.simple_list_item_1, lista);
        listaPalestrantes.setAdapter(adapter);
    }
}
