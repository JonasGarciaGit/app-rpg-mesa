package com.example.ppi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import model.Ficha;

public class Pericia_SalvaGuardas extends AppCompatActivity {

    private Ficha ficha;

    private Button btCriarFicha;

    private EditText inspiracaoId, bonusProficiencia, sabedoriaPassiva;
    private EditText forcaId, sabedoriaId, carismaId, inteligenciaId, destrezaId, constituicaoId;
    private CheckBox checkBoxForca, checkBoxSabedoria, checkBoxCarisma, checkBoxInteligencia, checkBoxDestreza, checkBoxConstituicao;
    private EditText acrobacia, adestrarAnimais, arcanismo, atletismo, atuacao, enganacao, furtividade, historia,
            intimidacao, intuicao, investigacao, medicina, natureza, percepcao, persuasao, prestigitacao, religiao, sobrevivencia;
    private CheckBox checkBoxAcrobacia, checkBoxAdrestrarAnimais, checkBoxArcanismo, checkBoxAtletismo, checkBoxAtuacao, checkBoxEnganacao,
            checkBoxFurtividade, checkBoxHistoria, checkBoxIntimidacao, checkBoxIntuicao, checkBoxInvestigacao, checkBoxMedicina,
            checkBoxNatureza, checkBoxPercepcao, checkBoxPersuasao, checkBoxPrestigitacao, checkBoxReligiao, checkBoxSobrevivencia;

    public void criarFicha(View view) {
        DatabaseReference fichas = FirebaseDatabase.getInstance().getReference().child("jogadores").child(ficha.getIdJogador()).child("fichas");


        fichas.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> idFichas = new ArrayList<>();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    idFichas.add(ds.getKey());
                }

                ficha.setTxtAcrobacia(acrobacia.getText().toString());
                ficha.setTxtAdestrarAnimais(adestrarAnimais.getText().toString());
                ficha.setTxtArcanismo(arcanismo.getText().toString());
                ficha.setTxtAtletismo(atletismo.getText().toString());
                ficha.setTxtAtuacao(atuacao.getText().toString());
                ficha.setTxtEnganacao(enganacao.getText().toString());
                ficha.setTxtFurtividade(furtividade.getText().toString());
                ficha.setTxtHistoria(historia.getText().toString());
                ficha.setTxtIntuicao(intuicao.getText().toString());
                ficha.setTxtInvestigacao(investigacao.getText().toString());
                ficha.setTxtMedicina(medicina.getText().toString());
                ficha.setTxtNatureza(natureza.getText().toString());
                ficha.setTxtPercepcao(percepcao.getText().toString());
                ficha.setTxtPersuasao(persuasao.getText().toString());
                ficha.setTxtPrestigitacao(prestigitacao.getText().toString());
                ficha.setTxtReligiao(religiao.getText().toString());
                ficha.setTxtSobrevivencia(sobrevivencia.getText().toString());
                ficha.setTxtIntimidacao(intimidacao.getText().toString());
                ficha.setAcrobacia(checkBoxAcrobacia.isChecked());
                ficha.setAdestrarAnimais(checkBoxAdrestrarAnimais.isChecked());
                ficha.setArcanismo(checkBoxArcanismo.isChecked());
                ficha.setAtletismo(checkBoxAtletismo.isChecked());
                ficha.setAtuacao(checkBoxAtuacao.isChecked());
                ficha.setEnganacao(checkBoxEnganacao.isChecked());
                ficha.setFurtividade(checkBoxFurtividade.isChecked());
                ficha.setHistoria(checkBoxHistoria.isChecked());
                ficha.setIntuicao(checkBoxIntuicao.isChecked());
                ficha.setInvestigacao(checkBoxInvestigacao.isChecked());
                ficha.setMedicina(checkBoxMedicina.isChecked());
                ficha.setNatureza(checkBoxNatureza.isChecked());
                ficha.setPercepcao(checkBoxPercepcao.isChecked());
                ficha.setPersuasao(checkBoxPersuasao.isChecked());
                ficha.setPrestigitacao(checkBoxPrestigitacao.isChecked());
                ficha.setReligiao(checkBoxReligiao.isChecked());
                ficha.setSobrevivencia(checkBoxSobrevivencia.isChecked());
                ficha.setIntimidacao(checkBoxIntimidacao.isChecked());
                ficha.setInspiracao(inspiracaoId.getText().toString());
                ficha.setBonusDeProficiencia(bonusProficiencia.getText().toString());
                ficha.setSabedoriaPassiva(sabedoriaPassiva.getText().toString());
                ficha.setTxtForca(forcaId.getText().toString());
                ficha.setTxtSabedoria(sabedoriaId.getText().toString());
                ficha.setTxtCarisma(carismaId.getText().toString());
                ficha.setTxtInteligencia(inteligenciaId.getText().toString());
                ficha.setTxtDestreza(destrezaId.getText().toString());
                ficha.setTxtCostituicao(constituicaoId.getText().toString());
                ficha.setForca(checkBoxForca.isChecked());
                ficha.setSabedoria(checkBoxSabedoria.isChecked());
                ficha.setCarisma(checkBoxCarisma.isChecked());
                ficha.setInteligencia(checkBoxInteligencia.isChecked());
                ficha.setDestreza(checkBoxDestreza.isChecked());
                ficha.setCostituicao(checkBoxConstituicao.isChecked());


                if (!idFichas.contains("001") && !idFichas.contains("002") && !idFichas.contains("003")) {
                    ficha.setId("001");
                }
                if (idFichas.contains("001") && !idFichas.contains("002") && !idFichas.contains("003")) {
                    ficha.setId("002");
                }
                if (!idFichas.contains("001") && !idFichas.contains("002") && idFichas.contains("003")) {
                    ficha.setId("001");
                }
                if (!idFichas.contains("001") && idFichas.contains("002") && idFichas.contains("003")) {
                    ficha.setId("001");
                }

