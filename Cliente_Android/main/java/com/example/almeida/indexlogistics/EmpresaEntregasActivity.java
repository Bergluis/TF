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

import classesDominio.Solicitacao;

public class EmpresaEntregasActivity extends Activity {

    RecyclerView rvEmpresaEntregasElementos;
    EmpresaEntregasAdapter empresaEntregasAdapter;
    InfoApp infoApp;
    ArrayList<Solicitacao> listaSolicitacoes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_entregas);

        rvEmpresaEntregasElementos = (RecyclerView) findViewById(R.id.rvEmpresaEntregasElementos);
        infoApp = (InfoApp) getApplicationContext();

        Thread thread = new Thread() {
            @Override
            public  void run () {
                try {
                    infoApp.saida.writeObject(8);
                    int ok = (int) infoApp.entrada.readObject();
                    if(ok == 1) {
                        infoApp.saida.writeObject(infoApp.USUARIO_NO_SISTEMA);
                        listaSolicitacoes = (ArrayList<Solicitacao>)infoApp.entrada.readObject();

                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(listaSolicitacoes != null) {
                                empresaEntregasAdapter = new EmpresaEntregasAdapter(listaSolicitacoes, tratacliqueitem);
                                rvEmpresaEntregasElementos.setLayoutManager(new LinearLayoutManager(EmpresaEntregasActivity.this));
                                rvEmpresaEntregasElementos.setItemAnimator(new DefaultItemAnimator());
                                rvEmpresaEntregasElementos.setAdapter(empresaEntregasAdapter);
                            }else{
                                Toast.makeText(infoApp, "Não há entregas!", Toast.LENGTH_SHORT).show();
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

    EmpresaEntregasAdapter.EmpresaEntregasOnClickListener tratacliqueitem =  new EmpresaEntregasAdapter.EmpresaEntregasOnClickListener() {
        @Override
        public void onClickEmpresaEntregas(View view, int position) {
            Solicitacao minhaSolicitacao = listaSolicitacoes.get(position);
            Intent it = new Intent(EmpresaEntregasActivity.this, EmpresaEntregaDetalhado.class);
            it.putExtra("solicitacao", minhaSolicitacao);
            startActivity(it);
        }
    };

}
