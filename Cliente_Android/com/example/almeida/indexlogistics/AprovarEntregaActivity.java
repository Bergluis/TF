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

public class AprovarEntregaActivity extends Activity {

    RecyclerView rvAprovarElementos;
    AprovarEntregaAdapter aprovarEntregaAdapter;
    InfoApp infoApp;
    ArrayList<Entrega> listaEntregas;
    ArrayList<Pessoa> listaPessoas;
    String nomeEmpresa = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprovar_entrega);

        rvAprovarElementos = (RecyclerView) findViewById(R.id.rvAprovarElementos);
        infoApp = (InfoApp)getApplicationContext();

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    infoApp.saida.writeObject(10);
                    int ok = (int)infoApp.entrada.readObject();
                    if(ok == 1) {
                        infoApp.saida.writeObject(infoApp.USUARIO_NO_SISTEMA);
                        listaEntregas = (ArrayList<Entrega>)infoApp.entrada.readObject();
                        infoApp.saida.writeObject(112);
                        listaPessoas = (ArrayList<Pessoa>)infoApp.entrada.readObject();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(listaEntregas.size()>0&&listaPessoas.size()>0) {
                                aprovarEntregaAdapter = new AprovarEntregaAdapter(listaEntregas, tratacliqueitem);
                                rvAprovarElementos.setLayoutManager(new LinearLayoutManager(AprovarEntregaActivity.this));
                                rvAprovarElementos.setItemAnimator(new DefaultItemAnimator());
                                rvAprovarElementos.setAdapter(aprovarEntregaAdapter);
                            }else{
                                Toast.makeText(infoApp, "Não há propostas para suas possíveis entregas!", Toast.LENGTH_SHORT).show();
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
    AprovarEntregaAdapter.AprovarOnClickListener tratacliqueitem =  new AprovarEntregaAdapter.AprovarOnClickListener() {
        @Override
        public void onClickAprovar(View view, int position) {
            Entrega minhaEntrega = listaEntregas.get(position);
            Intent it = new Intent(AprovarEntregaActivity.this, AprovarEntregaDetalhadoActivity.class);
            it.putExtra("entrega", minhaEntrega);
            startActivity(it);
        }
    };
}