                if (!idFichas.contains("001") && idFichas.contains("002") && !idFichas.contains("003")) {
                    ficha.setId("001");
                }

                if (idFichas.contains("001") && !idFichas.contains("002") && idFichas.contains("003")) {
                    ficha.setId("002");
                }
                if (idFichas.contains("001") && idFichas.contains("002") && !idFichas.contains("003")) {
                    ficha.setId("003");
                }

                Log.i("criacaoFicha", "To aqui" + "idFicha = " + ficha.getId());
                fichas.child(ficha.getId()).setValue(ficha);

                Bundle param = new Bundle();
                param.putString("id_usuario", ficha.getIdJogador());
                Intent intent = new Intent(Pericia_SalvaGuardas.this, GerenciamentoFichas.class);
                intent.putExtras(param);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("erroCriarFicha", error.getMessage());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pericia__salva_guardas);

        ficha = (Ficha) getIntent().getSerializableExtra("ficha");

        btCriarFicha = findViewById(R.id.btCriarFicha);
        checkBoxAcrobacia = findViewById(R.id.checkBoxAcrobacia);
        checkBoxAdrestrarAnimais = findViewById(R.id.checkBoxAdestrarAnimais);
        checkBoxArcanismo = findViewById(R.id.checkBoxArcanismo);
        checkBoxAtletismo = findViewById(R.id.checkBoxAtletismo);
        checkBoxAtuacao = findViewById(R.id.checkBoxAtuacao);
        checkBoxEnganacao = findViewById(R.id.checkBoxEnganacao);
        checkBoxFurtividade = findViewById(R.id.checkBoxFurtividade);
        checkBoxHistoria = findViewById(R.id.checkBoxHistoria);
        checkBoxIntimidacao = findViewById(R.id.checkBoxIntimidacao);
        checkBoxIntuicao = findViewById(R.id.checkBoxIntuicao);
        checkBoxInvestigacao = findViewById(R.id.checkBoxInvestigacao);
        checkBoxMedicina = findViewById(R.id.checkBoxMedicina);
        checkBoxNatureza = findViewById(R.id.checkBoxNatureza);
        checkBoxPercepcao = findViewById(R.id.checkBoxPercepcao);
        checkBoxPersuasao = findViewById(R.id.checkBoxPersuasao);
        checkBoxPrestigitacao = findViewById(R.id.checkBoxPrestidigitacao);
        checkBoxReligiao = findViewById(R.id.checkBoxReligiao);
        checkBoxSobrevivencia = findViewById(R.id.checkBoxSobrevivencia);
        acrobacia = findViewById(R.id.checkAcrobaciaValue);
        adestrarAnimais = findViewById(R.id.checkAdestrarAnimaisValue);
        arcanismo = findViewById(R.id.checkArcanismoValue);
        atletismo = findViewById(R.id.checkAtletismoValue);
        atuacao = findViewById(R.id.checkAtuacaoValue);
        enganacao = findViewById(R.id.checkEnganacaoValue);
        furtividade = findViewById(R.id.checkFurtividadeValue);
        historia = findViewById(R.id.checkHistoriaValue);
        intimidacao = findViewById(R.id.checkIntimidacaoValue);
        intuicao = findViewById(R.id.checkIntuicaoValue);
        investigacao = findViewById(R.id.checkInvestigacaoValue);
        medicina = findViewById(R.id.checkMedicinaValue);
        natureza = findViewById(R.id.checkNaturezaValue);
        percepcao = findViewById(R.id.checkPercepcaoValue);
        persuasao = findViewById(R.id.checkPersuasaoValue);
        prestigitacao = findViewById(R.id.checkPrestidigitacaoValue);
        religiao = findViewById(R.id.checkReligiaoValue);
        sobrevivencia = findViewById(R.id.checkSobrevivenciaValue);
        inspiracaoId = findViewById(R.id.inspiracaoId);
        bonusProficiencia = findViewById(R.id.proficienciaId);
        sabedoriaPassiva = findViewById(R.id.sabedoriaPassivaId);
        forcaId = findViewById(R.id.checkForcaValue);
        sabedoriaId = findViewById(R.id.checkSabedoriaValue);
        carismaId = findViewById(R.id.checkCarismaValue);
        inteligenciaId = findViewById(R.id.checkInteligenciaValue);
        destrezaId = findViewById(R.id.checkDestrezaValue);
        constituicaoId = findViewById(R.id.checkConstituicaoValue);
        checkBoxForca = findViewById(R.id.checkBoxForca);
        checkBoxSabedoria = findViewById(R.id.checkBoxSabedoria);
        checkBoxCarisma = findViewById(R.id.checkBoxCarisma);
        checkBoxInteligencia = findViewById(R.id.checkBoxInteligencia);
        checkBoxDestreza = findViewById(R.id.checkBoxDestreza);
        checkBoxConstituicao = findViewById(R.id.checkBoxConstituicao);

        carregaDados();
    }

    public void carregaDados() {
        checkBoxForca.setChecked(ficha.isForca());
        checkBoxSabedoria.setChecked(ficha.isSabedoria());
        checkBoxCarisma.setChecked(ficha.isCarisma());
        checkBoxInteligencia.setChecked(ficha.isInteligencia());
        checkBoxDestreza.setChecked(ficha.isDestreza());
        checkBoxConstituicao.setChecked(ficha.isCostituicao());
    }
}
