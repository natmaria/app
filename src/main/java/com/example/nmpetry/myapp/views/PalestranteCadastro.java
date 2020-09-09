package com.example.nmpetry.myapp.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.nmpetry.myapp.R;
import com.example.nmpetry.myapp.controller.PalestranteDao;
import com.example.nmpetry.myapp.models.Palestrante;
import com.example.nmpetry.myapp.tools.Ferramentas;

public class PalestranteCadastro extends AppCompatActivity {
    EditText txtNomePalestrante;
    Context context;
    private String TAG = "PalestranteCadastro";
    Palestrante objPalestrante;
    int codigo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palestrante_cadastro);

        txtNomePalestrante = findViewById(R.id.txtNomePalestrante);
        context = PalestranteCadastro.this;

        Intent infos = getIntent();
        if (infos.hasExtra("objeto_palestrante")) //"objeto_evento
        {
            objPalestrante = (Palestrante) infos.getSerializableExtra("objeto_palestrante");
            if (objPalestrante != null)
            {
                codigo = objPalestrante.getId();
                txtNomePalestrante.setText(objPalestrante.getNome());
            }
        }
        else
        {
            codigo=0;
        }
    }

    //Funcao para inflar o menu na tela
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastro, menu);

        return super.onCreateOptionsMenu(menu);


        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case R.id.action_ok:

                boolean validacao = validarDados();
                    if (validacao == true) {

                        try {
                            Palestrante objPalestrante = new Palestrante();
                            objPalestrante.setNome(txtNomePalestrante.getText().toString());
                            PalestranteDao palestranteD = new PalestranteDao(context);
                            if (codigo == 0) {
                                boolean inserir = palestranteD.inserir(objPalestrante);
                                if (inserir == true)
                                {
                                    Ferramentas.exibirMensagem(context, "Palestrante inserido com sucesso!");
                                }
                                else
                                {
                                    Ferramentas.exibirMensagem(context, "Não foi possível inserir o Palestrante!");
                                }
                                finish();
                            }
                            else
                            {
                                objPalestrante.setId(codigo);
                                boolean alterar = palestranteD.alterar(objPalestrante);
                                if (alterar == true)
                                {
                                    Ferramentas.exibirMensagem(context, "Palestrante alterado com sucesso!");
                                }
                                else
                                {
                                    Ferramentas.exibirMensagem(context, "Não foi possível alterar o Palestrante!");
                                }
                                finish();
                            }

                        } catch (Exception ex) {
                            Ferramentas.ultimoErro = ex.getMessage();
                            Log.e(TAG, ex.getMessage());
                        }
                    } else {
                        Ferramentas.exibirMensagem(context, "O preenchimento de todos os campos é obrigatório!");
                        break;
                    }
            case R.id.action_cancel:
                finish();

        }

        return super.onOptionsItemSelected(item);
    }


        public boolean validarDados()
        {
            if (txtNomePalestrante.getText().length() < 1)
            {
                return false;
            }
            else
                {
                return true;
                }
        }
}


