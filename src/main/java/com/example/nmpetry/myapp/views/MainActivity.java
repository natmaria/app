package com.example.nmpetry.myapp.views;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import android.content.Context;

import com.example.nmpetry.myapp.tools.Ferramentas;
import com.example.nmpetry.myapp.R;

public class MainActivity extends AppCompatActivity {

    Button btnGerarNumero;
    TextView lblNumeroGerado;
    Button btnGeraMensagem;
    Context context;
    TextView lblUsuario;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        btnGerarNumero = findViewById(R.id.btnGerar);
        lblNumeroGerado = findViewById(R.id.lblNumero);
        btnGeraMensagem = findViewById(R.id.btnMensagem);

        final ArrayList<Integer> numeros = new ArrayList<Integer>();

        btnGerarNumero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                numeros.clear();
                int x=0;

                List<Integer> numerosSorteados = new ArrayList<Integer>(
                        Arrays.asList(10,5,53,4,54,23,33,24,51,17,42,52,27,27,30,37,43,3,6,13,56,32,
                                44,2,16,29,36,41,18,35,50));

                while (x<6) {
                    int num = random.nextInt(60) + 1;
                    if (numerosSorteados.contains(num)) {
                        if (!numeros.contains(num)) {
                            numeros.add(num);
                            x++;
                        }

                    }
        }

                Ferramentas.exibirMensagem(context,"Números gerados com sucesso!");
            }
        });

        btnGeraMensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Collections.sort(numeros);
                lblNumeroGerado.setText("NÚMEROS = " + numeros);
            }
        });
    }
}
