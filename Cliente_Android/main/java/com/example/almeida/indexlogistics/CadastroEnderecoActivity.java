package com.example.almeida.indexlogistics;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.ArrayList;

import classesDominio.Cidade;
import classesDominio.Endereco;

public class CadastroEnderecoActivity extends Activity {

    EditText etCadastroEnderecoRua, etCadastroEnderecoNumero, etCadastroEnderecoComplemento, etCadastroEnderecoBairro;
    Button bCadastroEnderecoSubmeter, bCadastroEnderecoVer;
    Spinner spCadastroEnderecoCidadeEstado;

    ArrayList<Cidade> listaCidades;
    String[] listaCidadesString;

    InfoApp infoApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_endereco);
        spCadastroEnderecoCidadeEstado = findViewById(R.id.spCadastroEnderecoCidadeEstado);
        etCadastroEnderecoRua = findViewById(R.id.etCadastroEnderecoRua);
        etCadastroEnderecoNumero = findViewById(R.id.etCadastroEnderecoNumero);
        etCadastroEnderecoComplemento = findViewById(R.id.etCadastroEnderecoComplemento);
        etCadastroEnderecoBairro = findViewById(R.id.etCadastroEnderecoBairro);
        bCadastroEnderecoSubmeter = findViewById(R.id.bCadastroEnderecoSubmeter);
        bCadastroEnderecoVer = findViewById(R.id.bCadastroEnderecoVer);
        bCadastroEnderecoSubmeter.setOnClickListener(cadastroEnderecoBotoes);
        bCadastroEnderecoVer.setOnClickListener(cadastroEnderecoBotoes);

        infoApp = (InfoApp)getApplicationContext();

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    infoApp.saida.writeObject(4);
                    listaCidades = (ArrayList<Cidade>)infoApp.entrada.readObject();
                    listaCidadesString = new String[listaCidades.size()];
                    for(int x = 0; x < listaCidadesString.length;x++) {
                        listaCidadesString[x] = listaCidades.get(x).getNome() +"/"+ listaCidades.get(x).getUf();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            spCadastroEnderecoCidadeEstado.setAdapter(new ArrayAdapter<String>(CadastroEnderecoActivity.this ,android.R.layout.simple_spinner_dropdown_item, listaCidadesString));
                        }
                    });

                } catch (Exception e) {

                }
            }
        };
        thread.start();
    }

    View.OnClickListener cadastroEnderecoBotoes = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == bCadastroEnderecoSubmeter.getId()) {
                if(!etCadastroEnderecoRua.getText().toString().equals("")) {
                    if(!etCadastroEnderecoNumero.getText().toString().equals("")) {
                        if(!etCadastroEnderecoBairro.getText().toString().equals("")) {
                            String rua = etCadastroEnderecoRua.getText().toString();
                            int numero = Integer.parseInt(etCadastroEnderecoNumero.getText().toString());
                            String bairro = etCadastroEnderecoBairro.getText().toString();
                            String complemento = etCadastroEnderecoComplemento.getText().toString();
                            int cidadeEstado = spCadastroEnderecoCidadeEstado.getSelectedItemPosition();
                            int usu = infoApp.USUARIO_NO_SISTEMA;
                            final Endereco meuEndereco = new Endereco(rua, numero, cidadeEstado,usu);
                            meuEndereco.setComplemento(complemento);
                            Thread thread = new Thread() {
                                @Override
                                public void run() {
                                    try{
                                        infoApp.saida.writeObject(14);
                                        int teste = (int)infoApp.entrada.readObject();
                                        if(teste == 1) {
                                            infoApp.saida.writeObject(meuEndereco);
                                            int maisUmTeste = (int)infoApp.entrada.readObject();
                                            if(maisUmTeste == 1) {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Toast.makeText(infoApp, "Endereço cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
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
                        } else {
                            Toast.makeText(infoApp, "Informe um bairro!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(infoApp, "Informe um número!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(infoApp, "Informe uma rua!", Toast.LENGTH_SHORT).show();
                }
            } else if(v.getId() == bCadastroEnderecoVer.getId()) {
                Intent it  = new Intent(CadastroEnderecoActivity.this, EnderecosCadastradosActivity.class);
                startActivity(it);
            }
        }
    };
}
