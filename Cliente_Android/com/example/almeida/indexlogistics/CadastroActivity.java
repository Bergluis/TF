package com.example.almeida.indexlogistics;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import classesDominio.Pessoa;


public class CadastroActivity extends Activity {

    EditText etCadastroNome, etCadastroEmail, etCadastroCPF, etCadastroLogin, etCadastroSenha, etCadastroConfirmeSenha;
    Button bCadastroSubmeter;
    InfoApp infoApp;
    Pessoa p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        etCadastroNome = findViewById(R.id.etCadastroNome);
        etCadastroEmail = findViewById(R.id.etCadastroEmail);
        etCadastroCPF = findViewById(R.id.etCadastroCPF);
        etCadastroLogin = findViewById(R.id.etCadastroLogin);
        etCadastroSenha = findViewById(R.id.etCadastroSenha);
        etCadastroConfirmeSenha = findViewById(R.id.etCadastroConfirmeSenha);
        bCadastroSubmeter = findViewById(R.id.bCadastroSubmeter);
        bCadastroSubmeter.setOnClickListener(cadastroBotoes);

        infoApp = (InfoApp) getApplicationContext();
    }

    View.OnClickListener cadastroBotoes = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!etCadastroNome.getText().toString().equals("")) {
                if (!etCadastroEmail.getText().toString().equals("")) {
                    if (!etCadastroCPF.getText().toString().equals("")) {
                        if (!etCadastroLogin.getText().toString().equals("")) {
                            if (!etCadastroSenha.getText().toString().equals("")) {
                                if (etCadastroSenha.getText().toString().equals(etCadastroConfirmeSenha.getText().toString())) {

                                    String senhaNoCampo = etCadastroSenha.getText().toString();
                                    String nome = etCadastroNome.getText().toString();
                                    String email = etCadastroEmail.getText().toString();
                                    long documento = Long.parseLong(etCadastroCPF.getText().toString());
                                    String login = etCadastroLogin.getText().toString();
                                    String senha = senhaNoCampo;
                                    p = new Pessoa(1, nome, email, documento, login, senha);
                                    Thread thread = new Thread() {
                                        @Override
                                        public void run() {
                                            try {
                                                infoApp.saida.writeObject(12);
                                                int ok = (int) infoApp.entrada.readObject();
                                                if (ok == 1) {
                                                    infoApp.saida.writeObject(p);
                                                    int retorno = (int) infoApp.entrada.readObject();
                                                    if (retorno != 0) {
                                                        runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                Toast.makeText(infoApp, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                                                                finish();
                                                            }
                                                        });
                                                    } else {
                                                        runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                Toast.makeText(infoApp, "Cadastro deu erro!", Toast.LENGTH_SHORT).show();
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
                }
            }
        }
    };
}