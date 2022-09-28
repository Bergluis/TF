package com.example.almeida.indexlogistics;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import classesDominio.Entrega;
import classesDominio.Solicitacao;

public class EmpresaEntregaDetalhado extends Activity {

    EditText etEmpresaEntregaDetalhadoPreco, etEmpresaEntregaDetalhadoPrazo;
    TextView tvEmpresaEntregaDetalhadoCodigo;
    Button bEmpresaEntregaDetalhadoProporEntrega;
    InfoApp infoApp;
    int cod_solicitacao = 0;
    Solicitacao minhaSolicitacao = null;
    String nome = "";
    String descricao = "";
    Entrega minhaEntrega;

    int prazo = 0;
    float preco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_entrega_detalhado);
        etEmpresaEntregaDetalhadoPreco = findViewById(R.id.etEmpresaEntregaDetalhadoPreco);
        etEmpresaEntregaDetalhadoPrazo = findViewById(R.id.etEmpresaEntregaDetalhadoPrazo);
        tvEmpresaEntregaDetalhadoCodigo = findViewById(R.id.tvEmpresaEntregaDetalhadoCodigo);
        bEmpresaEntregaDetalhadoProporEntrega = findViewById(R.id.bEmpresaEntregaDetalhadoProporEntrega);
        bEmpresaEntregaDetalhadoProporEntrega.setOnClickListener(euNaoAguentoMais);
        Intent it = getIntent();
        minhaSolicitacao = (Solicitacao) it.getSerializableExtra("solicitacao");
        infoApp=(InfoApp)getApplicationContext();
        tvEmpresaEntregaDetalhadoCodigo.setText(tvEmpresaEntregaDetalhadoCodigo.getText()+ " "+ minhaSolicitacao.getCod());
    }

    View.OnClickListener euNaoAguentoMais = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v.getId() == bEmpresaEntregaDetalhadoProporEntrega.getId()) {
                if (!etEmpresaEntregaDetalhadoPreco.getText().toString().equals("")) {
                    if (!etEmpresaEntregaDetalhadoPrazo.getText().toString().equals("")) {
                        prazo = Integer.parseInt(etEmpresaEntregaDetalhadoPrazo.getText().toString());
                        preco = Float.parseFloat(etEmpresaEntregaDetalhadoPreco.getText().toString());
                        cod_solicitacao = minhaSolicitacao.getCod();


                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                try {
                                    nome = minhaSolicitacao.getNome();
                                    descricao = minhaSolicitacao.getDescricao();


                                    Entrega e=new Entrega(Integer.parseInt(etEmpresaEntregaDetalhadoPrazo.getText().toString()),Float.parseFloat(etEmpresaEntregaDetalhadoPreco.getText().toString()),infoApp.USUARIO_NO_SISTEMA,1,minhaSolicitacao.getNome(),minhaSolicitacao.getDescricao(), minhaSolicitacao.getCodEndSaida(), minhaSolicitacao.getCodEndChegada(),minhaSolicitacao.getCodPessoa());

                                    e.setCodSolicitacao(cod_solicitacao);

                                    infoApp.saida.writeObject(16);
                                    int teste = (int) infoApp.entrada.readObject();

                                    if (teste == 1) {
                                        infoApp.saida.writeObject(e);
                                        int maisUmTeste = (int) infoApp.entrada.readObject();
                                        if (maisUmTeste == 1) {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(infoApp, "Proposta cadastrada!", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                }
                                            });

                                        } else {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(infoApp, "Falha ao cadastrar!", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                        }
                                    }
                                } catch (Exception e) {

                                }
                            }
                        };
                        thread.start();


                    }
                }
            }
        }
    };

}
