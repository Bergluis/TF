package com.example.almeida.indexlogistics;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;

import classesDominio.Entrega;
import classesDominio.Pessoa;

public class ResumoActivity extends Activity {

    InfoApp infoApp;
    RecyclerView rvResumoElementos;
    ResumoAdapter resumoAdapter;
    ArrayList<Entrega> listaEntregasResumo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);
        rvResumoElementos=findViewById(R.id.rvResumoElementos);
        infoApp = (InfoApp) getApplicationContext();

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    infoApp.saida.writeObject(9);
                    int ok = (int) infoApp.entrada.readObject();
                    if(ok == 1) {
                        infoApp.saida.writeObject(infoApp.USUARIO_NO_SISTEMA);
                        listaEntregasResumo = (ArrayList<Entrega>)infoApp.entrada.readObject();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(listaEntregasResumo!=null) {

                                resumoAdapter = new ResumoAdapter(listaEntregasResumo, tratacliqueitemResumo);
                                rvResumoElementos.setLayoutManager(new LinearLayoutManager(ResumoActivity.this));
                                rvResumoElementos.setItemAnimator(new DefaultItemAnimator());
                                rvResumoElementos.setAdapter(resumoAdapter);
                            }else{
                                Toast.makeText(infoApp, "Não há nenhuma entrega feita!", Toast.LENGTH_SHORT).show();
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

    ResumoAdapter.ResumoOnClickListener tratacliqueitemResumo =  new ResumoAdapter.ResumoOnClickListener() {
        @Override
        public void onClickResumo(View view, int position) {

        }
    };

}
