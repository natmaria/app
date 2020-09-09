package com.example.nmpetry.myapp.views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.nmpetry.myapp.R;
import com.example.nmpetry.myapp.controller.EventoDao;
import com.example.nmpetry.myapp.controller.PalestranteDao;
import com.example.nmpetry.myapp.models.Evento;
import com.example.nmpetry.myapp.models.Palestrante;
import com.example.nmpetry.myapp.tools.Ferramentas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EventoCadastroActivity extends AppCompatActivity {
    EditText txtTituloEvento;
    EditText txtDescricaoEvento;
    EditText txtDataEvento;
    EditText txtVagasEvento;
    Context context;
    ImageButton imgData;
    Palestrante objPalestranteSelecionado;
    private String TAG = "EventoCadastroActivity";
    Evento objEvento;
    int codigo;
    Spinner spiPalestrante;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_cadastro);

        txtTituloEvento = findViewById(R.id.txtTituloEvento);
        txtDescricaoEvento = findViewById(R.id.txtDescricaoEvento);
        txtDataEvento = findViewById(R.id.txtDataEvento);
        txtVagasEvento = findViewById(R.id.txtVagasEvento);
        txtVagasEvento.setText("0");
        context = EventoCadastroActivity.this;
        imgData = findViewById(R.id.btn_calendar);
        spiPalestrante = findViewById(R.id.spn_palestrante);


        imgData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendario = Calendar.getInstance();
                Date data;

                try{
                    if(txtDataEvento.getText().toString().equals("")){
                        calendario = Calendar.getInstance();
                    }else{
                        String dtStart = txtDataEvento.getText().toString();
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            data = format.parse(dtStart);
                            calendario.setTime(data);

                        } catch (ParseException e) {
                            e.printStackTrace();
                            calendario = Calendar.getInstance();
                        }
                    }

                }catch (Exception ex){
                    calendario = Calendar.getInstance();
                }

                int ano = calendario.get(Calendar.YEAR);
                int mes = calendario.get(Calendar.MONTH);
                int dia = calendario.get(Calendar.DAY_OF_MONTH);

                new DatePickerDialog(context, android.R.style.Theme_Material_Dialog_Alert
                        , mDateSetListener, ano, mes, dia).show();
            }
        });
       //preencher o spinner
        PalestranteDao objPalestranteDao = new PalestranteDao(context);
        ArrayList<Palestrante> listaPalestrantes = objPalestranteDao.buscarTodos();
        listaPalestrantes.add(0,new Palestrante(0,"Selecione o Palestrante"));

        final ArrayAdapter<Palestrante> mAdapter = new ArrayAdapter<Palestrante>(this, android.R.layout.simple_spinner_item, listaPalestrantes);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiPalestrante.setAdapter(mAdapter);

        spiPalestrante.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                objPalestranteSelecionado = (Palestrante) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ///abrir evento preenchido
        Intent extras = getIntent();
        Bundle bundle = getIntent().getExtras();
        if (extras.hasExtra("objeto_evento")) //"objeto_evento
        {
            objEvento = (Evento) extras.getSerializableExtra("objeto_evento");
            codigo = objEvento.getId();
            txtTituloEvento.setText(objEvento.getTitulo());
            txtDescricaoEvento.setText(objEvento.getDescricao());
            txtDataEvento.setText(objEvento.getData());
            txtVagasEvento.setText(String.valueOf(objEvento.getVagas()));

            objPalestranteDao = new PalestranteDao(context);
            Palestrante objeto = objPalestranteDao.buscarPalestrante(objEvento.getId_palestrante());
            Ferramentas.selecionaSpinner(spiPalestrante,objeto);

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
                boolean formatoData = Ferramentas.validarFormatoData(txtDataEvento.getText().toString());
                if (formatoData == true) {
                    if (validacao == true) {

                        try {
                            Evento objEvento = new Evento();
                            objEvento.setTitulo(txtTituloEvento.getText().toString());
                            objEvento.setDescricao(txtDescricaoEvento.getText().toString());
                            objEvento.setData(txtDataEvento.getText().toString());
                            objEvento.setVagas(Integer.parseInt(txtVagasEvento.getText().toString()));
                            objEvento.setId_palestrante(objPalestranteSelecionado.getId());


                            EventoDao eventoD = new EventoDao(context);
                            if (codigo == 0) {
                               boolean inserir = eventoD.inserir(objEvento);
                               if (inserir == true)
                               {
                                   Ferramentas.exibirMensagem(context, "Evento inserido com sucesso!");
                               }
                               else
                               {
                                   Ferramentas.exibirMensagem(context, "Não foi possível inserir o Evento!");
                               }
                                finish();
                            }
                            else
                            {
                                objEvento.setId(codigo);
                              boolean alterar = eventoD.alterar(objEvento);
                                if (alterar == true)
                                {
                                    Ferramentas.exibirMensagem(context, "Evento alterado com sucesso!");
                                }
                                else
                                {
                                    Ferramentas.exibirMensagem(context, "Não foi possível alterar o Evento!");
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
                }
                else
                    {
                    Ferramentas.exibirMensagem(context, "A data deve estar no formato xx/xx/xxxx");
                    break;
                }
                    case R.id.action_cancel:
                        finish();

                }

                return super.onOptionsItemSelected(item);
        }

    public boolean validarDados()
    {
        if ( (txtTituloEvento.getText().length() <1) || (txtDescricaoEvento.getText().length() <1) ||
                (txtVagasEvento.getText().toString().equals("")) || (txtVagasEvento.getText().toString().equals("0")) ||
                (objPalestranteSelecionado.getId() == 0) )
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            String data = String.valueOf(String.format("%02d", dayOfMonth)) + "/"+ String.valueOf(String.format("%02d", monthOfYear + 1)) + "/" + String.valueOf(String.format("%02d", year));

            txtDataEvento.setText(data);
        }

    };

}
