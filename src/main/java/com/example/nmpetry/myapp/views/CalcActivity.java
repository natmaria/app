package com.example.nmpetry.myapp.views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nmpetry.myapp.R;
import com.example.nmpetry.myapp.tools.Ferramentas;

public class CalcActivity extends AppCompatActivity {
    EditText txtNumero;
    Button btnSomar, btnSub, btnMult, btnDiv, btnLimpar, btnResult;
    double numeroGuardado = 0;
    String operacao = "";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        context = CalcActivity.this;
        txtNumero = findViewById(R.id.txtNumeroInf);
        btnSomar = findViewById(R.id.btnSomar);
        btnSub = findViewById(R.id.btnSub);
        btnMult = findViewById(R.id.btnMult);
        btnDiv = findViewById(R.id.btnDiv);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnResult = findViewById(R.id.btnResult);


        txtNumero.requestFocus();

        btnSomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtNumero.getText().toString().equals(""))
                {
                    txtNumero.setText("0");
                }
               numeroGuardado= Double.parseDouble(txtNumero.getText().toString());
               operacao = "+";
               txtNumero.setText("");
               txtNumero.requestFocus();
               btnSomar.setVisibility(View.INVISIBLE);
               btnSub.setVisibility(View.INVISIBLE);
               btnMult.setVisibility(View.INVISIBLE);
               btnDiv.setVisibility(View.INVISIBLE);
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtNumero.getText().toString().equals(""))
                {
                    txtNumero.setText("0");
                }
                numeroGuardado= Double.parseDouble(txtNumero.getText().toString());
                operacao = "-";
                txtNumero.setText("");
                txtNumero.requestFocus();
                btnSomar.setVisibility(View.INVISIBLE);
                btnSub.setVisibility(View.INVISIBLE);
                btnMult.setVisibility(View.INVISIBLE);
                btnDiv.setVisibility(View.INVISIBLE);
            }
        });

        btnMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtNumero.getText().toString().equals(""))
                {
                    txtNumero.setText("0");
                }
                numeroGuardado= Double.parseDouble(txtNumero.getText().toString());
                operacao = "x";
                txtNumero.setText("");
                txtNumero.requestFocus();
                btnSomar.setVisibility(View.INVISIBLE);
                btnSub.setVisibility(View.INVISIBLE);
                btnMult.setVisibility(View.INVISIBLE);
                btnDiv.setVisibility(View.INVISIBLE);
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtNumero.getText().toString().equals(""))
                {
                    txtNumero.setText("0");
                }
                numeroGuardado= Double.parseDouble(txtNumero.getText().toString());
                operacao = "/";
                txtNumero.setText("");
                txtNumero.requestFocus();
                btnSomar.setVisibility(View.INVISIBLE);
                btnSub.setVisibility(View.INVISIBLE);
                btnMult.setVisibility(View.INVISIBLE);
                btnDiv.setVisibility(View.INVISIBLE);
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            txtNumero.setText("");
            numeroGuardado =0;
            operacao="";
            btnSub.setVisibility(View.VISIBLE);
            btnSomar.setVisibility(View.VISIBLE);
            btnMult.setVisibility(View.VISIBLE);
            btnDiv.setVisibility(View.VISIBLE);
            }
        });

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtNumero.getText().toString().equals(""))
                {
                    txtNumero.setText("0");
                }
                double result = 0;
                double numAtual = Double.parseDouble(txtNumero.getText().toString());
               switch (operacao) {
                   case "+":
                       result = numeroGuardado + numAtual;
                       txtNumero.setText(String.valueOf(result));
                       numeroGuardado = result;
                       break;
                   case "-":
                       result = numeroGuardado - numAtual;
                       txtNumero.setText(String.valueOf(result));
                       numeroGuardado = result;
                       break;
                   case "x":
                       result = numeroGuardado*numAtual;
                       txtNumero.setText(String.valueOf(result));
                       numeroGuardado=result;
                       break;
                   case "/":
                       if (numAtual==0)
                       {
                           Ferramentas.exibirMensagem(context,"ERRO: Divisão por 0");
                           txtNumero.setText("");
                           numeroGuardado=0;
                           operacao="";
                       }
                       else
                       {
                           result=numeroGuardado/numAtual;
                           txtNumero.setText(String.valueOf(result));
                           numeroGuardado=result;
                           break;
                       }
               }
                   btnSub.setVisibility(View.VISIBLE);
                   btnSomar.setVisibility(View.VISIBLE);
                   btnMult.setVisibility(View.VISIBLE);
                   btnDiv.setVisibility(View.VISIBLE);

               }
        });
    }
}
