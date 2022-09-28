package com.example.almeida.indexlogistics;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import classesDominio.Pessoa;


public class EditarActivity extends Activity {

    Button bEditarSubmeter;

    EditText etEditarNome, etEditarCPF, etEditarLogin, etEditarEmail, etEditarSenha, etEditarConfirmarSenha;

    InfoApp infoApp;

    String senha = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        bEditarSubmeter = findViewById(R.id.bEditarSubmeter);
        bEditarSubmeter.setOnClickListener(editarbotoes);
        etEditarNome = findViewById(R.id.etEditarNome);
        etEditarCPF = findViewById(R.id.etEditarCPF);
        etEditarLogin = findViewById(R.id.etEditarLogin);
        etEditarEmail = findViewById(R.id.etEditarEmail);
        etEditarSenha = findViewById(R.id.etEditarSenha);
        etEditarConfirmarSenha = findViewById(R.id.etEditarConfirmarSenha);


        infoApp = (InfoApp)getApplicationContext();

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    infoApp.saida.writeObject(113);
                    int ok = (int)infoApp.entrada.readObject();
                    if(ok == 1) {
                        infoApp.saida.writeObject(infoApp.USUARIO_NO_SISTEMA);
                        Pessoa minhaPessoa = (Pessoa)infoApp.entrada.readObject();
                        etEditarNome.setText(minhaPessoa.getNome());
                        etEditarCPF.setText(minhaPessoa.getDoc()+"");
                        etEditarLogin.setText(minhaPessoa.getLogin());
                        etEditarEmail.setText(minhaPessoa.getEmail());
                    }
                } catch (Exception e) {

                }
            }
        };
        thread.start();
    }

    View.OnClickListener editarbotoes = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == bEditarSubmeter.getId()) {
                senha = String.valueOf(etEditarSenha.getText());
                String confirma = String.valueOf(etEditarConfirmarSenha.getText());
                if(!senha.equals("")) {
                    if(!confirma.equals("")) {
                        if(senha.equals(confirma)) {
                            Thread thread = new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        infoApp.saida.writeObject(27);
                                        int ok = (int)infoApp.entrada.readObject();
                                        if(ok == 1) {
                                            infoApp.saida.writeObject(infoApp.USUARIO_NO_SISTEMA);
                                            int ok2 = (int)infoApp.entrada.readObject();
                                            if(ok == 1) {
                                                infoApp.saida.writeObject(senha);
                                                int retorno = (int)infoApp.entrada.readObject();
                                                if(retorno != 0) {
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Toast.makeText(infoApp, "Alteração realizada com sucesso!", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                } else {
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Toast.makeText(infoApp, "Falha na alteração!", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }
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
                    Toast.makeText(infoApp, "Digite a senha!", Toast.LENGTH_SHORT).show();
                }
            } else {

            }
        }
    };
}
