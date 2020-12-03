package com.example.ppi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import model.Ficha;

public class Caracteristica extends AppCompatActivity {

    private Ficha ficha;
    private Ficha alterarFicha = null;
    private String codSessao = null;
    private TextInputLayout tracosPersonalidades, ideais, vinculos, defeitos, caractTracosRaciais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caracteristica);

        tracosPersonalidades = findViewById(R.id.tracosPersonalidadesId);
        ideais  = findViewById(R.id.ideaisId);
        vinculos = findViewById(R.id.vinculosId);
        defeitos = findViewById(R.id.defeitosId);
        caractTracosRaciais = findViewById(R.id.caracTracosRaciaisId);
        codSessao = getIntent().getStringExtra("cod_sessao");
        ficha = (Ficha) getIntent().getSerializableExtra("ficha");
        alterarFicha = (Ficha) getIntent().getSerializableExtra("alterarFicha");

        if(alterarFicha != null){
            carregaDadosAlterar();
        }
    }

    public void buttonCaracteristica (View view) {
        ficha.setTracosPersonalidades(tracosPersonalidades.getEditText().getText().toString());
        ficha.setIdeias(ideais.getEditText().getText().toString());
        ficha.setVinculos(vinculos.getEditText().getText().toString());
        ficha.setDefeitos(defeitos.getEditText().getText().toString());
        ficha.setCaracteristicasTracosRaciais(caractTracosRaciais.getEditText().getText().toString());

        Bundle bundle = new Bundle();
        bundle.putSerializable("ficha", ficha);
        if(alterarFicha != null){
            bundle.putSerializable("alterarFicha", alterarFicha);
        }
        if(codSessao != null){
            bundle.putString("cod_sessao", getIntent().getStringExtra("cod_sessao"));
            bundle.putString("slot_jogador", getIntent().getStringExtra("slot_jogador"));
        }

        Intent intent = new Intent(getApplicationContext(), Pericia_SalvaGuardas.class);
        intent.putExtras(bundle);
        startActivity(intent);


    }


    public void carregaDadosAlterar(){
        tracosPersonalidades.getEditText().setText(alterarFicha.getTracosPersonalidades());
        ideais.getEditText().setText(alterarFicha.getIdeias());
        vinculos.getEditText().setText(alterarFicha.getVinculos());
        defeitos.getEditText().setText(alterarFicha.getDefeitos());
        caractTracosRaciais.getEditText().setText(alterarFicha.getCaracteristicasTracosRaciais());
    }

}
