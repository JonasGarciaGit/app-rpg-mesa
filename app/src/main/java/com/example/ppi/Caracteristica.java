package com.example.ppi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import model.Ficha;

public class Caracteristica extends AppCompatActivity {

    private Ficha ficha;
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

        ficha = (Ficha) getIntent().getSerializableExtra("ficha");
    }

    public void buttonCaracteristica (View view) {
        ficha.setTracosPersonalidades(tracosPersonalidades.getEditText().getText().toString());
        ficha.setIdeias(ideais.getEditText().getText().toString());
        ficha.setVinculos(vinculos.getEditText().getText().toString());
        ficha.setDefeitos(defeitos.getEditText().getText().toString());
        ficha.setCaracteristicasTracosRaciais(caractTracosRaciais.getEditText().getText().toString());

        Bundle bundle = new Bundle();
        bundle.putSerializable("ficha", ficha);


        Intent intent = new Intent(getApplicationContext(), Pericia_SalvaGuardas.class);
        intent.putExtras(bundle);
        startActivity(intent);


    }

}
