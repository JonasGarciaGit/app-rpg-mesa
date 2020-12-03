package com.example.ppi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Ficha;

public class Inventario extends AppCompatActivity {



    private Ficha ficha;
    private Ficha alterarFicha = null;
    private String codSessao = null;
    private EditText nomeAtq1, nomeAtq2, nomeAtq3, bonusAtq1, bonusAtq2, bonusAtq3, danoAtq1, danoAtq2, danoAtq3, pcld, ppld, peld, pold, plld;
    private TextInputLayout atqConjud, descricaoEquipamento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);
        codSessao = getIntent().getStringExtra("cod_sessao");
        alterarFicha = (Ficha) getIntent().getSerializableExtra("alterarFicha");
        nomeAtq1 = findViewById(R.id.nomeAtq1);
        nomeAtq2 = findViewById(R.id.nomeAtq2);
        nomeAtq3 = findViewById(R.id.nomeAtq3);
        bonusAtq1 = findViewById(R.id.bonusAtq1);
        bonusAtq2 = findViewById(R.id.bonusAtq2);
        bonusAtq3 = findViewById(R.id.bonusAtq3);
        danoAtq1 = findViewById(R.id.danoAtq1);
        danoAtq2 = findViewById(R.id.danoAtq2);
        danoAtq3 = findViewById(R.id.danoAtq3);
        pcld = findViewById(R.id.pcId);
        ppld = findViewById(R.id.ppId);
        peld = findViewById(R.id.peId);
        pold = findViewById(R.id.poId);
        plld = findViewById(R.id.plId);
        atqConjud = findViewById(R.id.atqConjuId);
        descricaoEquipamento = findViewById(R.id.descEquipamento);

        ficha = (Ficha) getIntent().getSerializableExtra("ficha");
        carregaDados();

        if(alterarFicha != null){
            carregaDadosAlterar();
        }
    }

    public void buttonInventario(View view) {
        List<String> nomeAtaqueConjuracao = new ArrayList<>();
        nomeAtaqueConjuracao.addAll(Arrays.asList(nomeAtq1.getText().toString(),nomeAtq2.getText().toString(),nomeAtq3.getText().toString()));
        ficha.setNomeAtaqueConjuracao(nomeAtaqueConjuracao);

        List<String> bonusAtaque = new ArrayList<>();
        bonusAtaque.addAll(Arrays.asList(bonusAtq1.getText().toString(),bonusAtq2.getText().toString(),bonusAtq3.getText().toString()));
        ficha.setBonusAtaque(bonusAtaque);

        List<String> danoTipo = new ArrayList<>();
        danoTipo.addAll(Arrays.asList(danoAtq1.getText().toString(),danoAtq2.getText().toString(), danoAtq3.getText().toString()));
        ficha.setDanoTipo(danoTipo);

        ficha.setAtaqueCojuracaoDescricao(atqConjud.getEditText().getText().toString());

        ficha.setPc(pcld.getText().toString());
        ficha.setPp(ppld.getText().toString());
        ficha.setPe(peld.getText().toString());
        ficha.setPo(pold.getText().toString());
        ficha.setPl(plld.getText().toString());

        ficha.setEquipamentoDescricao(descricaoEquipamento.getEditText().getText().toString());

        //Bundle é um Pacote
        Bundle bundle = new Bundle();
        if(alterarFicha != null){
            bundle.putSerializable("alterarFicha", alterarFicha);
        }
        if(codSessao != null){
            bundle.putString("cod_sessao", getIntent().getStringExtra("cod_sessao"));
            bundle.putString("slot_jogador", getIntent().getStringExtra("slot_jogador"));
        }
        bundle.putSerializable("ficha",ficha);
        Intent intent = new Intent(getApplicationContext(), Caracteristica.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void carregaDados(){
        descricaoEquipamento.getEditText().setText(ficha.getEquipamentoDescricao());

        for(int i = 0 ; i < ficha.getNomeAtaqueConjuracao().size() ; i++){
            if(i == 0){
                nomeAtq1.setText(ficha.getNomeAtaqueConjuracao().get(0).isEmpty() ? "" : ficha.getNomeAtaqueConjuracao().get(0));
                danoAtq1.setText(ficha.getDanoTipo().get(0).isEmpty() ? "" : ficha.getDanoTipo().get(0));
            }
            if(i == 1){
                nomeAtq2.setText(ficha.getNomeAtaqueConjuracao().get(1).isEmpty() ? "" : ficha.getNomeAtaqueConjuracao().get(1));
                danoAtq2.setText(ficha.getDanoTipo().get(1).isEmpty() ? "" : ficha.getDanoTipo().get(1));
            }
            if(i == 2){
                nomeAtq3.setText(ficha.getNomeAtaqueConjuracao().get(2).isEmpty() ? "" : ficha.getNomeAtaqueConjuracao().get(2));
                danoAtq3.setText(ficha.getDanoTipo().get(2).isEmpty() ? "" : ficha.getDanoTipo().get(2));
            }
        }
    }


    private void carregaDadosAlterar(){
        nomeAtq1.setText(alterarFicha.getNomeAtaqueConjuracao().get(0).isEmpty() ? "" : alterarFicha.getNomeAtaqueConjuracao().get(0));
        nomeAtq2.setText(alterarFicha.getNomeAtaqueConjuracao().get(1).isEmpty() ? "" : alterarFicha.getNomeAtaqueConjuracao().get(1));
        nomeAtq3.setText(alterarFicha.getNomeAtaqueConjuracao().get(2).isEmpty() ? "" : alterarFicha.getNomeAtaqueConjuracao().get(2));
        bonusAtq1.setText(alterarFicha.getBonusAtaque().get(0).isEmpty() ? "" : alterarFicha.getBonusAtaque().get(0));
        bonusAtq2.setText(alterarFicha.getBonusAtaque().get(1).isEmpty() ? "" : alterarFicha.getBonusAtaque().get(1));
        bonusAtq3.setText(alterarFicha.getBonusAtaque().get(2).isEmpty() ? "" : alterarFicha.getBonusAtaque().get(2));
        danoAtq1.setText(alterarFicha.getDanoTipo().get(0).isEmpty() ? "" : alterarFicha.getDanoTipo().get(0));
        danoAtq2.setText(alterarFicha.getDanoTipo().get(1).isEmpty() ? "" : alterarFicha.getDanoTipo().get(1));
        danoAtq3.setText(alterarFicha.getDanoTipo().get(2).isEmpty() ? "" : alterarFicha.getDanoTipo().get(2));
        pcld.setText(alterarFicha.getPc());
        ppld.setText(alterarFicha.getPp());
        peld.setText(alterarFicha.getPe());
        pold.setText(alterarFicha.getPo());
        plld.setText(alterarFicha.getPl());
        atqConjud.getEditText().setText(alterarFicha.getAtaqueCojuracaoDescricao());
        descricaoEquipamento.getEditText().setText(alterarFicha.getEquipamentoDescricao());
    }
}
