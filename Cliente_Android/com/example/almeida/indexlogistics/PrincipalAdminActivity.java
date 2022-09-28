package com.example.almeida.indexlogistics;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PrincipalAdminActivity extends Activity {

    TextView tvPrincipalAdminNome;
    Button bPrincipalAdminCadastrarEmpresa;
    InfoApp infoApp;
    String nome = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_admin);
        tvPrincipalAdminNome = findViewById(R.id.tvPrincipalAdminNome);
        bPrincipalAdminCadastrarEmpresa = findViewById(R.id.bPrincipalAdminCadastrarEmpresa);
        bPrincipalAdminCadastrarEmpresa.setOnClickListener(trataCliquePrincipalAdmin);


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
                                tvPrincipalAdminNome.setText("Olá, " + nome);
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

    View.OnClickListener trataCliquePrincipalAdmin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == bPrincipalAdminCadastrarEmpresa.getId()) {
                Intent it =  new Intent(PrincipalAdminActivity.this, CadastroEmpresaActivity.class);
                startActivity(it);
            }
        }
    };

}
