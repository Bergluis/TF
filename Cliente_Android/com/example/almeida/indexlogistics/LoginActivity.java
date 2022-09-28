package com.example.almeida.indexlogistics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LoginActivity extends Activity {

    Button bLoginSubmeter;
    EditText etLoginUsuario, etLoginSenha;
    TextView tvLoginCadastrese, tvLoginEsqueciSenha, tvLoginCamposVazios;
    InfoApp infoApp;

    String user = "";
    String pass = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bLoginSubmeter = findViewById(R.id.bLoginSubmeter);
        etLoginUsuario = findViewById(R.id.etLoginUsuario);
        etLoginSenha = findViewById(R.id.etLoginSenha);
        tvLoginCadastrese = findViewById(R.id.tvLoginCadastrese);
        tvLoginEsqueciSenha = findViewById(R.id.tvLoginEsqueciSenha);
        tvLoginCamposVazios = findViewById(R.id.tvLoginCamposVazios);
        bLoginSubmeter.setOnClickListener(cliques);
        tvLoginEsqueciSenha.setOnClickListener(cliques);
        tvLoginCadastrese.setOnClickListener(cliques);

        infoApp = (InfoApp)getApplicationContext();
        //Estabelecendo conexão com o servidor
        Thread thread = new Thread() {
            @Override
            public void run() {
                try{
                    infoApp.socket = new Socket("192.168.0.106", 12345);
                    infoApp.saida = new ObjectOutputStream(infoApp.socket.getOutputStream());
                    infoApp.entrada = new ObjectInputStream(infoApp.socket.getInputStream());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(infoApp, "Conexão bem sucedida!", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (IOException ioe) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(infoApp, "Errrrooouuuuu", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        };
        thread.start();
    }

    View.OnClickListener cliques = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == bLoginSubmeter.getId()) {
                user = etLoginUsuario.getText().toString();
                pass = etLoginSenha.getText().toString();
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            infoApp.saida.writeObject(5);
                            int ok = ((int)infoApp.entrada.readObject());
                            if(ok == 1) {
                                infoApp.saida.writeObject(user);
                                int ok2 = (int)infoApp.entrada.readObject();
                                if(ok2 == 1) {
                                    infoApp.saida.writeObject(pass);
                                    int retorno = (int)infoApp.entrada.readObject();
                                    if(retorno == 1234567) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(infoApp, "Usuário ou senha são inválidos!@", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    } else {
                                        infoApp.USUARIO_NO_SISTEMA = retorno;
                                        infoApp.saida.writeObject(2);
                                        ok = (int)infoApp.entrada.readObject();
                                        if(ok == 1) {
                                            infoApp.saida.writeObject(infoApp.USUARIO_NO_SISTEMA);
                                            int nivel = (int)infoApp.entrada.readObject();
                                            if(nivel == 1) {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Intent it = new Intent(LoginActivity.this, PrincipalActivity.class);
                                                        startActivity(it);
                                                    }
                                                });
                                            } else if(nivel == 2) {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Intent it = new Intent(LoginActivity.this, PrincipalEmpresaActivity.class);
                                                        startActivity(it);
                                                    }
                                                });
                                            } else if(nivel == 3) {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Intent it = new Intent(LoginActivity.this, PrincipalAdminActivity.class);
                                                        startActivity(it);
                                                    }
                                                });
                                            }
                                        }
                                    }
                                }
                                }

                        } catch (Exception e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(infoApp, "Erro dentro da thread no LOGIN", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                };
                thread.start();

            } else if(v.getId() == tvLoginEsqueciSenha.getId()) {
                Intent it  = new Intent(LoginActivity.this, RecuperacaoSenhaActivity.class);
                startActivity(it);
            } else if(v.getId() == tvLoginCadastrese.getId()) {
                Intent it = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(it);
            }
        }
    };
}
