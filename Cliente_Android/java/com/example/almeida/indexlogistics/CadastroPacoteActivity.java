package com.example.almeida.indexlogistics;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.ArrayList;

import classesDominio.Endereco;
import classesDominio.Solicitacao;

public class CadastroPacoteActivity extends Activity {

    EditText etCadastroPacoteNomeProduto, etCadastroPacoteDescricao;
    Spinner spCadastroPacoteEnderecoBusca, spCadastroPacoteEnderecoDestino;
    Button bCadastroPacoteSubmeter;
    ArrayList<Endereco> listaEnderecos;
    String[] enderecos;
    Solicitacao minhaSolicitacao;

    InfoApp infoApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pacote);
        etCadastroPacoteNomeProduto = findViewById(R.id.etCadastroPacoteNomeProduto);
        etCadastroPacoteDescricao = findViewById(R.id.etCadastroPacoteDescricao);
        spCadastroPacoteEnderecoBusca = findViewById(R.id.spCadastroPacoteEnderecoBusca);
        spCadastroPacoteEnderecoDestino = findViewById(R.id.spCadastroPacoteEnderecoDestino);
        bCadastroPacoteSubmeter = findViewById(R.id.bCadastroPacoteSubmeter);
        bCadastroPacoteSubmeter.setOnClickListener(cadastropacotebotoes);

        infoApp = (InfoApp) getApplicationContext();


        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    infoApp.saida.writeObject(3);
                    int ok = (int) infoApp.entrada.readObject();
                    if (ok == 1) {
                        infoApp.saida.writeObject(infoApp.USUARIO_NO_SISTEMA);
                        listaEnderecos = (ArrayList<Endereco>) infoApp.entrada.readObject();

                        enderecos = new String[listaEnderecos.size() + 1];
                        enderecos[0] = "Selecione";

                        for (int x = 1; x <= listaEnderecos.size(); x++) {
                            Endereco meuEndereco = listaEnderecos.get(x - 1);
                            enderecos[x] = meuEndereco.getCod() + "; Rua: " + meuEndereco.getRua() + ", Número: " + meuEndereco.getNumero();
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                spCadastroPacoteEnderecoDestino.setAdapter(new ArrayAdapter<String>(CadastroPacoteActivity.this, android.R.layout.simple_spinner_dropdown_item, enderecos));
                                spCadastroPacoteEnderecoBusca.setAdapter(new ArrayAdapter<String>(CadastroPacoteActivity.this, android.R.layout.simple_spinner_dropdown_item, enderecos));
                            }
                        });
                    }


                } catch (Exception e) {

                }
            }
        };
        thread.start();

    }

    View.OnClickListener cadastropacotebotoes = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == bCadastroPacoteSubmeter.getId()) {
                if (!etCadastroPacoteNomeProduto.getText().toString().equals("")) {
                    if (!etCadastroPacoteDescricao.getText().toString().equals("")) {
                        int indiceBusca = spCadastroPacoteEnderecoBusca.getSelectedItemPosition();
                        if (indiceBusca != 0) {
                            int indiceDestino = spCadastroPacoteEnderecoDestino.getSelectedItemPosition();
                            if (indiceDestino != 0) {
                                if (indiceBusca != indiceDestino) {


                                    String nomeProduto = etCadastroPacoteNomeProduto.getText().toString();
                                    String descricaoProduto = etCadastroPacoteDescricao.getText().toString();/*
                                    String enderecoBusca = (String) spCadastroPacoteEnderecoBusca.getSelectedItem();
                                    String enderecoDestino = (String) spCadastroPacoteEnderecoDestino.getSelectedItem();*/
                                    int usu = infoApp.USUARIO_NO_SISTEMA;/*
                                    String[] parts = enderecoDestino.split(";");
                                    String[] parts2 = enderecoBusca.split(";");*/
                                    int busca = listaEnderecos.get(spCadastroPacoteEnderecoBusca.getSelectedItemPosition() - 1).getCod();
                                    int destino = listaEnderecos.get(spCadastroPacoteEnderecoDestino.getSelectedItemPosition() - 1).getCod();

                                     minhaSolicitacao = new Solicitacao(nomeProduto, descricaoProduto, busca, destino, usu);
                                    Thread thread = new Thread() {
                                        @Override
                                        public void run() {
                                            try

                                            {
                                                infoApp.saida.writeObject(15);
                                                int teste = (int) infoApp.entrada.readObject();
                                                if (teste == 1) {
                                                    infoApp.saida.writeObject(minhaSolicitacao);
                                                    int maisUmTeste = (int) infoApp.entrada.readObject();
                                                    if (maisUmTeste == 1) {
                                                        runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                Toast.makeText(infoApp, "Solicitação cadastrada!", Toast.LENGTH_SHORT).show();
                                                                finish();
                                                            }
                                                        });

                                                    } else {
                                                        runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                Toast.makeText(infoApp, "Erro na solicitação!", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });

                                                    }
                                                }
                                            } catch (Exception e){

                                            }
                                        }
                                    };
                                    thread.start();
                                } else {
                                    Toast.makeText(infoApp, "Selecione endereços diferentes!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(infoApp, "Selecione um endereço de destino!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(infoApp, "Selecione um endereço de busca!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(infoApp, "Preencha a descrição, quanto mais detalhes, melhor!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(infoApp, "Dê um nome bem bonitinho ao seu produto!", Toast.LENGTH_LONG).show();
                }
            } else {
                finish();
            }
        }
    };
}
