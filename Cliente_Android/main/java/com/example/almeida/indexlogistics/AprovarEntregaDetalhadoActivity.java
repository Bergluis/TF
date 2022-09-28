package com.example.almeida.indexlogistics;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import classesDominio.Entrega;


public class AprovarEntregaDetalhadoActivity extends Activity {

    TextView tvAprovarEntregaDetalhadoCodigoObjeto, tvAprovarEntregaDetalhadoCodigoEntrega, tvAprovarEntregaDetalhadoNomeObjeto;
    TextView tvAprovarEntregaDetalhadoNomeEmpresa, tvAprovarEntregaDetalhadoValorFrete, tvAprovarEntregaDetalhadoPrazoEntrega;
    Button bAprovarEntregaDetalhadoSelecionar;

    int codigoSolicitacao = 0, codigoObjeto = 0;
    InfoApp infoApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprovar_entrega_detalhado);
        tvAprovarEntregaDetalhadoCodigoObjeto = findViewById(R.id.tvAprovarEntregaDetalhadoCodigoObjeto);
        tvAprovarEntregaDetalhadoCodigoEntrega = findViewById(R.id.tvAprovarEntregaDetalhadoCodigoEntrega);
        tvAprovarEntregaDetalhadoNomeObjeto = findViewById(R.id.tvAprovarEntregaDetalhadoNomeObjeto);
        tvAprovarEntregaDetalhadoNomeEmpresa = findViewById(R.id.tvAprovarEntregaDetalhadoNomeEmpresa);
        tvAprovarEntregaDetalhadoValorFrete = findViewById(R.id.tvAprovarEntregaDetalhadoValorFrete);
        tvAprovarEntregaDetalhadoPrazoEntrega = findViewById(R.id.tvAprovarEntregaDetalhadoPrazoEntrega);
        bAprovarEntregaDetalhadoSelecionar = findViewById(R.id.bAprovarEntregaDetalhadoSelecionar);
        bAprovarEntregaDetalhadoSelecionar.setOnClickListener(aprovardetalhadoclique);

        infoApp = (InfoApp) getApplicationContext();

        Intent it = getIntent();
        Entrega entrega = (Entrega) it.getSerializableExtra("entrega");
        tvAprovarEntregaDetalhadoCodigoEntrega.setText(""+entrega.getCodSolicitacao());
        tvAprovarEntregaDetalhadoCodigoObjeto.setText(""+entrega.getCod());
        tvAprovarEntregaDetalhadoNomeEmpresa.setText(entrega.getNome_empresa());
        tvAprovarEntregaDetalhadoNomeObjeto.setText(entrega.getNome());
        tvAprovarEntregaDetalhadoPrazoEntrega.setText(""+entrega.getPrazo());
        tvAprovarEntregaDetalhadoValorFrete.setText(""+entrega.getValor());
        codigoObjeto = entrega.getCod();
        codigoSolicitacao = entrega.getCodSolicitacao();
    }

    View.OnClickListener aprovardetalhadoclique = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == bAprovarEntregaDetalhadoSelecionar.getId()) {
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            infoApp.saida.writeObject(25); //aprovando a entrega
                            int y = (int) infoApp.entrada.readObject();
                            if(y == 1) {
                                infoApp.saida.writeObject(codigoObjeto+"");
                                int ok = (int) infoApp.entrada.readObject();
                                if(ok == 1) {
                                    infoApp.saida.writeObject(codigoSolicitacao+"");
                                    int retorno = (int) infoApp.entrada.readObject();
                                    if(retorno == 1) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(infoApp, "Pronto!", Toast.LENGTH_SHORT).show();
                                                finish();
                                            }
                                        });

                                    } else {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(infoApp, "Ocorreu algum problema!", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                    }
                                }
                            } else {
                                Toast.makeText(infoApp, "Servidor indisponível!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {

                        }
                    }
                };
                thread.start();
            }
        }
    };
}
