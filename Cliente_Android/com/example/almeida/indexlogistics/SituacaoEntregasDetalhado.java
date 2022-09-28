package com.example.almeida.indexlogistics;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import classesDominio.Entrega;

public class SituacaoEntregasDetalhado extends Activity {

    Spinner spSituacao;
    Button bSituacaoSalvar;
    String [] spinha = new String[2];
    InfoApp infoApp;
    int cod = 0;
    int situation = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situacao_entregas_detalhado);
        spSituacao = findViewById(R.id.spSituacao);
        bSituacaoSalvar = findViewById(R.id.bSituacaoSalvar);
        bSituacaoSalvar.setOnClickListener(IWONTGIVEUP);

        infoApp = (InfoApp) getApplicationContext();

        spinha [0] = "Saiu para entrega";
        spinha [1] = "Entregue";
        spSituacao.setAdapter(new ArrayAdapter<String>(SituacaoEntregasDetalhado.this ,android.R.layout.simple_spinner_dropdown_item, spinha));

        Intent it = getIntent();
        Entrega minhaEntrega = (Entrega) it.getSerializableExtra("entrega");
        cod = minhaEntrega.getCod();

    }

    View.OnClickListener IWONTGIVEUP = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            situation = spSituacao.getSelectedItemPosition();
            Thread thread = new Thread() {
                @Override
                public void run () {
                    try {
                        infoApp.saida.writeObject(26);
                        int y = (int)infoApp.entrada.readObject();
                        if(y == 1) {
                            infoApp.saida.writeObject(cod);
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(infoApp, "Falha na conexão com o servidor!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        if(situation == 0) {
                            infoApp.saida.writeObject(3);
                            int ok = (int) infoApp.entrada.readObject();
                        } else if(situation == 0) {
                            infoApp.saida.writeObject(4);
                        }
                    } catch (Exception e) {

                    }
                }
            };
            thread.start();
        }
    };

}
