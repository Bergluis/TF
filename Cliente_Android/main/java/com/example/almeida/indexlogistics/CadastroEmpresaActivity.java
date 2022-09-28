package com.example.almeida.indexlogistics;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import classesDominio.Pessoa;

public class CadastroEmpresaActivity extends Activity {

    EditText etCadastroEmpresaNome, etCadastroEmpresaCNPJ, etCadastroEmpresaEmail, etCadastroEmpresaLogin, etCadastroEmpresaSenha, etCadastroEmpresaConfirmarSenha;
    Button bCadastroEmpresaSubmeter;
    InfoApp infoApp;
    Pessoa p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_empresa);
        etCadastroEmpresaNome = findViewById(R.id.etCadastroEmpresaNome);
        etCadastroEmpresaCNPJ = findViewById(R.id.etCadastroEmpresaCNPJ);
        etCadastroEmpresaEmail = findViewById(R.id.etCadastroEmpresaEmail);
        etCadastroEmpresaLogin = findViewById(R.id.etCadastroEmpresaLogin);
        etCadastroEmpresaSenha = findViewById(R.id.etCadastroEmpresaSenha);
        etCadastroEmpresaConfirmarSenha = findViewById(R.id.etCadastroEmpresaConfirmaSenha);
        bCadastroEmpresaSubmeter = findViewById(R.id.bCadastroEmpresaSubmeter);
        bCadastroEmpresaSubmeter.setOnClickListener(trataCliqueCadastroEmpresa);
        infoApp=(InfoApp)getApplicationContext();
    }

    View.OnClickListener trataCliqueCadastroEmpresa = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v.getId() == bCadastroEmpresaSubmeter.getId()) {
                if (!etCadastroEmpresaNome.getText().toString().equals("")) {
                    if (!etCadastroEmpresaCNPJ.getText().toString().equals("")) {
                        if (!etCadastroEmpresaEmail.getText().toString().equals("")) {
                            if (!etCadastroEmpresaLogin.getText().toString().equals("")) {
                                if (!etCadastroEmpresaSenha.getText().toString().equals("")) {
                                    if (!etCadastroEmpresaConfirmarSenha.getText().toString().equals("")) {
                                        Log.d("Teste", "entrou ifs ");
                                        String senha = etCadastroEmpresaSenha.getText().toString();
                                        String confirmacao = etCadastroEmpresaConfirmarSenha.getText().toString();
                                        if (senha.equals(confirmacao)) {
                                            String nome = etCadastroEmpresaNome.getText().toString();
                                            long doc = Long.parseLong(etCadastroEmpresaCNPJ.getText().toString());
                                            String email = etCadastroEmpresaEmail.getText().toString();
                                            String login = etCadastroEmpresaLogin.getText().toString();
                                            Log.d("Teste", "Pegou dados");
                                            p = new Pessoa(2, nome, email, doc, login, senha);
                                            Log.d("Teste", "Vai entrar na thread");
                                            Thread thread = new Thread() {
                                                @Override
                                                public void run() {
                                                    try {
                                                        Log.d("Teste", "Vai entrou na thread");
                                                        infoApp.saida.writeObject(12);
                                                        int ok = (int) infoApp.entrada.readObject();
                                                        Log.d("Teste", "Recebeu "+ok);
                                                        if (ok == 1) {
                                                            infoApp.saida.writeObject(p);
                                                            Log.d("Teste", "Mandou p");
                                                            int retorno = (int) infoApp.entrada.readObject();
                                                            Log.d("Teste", "Recebeu "+p);
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
                                        } else {
                                            Toast.makeText(infoApp, "Senhas não conferem!", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(infoApp, "Confirme a senha!", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(infoApp, "Informe uma senha!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(infoApp, "Crie um login!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(infoApp, "Informe um email válido!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(infoApp, "Informe o CNPJ da empresa!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(infoApp, "Digite o nome da empresa!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

}
