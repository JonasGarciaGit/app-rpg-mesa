package com.example.ppi;

import android.app.AppComponentFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TelaEditar extends AppCompatActivity {

    TextView idJogador;
    TextView idFicha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_editar);

        idJogador = findViewById(R.id.idJogador);
        idFicha = findViewById(R.id.idFicha);

        idJogador.setText(getIntent().getStringExtra("id_jogador"));
        idFicha.setText(getIntent().getStringExtra("id_ficha"));

    }
}
