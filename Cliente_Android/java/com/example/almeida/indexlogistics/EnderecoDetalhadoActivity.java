package com.example.almeida.indexlogistics;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import java.util.ArrayList;

import classesDominio.Endereco;

public class EnderecoDetalhadoActivity extends Activity {

    TextView tvAquiVaiARua, tvAquiVaONumero, tvAquiVaiACidade, tvAquiVaiOComplemento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endereco_detalhado);
        tvAquiVaiARua = findViewById(R.id.tvAquiVaiARua);
        tvAquiVaONumero = findViewById(R.id.tvAquiVaiONumero);
        tvAquiVaiACidade = findViewById(R.id.tvAquiVaiACidade);
        tvAquiVaiOComplemento = findViewById(R.id.tvAquiVaiOComplemento);

        Intent it = getIntent();
        if(it != null) {
            Endereco endereco = (Endereco) it.getSerializableExtra("endereco");
            tvAquiVaiARua.setText(endereco.getRua());
            tvAquiVaONumero.setText(endereco.getNumero());
            tvAquiVaiACidade.setText(endereco.getCod_cidade());
            tvAquiVaiOComplemento.setText(endereco.getComplemento());
        }
    }
}
