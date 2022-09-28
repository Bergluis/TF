package com.example.almeida.indexlogistics;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import java.util.ArrayList;

import classesDominio.Endereco;

public class EnderecosCadastradosActivity extends Activity {

    RecyclerView rvElementos;
    EnderecosCadastradosAdapter enderecosCadastradosAdapter;
    InfoApp infoApp;
    ArrayList<Endereco> listaEnderecos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Teste","Vai fazer os findsss");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enderecos_cadastrados);

        Log.d("Teste","Vai fazer os find");
        rvElementos = (RecyclerView) findViewById(R.id.rvElementos);
        infoApp = (InfoApp) getApplicationContext();
        Log.d("Teste","Vai entrar na thread");
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Log.d("Teste","vai enviar 3");
                    infoApp.saida.writeObject(3);
                    Log.d("Teste","Vai receber 1");
                    int aux=(int)infoApp.entrada.readObject();
                    Log.d("Teste","Vai enviar cod");
                    infoApp.saida.writeObject(infoApp.USUARIO_NO_SISTEMA);
                    Log.d("Teste","Vai receber lista");
                    listaEnderecos = (ArrayList<Endereco>)infoApp.entrada.readObject();
                    Log.d("Teste","Vai botar na lista");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(listaEnderecos != null) {
                                enderecosCadastradosAdapter = new EnderecosCadastradosAdapter(listaEnderecos, tratacliqueitem);
                                rvElementos.setLayoutManager(new LinearLayoutManager(EnderecosCadastradosActivity.this));
                                rvElementos.setItemAnimator(new DefaultItemAnimator());
                                rvElementos.setAdapter(enderecosCadastradosAdapter);
                            }
                        }
                    });
                } catch (Exception e) {

                }
            }
        };
        thread.start();

    }

    EnderecosCadastradosAdapter.EnderecoOnClickListener tratacliqueitem =  new EnderecosCadastradosAdapter.EnderecoOnClickListener() {
        @Override
        public void onClickEndereco(View view, int position) {
            Endereco meuEndereco = listaEnderecos.get(position);
            Intent it = new Intent(EnderecosCadastradosActivity.this, EnderecoDetalhadoActivity.class);
            it.putExtra("endereco", meuEndereco);
            startActivity(it);
        }
    };

}
