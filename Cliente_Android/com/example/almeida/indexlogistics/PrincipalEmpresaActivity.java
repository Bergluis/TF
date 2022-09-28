package com.example.almeida.indexlogistics;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PrincipalEmpresaActivity extends Activity {

    TextView tvPrincipalEmpresaNome;
    Button bPrincipalEmpresaEntregas, bPrincipalEmpresaAlterarSituacao;
    InfoApp infoApp;
    String nome = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_empresa);
        tvPrincipalEmpresaNome = findViewById(R.id.tvPrincipalEmpresaNome);
        bPrincipalEmpresaEntregas = findViewById(R.id.bPrincipalEmpresaEntregas);
        bPrincipalEmpresaAlterarSituacao = findViewById(R.id.bPrincipalEmpresaAlterarSituacao);
        bPrincipalEmpresaAlterarSituacao.setOnClickListener(trataCliquePrincipalEmpresa);
        bPrincipalEmpresaEntregas.setOnClickListener(trataCliquePrincipalEmpresa);

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
                                tvPrincipalEmpresaNome.setText("Olá, " + nome);
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

    View.OnClickListener trataCliquePrincipalEmpresa = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == bPrincipalEmpresaEntregas.getId()) {
                Intent it = new Intent(PrincipalEmpresaActivity.this, EmpresaEntregasActivity.class);
                startActivity(it);
            } else if(v.getId() == bPrincipalEmpresaAlterarSituacao.getId()) {
                Intent it = new Intent(PrincipalEmpresaActivity.this, SituacaoEntregasActivity.class);
                startActivity(it);
            }
        }
    };

}
