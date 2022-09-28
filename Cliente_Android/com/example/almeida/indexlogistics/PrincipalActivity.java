package com.example.almeida.indexlogistics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PrincipalActivity extends Activity {

    TextView tvPrincipalNome;
    Button bPrincipalEditar, bPrincipalSalvos, bPrincipalAprovar, bPrincipalResumo, bPrincipalCadastrarPacote;
    InfoApp infoApp;
    String nome = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        tvPrincipalNome = findViewById(R.id.tvPrincipalNome);
        bPrincipalEditar = findViewById(R.id.bPrincipalEditar);
        bPrincipalSalvos = findViewById(R.id.bPrincipalSalvos);
        bPrincipalAprovar = findViewById(R.id.bPrincipalAprovar);
        bPrincipalResumo = findViewById(R.id.bPrincipalResumo);
        bPrincipalCadastrarPacote = findViewById(R.id.bPrincipalCadastrarPacote);
        bPrincipalResumo.setOnClickListener(principalBotoes);
        bPrincipalAprovar.setOnClickListener(principalBotoes);
        bPrincipalSalvos.setOnClickListener(principalBotoes);
        bPrincipalEditar.setOnClickListener(principalBotoes);
        bPrincipalCadastrarPacote.setOnClickListener(principalBotoes);

        infoApp = (InfoApp)getApplicationContext();

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    infoApp.saida.writeObject(1);
                    int ok = (int)infoApp.entrada.readObject();
                    if(ok == 1) {
                        infoApp.saida.writeObject(infoApp.USUARIO_NO_SISTEMA);
                        nome = (String)infoApp.entrada.readObject();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvPrincipalNome.setText("Olá, " + nome);
                            }
                        });

                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(infoApp, "Servidor não está respondendo, tente novamente mais tarde!", Toast.LENGTH_LONG).show();

                            }
                        });
                    }
                } catch (Exception e) {

                }
            }
        };
        thread.start();

    }

    View.OnClickListener principalBotoes = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == bPrincipalEditar.getId()) {
                Intent it = new Intent(PrincipalActivity.this, EditarActivity.class);
                startActivity(it);
            } else if (v.getId() == bPrincipalSalvos.getId()) {
                Intent it = new Intent(PrincipalActivity.this, CadastroEnderecoActivity.class);
                startActivity(it);
            } else if (v.getId() == bPrincipalAprovar.getId()) {
                Intent it = new Intent(PrincipalActivity.this, AprovarEntregaActivity.class);
                startActivity(it);
            } else if (v.getId() == bPrincipalResumo.getId()) {
                Intent it = new Intent(PrincipalActivity.this, ResumoActivity.class);
                startActivity(it);
            } else if (v.getId() == bPrincipalCadastrarPacote.getId()) {
                Intent it = new Intent(PrincipalActivity.this, CadastroPacoteActivity.class);
                startActivity(it);
            }
        }
    };
}
