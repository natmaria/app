package com.example.nmpetry.myapp.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nmpetry.myapp.R;

public class MenuActivity extends AppCompatActivity {
    TextView lblUsuario;
    Button btnGerar;
    Button btnCalc;
    Context context;
    Button btnEventos;
    Button btnPalestrantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        context=MenuActivity.this;
        lblUsuario = findViewById(R.id.lblUsuarioNome);
        btnGerar = findViewById(R.id.btnGerador);
        btnCalc = findViewById(R.id.btnCalc);
        btnEventos = findViewById(R.id.btnEventos);
        btnPalestrantes = findViewById(R.id.btnPalestrantes);

        Intent extras = getIntent();
        Bundle bundle = getIntent().getExtras();
        if (extras.hasExtra("usuario_nome"))
        {
            String usuario = extras.getStringExtra("usuario_nome");
            lblUsuario.setText("Usuário logado:" + usuario);
        }

        btnGerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MainActivity.class);
                startActivity(intent);
            }
        });

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,CalcActivity.class);;
                startActivity(intent);
            }
        });

        btnEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ListActivity.class);;
                startActivity(intent);
            }
        });

        btnPalestrantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ListaPalestrante.class);;
                startActivity(intent);
            }
        });
    }
}
