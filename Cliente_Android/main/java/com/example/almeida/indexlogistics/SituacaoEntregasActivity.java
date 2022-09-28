package com.example.almeida.indexlogistics;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;

import classesDominio.Entrega;
import classesDominio.Pessoa;

public class SituacaoEntregasActivity extends Activity {

    RecyclerView rvSituacaoEntregasElementos;
    InfoApp infoApp;
    SituacaoEntregasAdapter situacaoEntregasAdapter;
    ArrayList<Pessoa> listaPessoas;
    ArrayList<Entrega> listaEntregas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situacao_entregas);

        rvSituacaoEntregasElementos = (RecyclerView) findViewById(R.id.rvSituacaoEntregasElementos);
        infoApp = (InfoApp) getApplicationContext();

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    infoApp.saida.writeObject(11);
                    int ok = (int) infoApp.entrada.readObject();
                    if(ok == 1) {
                        infoApp.saida.writeObject(infoApp.USUARIO_NO_SISTEMA);
                        listaEntregas = (ArrayList<Entrega>) infoApp.entrada.readObject();
                        infoApp.saida.writeObject(111);
                        listaPessoas = (ArrayList<Pessoa>) infoApp.entrada.readObject();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(listaEntregas.size()>0&&listaPessoas.size()>0) {
                                situacaoEntregasAdapter = new SituacaoEntregasAdapter(listaPessoas,listaEntregas, tratacliqueitem);
                                rvSituacaoEntregasElementos.setLayoutManager(new LinearLayoutManager(SituacaoEntregasActivity.this));
                                rvSituacaoEntregasElementos.setItemAnimator(new DefaultItemAnimator());
                                rvSituacaoEntregasElementos.setAdapter(situacaoEntregasAdapter);
                            }else{
                                Toast.makeText(infoApp, "Não há Entregas!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    });
                } catch (Exception e) {

                }
            }
        };
        thread.start();

    }

    SituacaoEntregasAdapter.SituacaoOnClickListener tratacliqueitem =  new SituacaoEntregasAdapter.SituacaoOnClickListener() {
        @Override
        public void onClickSituacao(View view, int position) {
            Entrega minhaEntrega = listaEntregas.get(position);
            Pessoa minhaPessoa = listaPessoas.get(position);
            int cod = minhaEntrega.getCod();
            Intent it = new Intent(SituacaoEntregasActivity.this, SituacaoEntregasDetalhado.class);
            it.putExtra("entrega", minhaEntrega);
            startActivity(it);
        }
    };

}
