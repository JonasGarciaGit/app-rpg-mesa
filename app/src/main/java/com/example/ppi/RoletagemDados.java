package com.example.ppi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class RoletagemDados extends AppCompatActivity {

    Button btD4;
    Button btD6;
    Button btD8;
    Button btD10;
    Button btD12;
    Button btD20;

    TextView textResultado;

    private String codSessao;
    DatabaseReference sessao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roletagem_dados);

        btD4 = findViewById(R.id.btD4);
        btD6 = findViewById(R.id.btD6);
        btD8 = findViewById(R.id.btD8);
        btD10 = findViewById(R.id.btD10);
        btD12 = findViewById(R.id.btD12);
        btD20 = findViewById(R.id.btD20);

        textResultado = findViewById(R.id.resultado);
        codSessao = getIntent().getStringExtra("cod_sessao");
        sessao = FirebaseDatabase.getInstance().getReference().child("sessoes").child(codSessao);
    }

    public void rodarDadoD4(View view){
        Random random = new Random();
        int resultado =  random.nextInt((4 - 1) + 1) + 1;
        textResultado.setText(String.valueOf(resultado));
        sessao.child("valorDado").setValue(String.valueOf(resultado));
    }


    public void rodarDadoD6(View view){
        Random random = new Random();
        int resultado =  random.nextInt((6 - 1) + 1) + 1;
        textResultado.setText(String.valueOf(resultado));
        sessao.child("valorDado").setValue(String.valueOf(resultado));
    }


    public void rodarDadoD8(View view){
        Random random = new Random();
        int resultado =  random.nextInt((8 - 1) + 1) + 1;
        textResultado.setText(String.valueOf(resultado));
        sessao.child("valorDado").setValue(String.valueOf(resultado));
    }


    public void rodarDadoD10(View view){
        Random random = new Random();
        int resultado =  random.nextInt((10 - 1) + 1) + 1;
        textResultado.setText(String.valueOf(resultado));
        sessao.child("valorDado").setValue(String.valueOf(resultado));
    }


    public void rodarDadoD12(View view){
        Random random = new Random();
        int resultado =  random.nextInt((12 - 1) + 1) + 1;
        textResultado.setText(String.valueOf(resultado));
        sessao.child("valorDado").setValue(String.valueOf(resultado));
    }


    public void rodarDadoD20(View view){
        Random random = new Random();
        int resultado =  random.nextInt((20 - 1) + 1) + 1;
        textResultado.setText(String.valueOf(resultado));
        sessao.child("valorDado").setValue(String.valueOf(resultado));
    }

}