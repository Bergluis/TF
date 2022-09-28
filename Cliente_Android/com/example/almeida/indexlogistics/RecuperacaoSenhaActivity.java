package com.example.almeida.indexlogistics;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class RecuperacaoSenhaActivity extends Activity {

    EditText etRecuperacaoDocumento;
    Button bRecuperacaoEnviar;
    InfoApp infoApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperacao_senha);
        etRecuperacaoDocumento = findViewById(R.id.etRecuperacaoDocumento);
        bRecuperacaoEnviar = findViewById(R.id.bRecuperacaoEnviar);
        bRecuperacaoEnviar.setOnClickListener(clique);
        infoApp = (InfoApp) getApplicationContext();
    }

    View.OnClickListener clique = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == bRecuperacaoEnviar.getId()) {
                if (!etRecuperacaoDocumento.getText().toString().equals("")) {
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            try {
                                long doc = Long.parseLong(etRecuperacaoDocumento.getText().toString());
                                infoApp.saida.writeObject(12345); //consulta email com base no documento
                                int ok = (int) infoApp.entrada.readObject();
                                if (ok == 1) {
                                    infoApp.saida.writeObject(doc); //envia o documento
                                    int teste = (int) infoApp.entrada.readObject();
                                    if (teste == 0) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(infoApp, "Documento inexistente ou inválido!", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    } else {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(infoApp, "Enviou email!", Toast.LENGTH_SHORT).show();
                                                finish();
                                            }
                                        });
                                        infoApp.saida.writeObject(123456); //envia o email se "teste" não voltar vazio
                                        int ok2 = (int) infoApp.entrada.readObject();
                                        if (ok == 2) {
                                            infoApp.saida.writeObject(teste); //envia as informações necessárias para mandar o email
                                            int testando = (int) infoApp.entrada.readObject(); //recebe o resultado da operação
                                            if (testando != 1) {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Toast.makeText(infoApp, "Erro ao enviar o e-mail!", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            } else {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Toast.makeText(infoApp, "Enviou email!", Toast.LENGTH_SHORT).show();
                                                        finish();
                                                    }
                                                });
                                            }
                                        }

                                    }
                                }

                            } catch (IOException ioe) {

                            } catch (ClassNotFoundException cnfe) {

                            }
                        }
                    };
                    thread.start();
                }
            }
        }
    };
}
