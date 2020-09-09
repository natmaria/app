package com.example.nmpetry.myapp.views;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.content.Intent;

import com.example.nmpetry.myapp.tools.Ferramentas;
import com.example.nmpetry.myapp.R;

public class LoginActivity extends AppCompatActivity {

    EditText txtUsuario;
    EditText txtSenha;
    Button btnLogin;
    Context context;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = LoginActivity.this;

        txtUsuario = findViewById(R.id.txtLogin_usuario);
        txtSenha = findViewById(R.id.txtSenha_usuario);
        btnLogin = findViewById(R.id.btnLogin_usuario);

         preferences=getSharedPreferences("user_login", MODE_PRIVATE);
         String sharedUsuario = preferences.getString("usuario", "");
         String sharedSenha = preferences.getString("senha", "");
         txtUsuario.setText(sharedUsuario);
         txtSenha.setText(sharedSenha);



            btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((txtUsuario.getText() == null) || (txtUsuario.getText().length()<2)) {
                    Ferramentas.exibirMensagem(context, "Insira um nome de usuário!");
                }
                    else
                {
                    if (txtSenha.getText() == null)
                    {
                        Ferramentas.exibirMensagem(context, "Insira uma senha");
                    }
                    else
                    {
                        if ((txtUsuario.getText().toString().toLowerCase().equals(getString(R.string.user_login))) && (txtSenha.getText().toString().equals(getString(R.string.user_senha))))
                        {
                            preferences=getSharedPreferences("user_login", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("usuario", txtUsuario.getText().toString());
                            editor.putString("senha", txtSenha.getText().toString());
                            editor.commit();

                            Bundle bundle = new Bundle();
                            Intent intent = new Intent(context,MenuActivity.class);
                            bundle.putString("usuario_nome",txtUsuario.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                        }
                        else
                        {
                            Ferramentas.exibirMensagem(context,"Usuário ou senha inválidos!");
                        }
                    }
                }
            }
        });
    }
}
