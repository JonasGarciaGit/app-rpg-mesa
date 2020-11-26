package com.example.ppi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Ficha;

public class FichaPersonagem extends AppCompatActivity {

    DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();

    private EditText nomePersonagem, antecedente, tendencia, pontosExperiencia, nomeJogador, nivel;
    private EditText forca, destreza, constituicao, inteligencia, sabedoria, carisma, classeArmadura, iniciativa, deslocamento;
    private EditText extraForca, extraDestreza, extraCostituicao, extraInteligencia, extraSabedoria, extraCarisma;
    private EditText pvTotais, pvAtuais, pvTemporario, dadosVida, total;
    private CheckBox sucesso1, sucesso2, sucesso3, fracasso1, fracasso2, fracasso3;
    private Spinner classe, raca, subRaca;

    public String idJogador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personagem);
        idJogador = getIntent().getStringExtra("id_jogador");

        nivel = findViewById(R.id.nivel);
        nomePersonagem = findViewById(R.id.nomePersonagem);
        antecedente = findViewById(R.id.antecedente);
        classe = findViewById(R.id.classeNivel);
        raca = findViewById(R.id.raca);
        tendencia = findViewById(R.id.tendencia);
        pontosExperiencia = findViewById(R.id.pontosExperiencia);
        subRaca = findViewById(R.id.subRacaId);
        nomeJogador = findViewById(R.id.nomeJogador);
        forca = findViewById(R.id.forca);
        destreza = findViewById(R.id.destreza);
        constituicao = findViewById(R.id.constituicao);
        inteligencia = findViewById(R.id.inteligencia);
        sabedoria = findViewById(R.id.sabedoria);
        carisma = findViewById(R.id.carisma);
        extraForca = findViewById(R.id.extraForca);
        extraDestreza = findViewById(R.id.extraDestreza);
        extraCostituicao = findViewById(R.id.extraConstituicao);
        extraInteligencia = findViewById(R.id.extraInteligencia);
        extraSabedoria = findViewById(R.id.extraSabedoria);
        extraCarisma = findViewById(R.id.extraCarisma);
        classeArmadura = findViewById(R.id.classeArmd);
        iniciativa =  findViewById(R.id.iniciativa);
        deslocamento =  findViewById(R.id.deslocamento);
        pvTotais = findViewById(R.id.pvTotais);
        pvAtuais = findViewById(R.id.pvAtuais);
        pvTemporario = findViewById(R.id.pvTemporário);
        dadosVida = findViewById(R.id.dadosVida);
        total = findViewById(R.id.total);
        sucesso1 =  findViewById(R.id.sucesso1);
        sucesso2 = findViewById(R.id.sucesso2);
        sucesso3 = findViewById(R.id.sucesso3);
        fracasso1 = findViewById(R.id.fracasso1);
        fracasso2 = findViewById(R.id.fracasso2);
        fracasso3 = findViewById(R.id.fracasso3);

        //variavel global, gambiarra
        final String[] classeNomeGlobal = {""};

        //Identifica classe e seta os pontos.
        classe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String classeNome = parent.getItemAtPosition(position).toString();
                classeNomeGlobal[0] = classeNome;
                carregaDadosClasse(classeNome);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // sometimes you need nothing here
            }
        });

        //Identifica a raca e carrega as subRacas.
        raca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    String raca = adapterView.getItemAtPosition(position).toString();
                    carregaSubRaca(raca);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


       subRaca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
               String subRaca = adapterView.getItemAtPosition(position).toString();
               carregaDadosSubRaca(subRaca);
               carregaDadosClasse(classeNomeGlobal[0]);
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });
    }



    public void buttonFicha (View view){
        List<Boolean> listaSucesso = new ArrayList<>();
        List<Boolean> listaFracasso = new ArrayList<>();

        Ficha ficha = new Ficha();
        ficha.setIdJogador(idJogador);
        ficha.setNomeDoPersonagem(nomePersonagem.getText().toString());
        ficha.setAntecedente(antecedente.getText().toString());
        ficha.setClasse(classe.getSelectedItem().toString());
        ficha.setNivel(nivel.getText().toString());
        ficha.setRaca(raca.getSelectedItem().toString());
        ficha.setTendencia(tendencia.getText().toString());
        ficha.setPontosDeExperiencia(pontosExperiencia.getText().toString());
        ficha.setNomeDoJogador(nomeJogador.getText().toString());
        ficha.setEquipamentoDescricao(carregarEquipamento(classe.getSelectedItem().toString()));
        ficha.setSubRaca(subRaca.getSelectedItem().toString().isEmpty() ? "" : subRaca.getSelectedItem().toString());
        ficha.setPtsForça(forca.getText().toString());
        ficha.setPtsInteligencia(inteligencia.getText().toString());
        ficha.setPtsDestreza(destreza.getText().toString());
        ficha.setPtsConstituicao(constituicao.getText().toString());
        ficha.setPtsSabedoria(sabedoria.getText().toString());
        ficha.setPtsCarisma(carisma.getText().toString());
        ficha.setExtraForca(extraForca.getText().toString());
        ficha.setExtraDestreza(extraDestreza.getText().toString());
        ficha.setExtraConstituicao(extraCostituicao.getText().toString());
        ficha.setExtraInteligencia(extraInteligencia.getText().toString());
        ficha.setExtraSabedoria(extraSabedoria.getText().toString());
        ficha.setExtraCarisma(extraCarisma.getText().toString());
        ficha.setClasseArmadura(classeArmadura.getText().toString());
        ficha.setIniciativa(iniciativa.getText().toString());
        ficha.setDeslocamento(deslocamento.getText().toString());
        ficha.setPvTotais(pvTotais.getText().toString());
        ficha.setPvAtuais(pvAtuais.getText().toString());
        ficha.setPvTemporario(pvTemporario.getText().toString());
        ficha.setDadosDeVida(dadosVida.getText().toString());
        ficha.setTotal(total.getText().toString());
        listaSucesso.add(sucesso1.isChecked());
        listaSucesso.add(sucesso2.isChecked());
        listaSucesso.add(sucesso3.isChecked());
        listaFracasso.add(fracasso1.isChecked());
        listaFracasso.add(fracasso2.isChecked());
        listaFracasso.add(fracasso3.isChecked());

        carregarArmas(classe.getSelectedItem().toString(), ficha);
        carregaTesteResistenciaPericias(classe.getSelectedItem().toString(), ficha);

        Bundle bundle = new Bundle();
        bundle.putSerializable("ficha",ficha);

        Intent intent = new Intent(getApplicationContext(), Inventario.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void carregaSubRaca(String nomeRaca){
        if(nomeRaca.equals("Anao")){
            ArrayList<String> addSubclass = new ArrayList<>();
            addSubclass.add("Anao da colina");
            addSubclass.add("Anao da montanha");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, addSubclass);
            subRaca.setAdapter(arrayAdapter);
            ajustarInfo("","","2","","","","7,5 m");
        }

        if(nomeRaca.equals("Elfo")){
            ArrayList<String> addSubclass = new ArrayList<>();
            addSubclass.add("Elfo da floresta");
            addSubclass.add("Alto elfo");
            addSubclass.add("Elfo negro");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, addSubclass);
            subRaca.setAdapter(arrayAdapter);
            ajustarInfo("","2","","","","","9 m");
        }

        if(nomeRaca.equals("Halfling")){
            ArrayList<String> addSubclass = new ArrayList<>();
            addSubclass.add("Pes leves");
            addSubclass.add("Robustos");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, addSubclass);
            subRaca.setAdapter(arrayAdapter);
            ajustarInfo("","2","","","","","7,5 m");
        }

        if(nomeRaca.equals("Humano")){
            ArrayList<String> addSubclass = new ArrayList<>();
            addSubclass.add("");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, addSubclass);
            subRaca.setAdapter(arrayAdapter);
            ajustarInfo("1","1","1","1","1","1","9 m");
        }

        if(nomeRaca.equals("Draconato")){
            ArrayList<String> addSubclass = new ArrayList<>();
            addSubclass.add("");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, addSubclass);
            subRaca.setAdapter(arrayAdapter);
            ajustarInfo("2","","","","","1","9 m");
        }

        if(nomeRaca.equals("Gnomo")){
            ArrayList<String> addSubclass = new ArrayList<>();
            addSubclass.add("Gnomo da floresta");
            addSubclass.add("Gnomo das rochas");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, addSubclass);
            subRaca.setAdapter(arrayAdapter);
            ajustarInfo("","","","2","","","7,5 m");
        }

        if(nomeRaca.equals("Meio-elfo")){
            ArrayList<String> addSubclass = new ArrayList<>();
            addSubclass.add("");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, addSubclass);
            subRaca.setAdapter(arrayAdapter);
            ajustarInfo("","","","","","2","9 m");
        }

        if(nomeRaca.equals("Meio-orc")){
            ArrayList<String> addSubclass = new ArrayList<>();
            addSubclass.add("");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, addSubclass);
            subRaca.setAdapter(arrayAdapter);
            ajustarInfo("2","","1","","","","9 m");
        }

        if(nomeRaca.equals("Tieflings")){
            ArrayList<String> addSubclass = new ArrayList<>();
            addSubclass.add("");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, addSubclass);
            subRaca.setAdapter(arrayAdapter);
            ajustarInfo("","","","1","","2","9 m");
        }
    }

    public void carregaDadosSubRaca(String nomeSubRaca){
        //Anao
        if(nomeSubRaca.equals("Anao da montanha")){
            ajustarInfo("2","","2","","","","7,5 m");
        }
        if(nomeSubRaca.equals("Anao da colina")){
            ajustarInfo("","","2","","1","","7,5 m");
        }
        //Elfo
        if(nomeSubRaca.equals("Elfo da floresta")){
            ajustarInfo("","2","","","1","","10,5 m");
        }
        if(nomeSubRaca.equals("Alto elfo")){
            ajustarInfo("","2","","1","","","9 m");
        }
        if(nomeSubRaca.equals("Elfo negro")){
            ajustarInfo("","2","","","","1","9 m");
        }
        //Halfling
        if(nomeSubRaca.equals("Pes leves")){
            ajustarInfo("","2","","","","1","7,5 m");
        }
        if(nomeSubRaca.equals("Robustos")){
            ajustarInfo("","2","1","","","","7,5 m");
        }
        //Gnomos
        if(nomeSubRaca.equals("Gnomo da floresta")){
            ajustarInfo("","1","","2","","","7,5 m");
        }

        if(nomeSubRaca.equals("Gnomo das rochas")){
            ajustarInfo("","","1","2","","","7,5 m");
        }
    }

    public void carregaDadosClasse(String classeNome){
        if(classeNome.equals("Barbaro")){
            ajustarInfoClasse("D12",calculaPropriedadesIniciais(10,extraCostituicao.getText().toString(), extraDestreza.getText().toString()) ,calculaPropriedadesIniciais(12,extraCostituicao.getText().toString(),""),calculaPropriedadesIniciais(12,extraCostituicao.getText().toString(),"") );
        }
        if(classeNome.equals("Bardo")){
            ajustarInfoClasse("D8",calculaPropriedadesIniciais(11, extraDestreza.getText().toString(),""),calculaPropriedadesIniciais(8 , extraCostituicao.getText().toString(), ""),calculaPropriedadesIniciais(8 , extraCostituicao.getText().toString(), ""));
        }
        if(classeNome.equals("Bruxo")){
            ajustarInfoClasse("D8",calculaPropriedadesIniciais(11, extraDestreza.getText().toString(),""),calculaPropriedadesIniciais(8 , extraCostituicao.getText().toString(), ""),calculaPropriedadesIniciais(8 , extraCostituicao.getText().toString(), ""));
        }
        if(classeNome.equals("Clerigo")){
            ajustarInfoClasse("D8",calculaPropriedadesIniciais(11, extraDestreza.getText().toString(),"2"),calculaPropriedadesIniciais(8 , extraCostituicao.getText().toString(), ""),calculaPropriedadesIniciais(8 , extraCostituicao.getText().toString(), ""));
        }
        if(classeNome.equals("Druida")){
            ajustarInfoClasse("D8",calculaPropriedadesIniciais(11, extraDestreza.getText().toString(),"2"),calculaPropriedadesIniciais(8 , extraCostituicao.getText().toString(), ""),calculaPropriedadesIniciais(8 , extraCostituicao.getText().toString(), ""));
        }
        if(classeNome.equals("Feiticeiro")){
            ajustarInfoClasse("D6",calculaPropriedadesIniciais(10, extraDestreza.getText().toString(),""),calculaPropriedadesIniciais(6 , extraCostituicao.getText().toString(), ""),calculaPropriedadesIniciais(6 , extraCostituicao.getText().toString(), ""));
        }
        if(classeNome.equals("Guerreiro")){
            ajustarInfoClasse("D10",16,calculaPropriedadesIniciais(10 , extraCostituicao.getText().toString(), ""),calculaPropriedadesIniciais(10 , extraCostituicao.getText().toString(), ""));
        }
        if(classeNome.equals("Ladino")){
            ajustarInfoClasse("D8",calculaPropriedadesIniciais(11, extraDestreza.getText().toString(),""),calculaPropriedadesIniciais(8 , extraCostituicao.getText().toString(), ""),calculaPropriedadesIniciais(8 , extraCostituicao.getText().toString(), ""));
        }
        if(classeNome.equals("Mago")){
            ajustarInfoClasse("D6",calculaPropriedadesIniciais(10, extraDestreza.getText().toString(),""),calculaPropriedadesIniciais(6 , extraCostituicao.getText().toString(), ""),calculaPropriedadesIniciais(6 , extraCostituicao.getText().toString(), ""));
        }
        if(classeNome.equals("Monge")){
            ajustarInfoClasse("D8",calculaPropriedadesIniciais(10, extraDestreza.getText().toString(),extraSabedoria.getText().toString()),calculaPropriedadesIniciais(8 , extraCostituicao.getText().toString(), ""),calculaPropriedadesIniciais(8 , extraCostituicao.getText().toString(), ""));
        }
        if(classeNome.equals("Paladino")){
            ajustarInfoClasse("D10",16,calculaPropriedadesIniciais(10 , extraCostituicao.getText().toString(), ""),calculaPropriedadesIniciais(10 , extraCostituicao.getText().toString(), ""));
        }
        if(classeNome.equals("Patrulheiro")){
            ajustarInfoClasse("D10",calculaPropriedadesIniciais(13 , extraDestreza.getText().toString(), ""),calculaPropriedadesIniciais(10 , extraCostituicao.getText().toString(), ""),calculaPropriedadesIniciais(10 , extraCostituicao.getText().toString(), ""));
        }
    }



    public void ajustarInfo(String ptsExtraFor, String ptsExtraDex,String ptsExtraConst, String ptsExtraInt, String ptsExtraSab, String ptsExtraCarisma, String desloc ){
        extraForca.setText(ptsExtraFor);
        extraDestreza.setText(ptsExtraDex);
        extraCostituicao.setText(ptsExtraConst);
        extraInteligencia.setText(ptsExtraInt);
        extraSabedoria.setText(ptsExtraSab);
        extraCarisma.setText(ptsExtraCarisma);
        deslocamento.setText(desloc);
    }

    public void ajustarInfoClasse(String dadoVida,Integer classArm, Integer pontoVidaAtual, Integer pontoVidaTemp){
        dadosVida.setText(dadoVida);
        classeArmadura.setText(classArm.toString());
        pvAtuais.setText(pontoVidaAtual.toString());
        pvTemporario.setText(pontoVidaTemp.toString());
    }

    public Integer calculaPropriedadesIniciais(Integer valorInicial,String modificador1,String modificador2){
        return valorInicial + Integer.parseInt(modificador1.equals("") ? "0" : modificador1 )  + Integer.parseInt((modificador2.equals("") ? "0" : modificador2 ));
    }

    public void carregarArmas(String nomeClasse, Ficha ficha){
        List<String> nomeAtaqueConjuracao = new ArrayList<>();
        List<String> danoTipo = new ArrayList<>();
        switch (nomeClasse){
            case "Barbaro":
                nomeAtaqueConjuracao.add("Machado G");
                danoTipo.add("1D12 - Cortante");
                nomeAtaqueConjuracao.add("Machadinha");
                danoTipo.add("1D6 - Cortante");
                nomeAtaqueConjuracao.add("Azagaia 4x");
                danoTipo.add("1D6 - Perfurante");
                ficha.setNomeAtaqueConjuracao(nomeAtaqueConjuracao);
                ficha.setDanoTipo(danoTipo);
                break;
            case "Bardo":
                nomeAtaqueConjuracao.add("Rapieira");
                danoTipo.add("1D8 - Perfurante");
                nomeAtaqueConjuracao.add("Adaga");
                danoTipo.add("1D4 - Perfurante");
                ficha.setNomeAtaqueConjuracao(nomeAtaqueConjuracao);
                ficha.setDanoTipo(danoTipo);
                break;
            case "Bruxo":
                nomeAtaqueConjuracao.add("Besta leve");
                danoTipo.add("1D8 - Perfurante");
                ficha.setNomeAtaqueConjuracao(nomeAtaqueConjuracao);
                ficha.setDanoTipo(danoTipo);
                break;
            case "Clerigo":
                nomeAtaqueConjuracao.add("Maça");
                danoTipo.add("1D6 - Concussão");
                nomeAtaqueConjuracao.add("Besta leve");
                danoTipo.add("1D8 - Perfurante");
                ficha.setNomeAtaqueConjuracao(nomeAtaqueConjuracao);
                ficha.setDanoTipo(danoTipo);
                break;
            case "Druida":
                nomeAtaqueConjuracao.add("Cimitarra");
                danoTipo.add("1D6 - Cortante");
                ficha.setNomeAtaqueConjuracao(nomeAtaqueConjuracao);
                ficha.setDanoTipo(danoTipo);
                break;
            case "Feiticeiro":
                nomeAtaqueConjuracao.add("Besta leve");
                danoTipo.add("1D8 - Perfurante");
                ficha.setNomeAtaqueConjuracao(nomeAtaqueConjuracao);
                ficha.setDanoTipo(danoTipo);
                break;
            case "Guerreiro":
                nomeAtaqueConjuracao.add("Besta leve");
                danoTipo.add("1D8 - Perfurante");
                ficha.setNomeAtaqueConjuracao(nomeAtaqueConjuracao);
                ficha.setDanoTipo(danoTipo);
                break;
            case "Ladino":
                nomeAtaqueConjuracao.add("Rapieira");
                danoTipo.add("1D8 - Perfurante");
                nomeAtaqueConjuracao.add("Arco curto");
                danoTipo.add("1D6 - Perfurante");
                ficha.setNomeAtaqueConjuracao(nomeAtaqueConjuracao);
                ficha.setDanoTipo(danoTipo);
                break;
            case "Mago":
                nomeAtaqueConjuracao.add("Bordão");
                danoTipo.add("1D6 - Concussão");
                ficha.setNomeAtaqueConjuracao(nomeAtaqueConjuracao);
                ficha.setDanoTipo(danoTipo);
                break;
            case "Monge":
                nomeAtaqueConjuracao.add("Espada curta");
                danoTipo.add("1D6 - Perfurante");
                ficha.setNomeAtaqueConjuracao(nomeAtaqueConjuracao);
                ficha.setDanoTipo(danoTipo);
                break;
            case "Paladino":
                nomeAtaqueConjuracao.add("Azagaia 5x");
                danoTipo.add("1D6 - Perfurante");
                ficha.setNomeAtaqueConjuracao(nomeAtaqueConjuracao);
                ficha.setDanoTipo(danoTipo);
                break;
            case "Patrulheiro":
                nomeAtaqueConjuracao.add("Espada curta (x2)");
                danoTipo.add("1D6 - Perfurante");
                nomeAtaqueConjuracao.add("Arco longo");
                danoTipo.add("1D8 - Perfurante");
                ficha.setNomeAtaqueConjuracao(nomeAtaqueConjuracao);
                ficha.setDanoTipo(danoTipo);
                break;
        }
    }

    public String carregarEquipamento(String nomeClasse){
        String descricaoEquipamento = "";
        switch (nomeClasse){
            case "Barbaro":
                descricaoEquipamento = "\uF0B7 (a) um machado grande ou (b) qualquer arma marcial corpo-a-corpo\n" +
                        "\uF0B7 (a) dois machados de mão ou (b) qualquer arma simples\n" +
                        "\uF0B7 Um pacote de aventureiro e quatro azagaias\n";
                break;
            case "Bardo":
                descricaoEquipamento = "\uF0B7 (a) uma rapieira, (b) uma espada longa ou (c) qualquer\n" +
                        "arma simples\n" +
                        "\uF0B7 (a) um pacote de diplomata ou (b) um pacote de artista\n" +
                        "\uF0B7 (a) um lute ou (b) qualquer outro instrumento musical\n" +
                        "\uF0B7 Armadura de couro e uma adaga\n";
                break;
            case "Bruxo":
                descricaoEquipamento = "\uF0B7 (a) uma besta leve e 20 virotes ou (b) qualquer arma\n" +
                        "simples\n" +
                        "\uF0B7 (a) uma bolsa de componentes ou (b) um foco arcano\n" +
                        "\uF0B7 (a) um pacote de estudioso ou (b) um pacote de\n" +
                        "explorador\n" +
                        "\uF0B7 Armadura de couro, qualquer arma simples e duas\n" +
                        "adagas";
                break;
            case "Clerigo":
                descricaoEquipamento = "\uF0B7 (a) uma maça ou (b) um martelo de guerra (se for\n" +
                        "proficiente)\n" +
                        "\uF0B7 (a) brunea, (b) armadura de couro ou (c) cota de malha\n" +
                        "(se for proficiente)\n" +
                        "\uF0B7 (a) um besta leve e 20 virotes ou (b) qualquer arma\n" +
                        "simples\n" +
                        "\uF0B7 (a) um pacote de sacerdote ou (b) um pacote de\n" +
                        "aventureiro\n" +
                        "\uF0B7 Um escudo e um símbolo sagrado";
                break;
            case "Druida":
                descricaoEquipamento = "\uF0B7 (a) um escudo de madeira ou (b) qualquer arma\n" +
                        "simples\n" +
                        "(a) uma cimitarra ou (b) qualquer arma corpo-a-corpo\n" +
                        "simples\n" +
                        "(a) um pacote de estudioso ou (b) um pacote de\n" +
                        "explorador\n" +
                        "\uF0B7 Armadura de couro, um pacote de aventureiro e um\n" +
                        "foco druídico\n";
                break;
            case "Feiticeiro":
                descricaoEquipamento = "\uF0B7 (a) uma besta leve e 20 virotes ou (b) qualquer arma\n" +
                        "simples\n" +
                        "\uF0B7 (a) uma bolsa de componentes ou (b) um foco arcano\n" +
                        "\uF0B7 (a) um pacote de explorador ou (b) um pacote de\n" +
                        "aventureiro";
                break;
            case "Guerreiro":
                descricaoEquipamento = "\uF0B7 (a) cota de malha ou (b) gibão de peles, arco longo e 20\n" +
                        "flechas\n" +
                        "\uF0B7 (a) uma arma marcial e um escudo ou (b) duas armas\n" +
                        "marciais\n" +
                        "\uF0B7 (a) uma besta leve e 20 virotes ou (b) dois machados de\n" +
                        "arremesso\n" +
                        "\uF0B7 (a) uma pacote de aventureiro ou (b) um pacote de\n" +
                        "explorador\n";
                break;
            case "Ladino":
                descricaoEquipamento = "\uF0B7 (a) uma rapieira ou (b) uma espada longa\n" +
                        "\uF0B7 (a) um arco curto e uma aljava com 20 flechas ou (b)\n" +
                        "uma espada curta\n" +
                        "\uF0B7 (a) um pacote de assaltante ou (b) um pacote de\n" +
                        "aventureiro ou (c) um pacote de explorador\n" +
                        "\uF0B7 Armadura de couro, duas adagas e ferramentas de\n" +
                        "ladrão\n";
                break;
            case "Mago":
                descricaoEquipamento = "\uF0B7 (a) um bordão ou (b) uma adaga\n" +
                        "\uF0B7 (a) uma bolsa de componentes ou (b) um foco arcano\n" +
                        "\uF0B7 (a) um pacote de estudioso ou (b) um pacote de\n" +
                        "explorador\n" +
                        "Um grimório";
                break;
            case "Monge":
                descricaoEquipamento = "\uF0B7 (a) uma espada curta ou (b) qualquer arma simples\n" +
                        "\uF0B7 (a) um pacote de explorador ou (b) um pacote de\n" +
                        "aventureiro\n" +
                        "\uF0B7 10 dardos";
                break;
            case "Paladino":
                descricaoEquipamento = "\uF0B7 (a) uma arma marcial e um escudo ou (b) duas armas\n" +
                        "marciais\n" +
                        "\uF0B7 (a) cinco azagaias ou (b) qualquer arma simples corpoa-corpo\n" +
                        "\uF0B7 (a) um pacote de sacerdote ou (b) um pacote de\n" +
                        "aventureiro\n" +
                        "\uF0B7 Cota de malha e um símbolo sagrado";
                break;
            case "Patrulheiro":
                descricaoEquipamento = "\uF0B7 (a) camisão de malha ou (b) armadura de couro\n" +
                        "\uF0B7 (a) duas espadas curtas ou (b) duas armas simples\n" +
                        "corpo-a-corpo\n" +
                        "\uF0B7 (a) um pacote de explorador ou (b) um pacote de\n" +
                        "aventureiro\n" +
                        "\uF0B7 Um arco longo e uma aljava com 20 flechas";
                break;
        }
        return descricaoEquipamento;
    }

    public void carregaTesteResistenciaPericias(String nomeClasse, Ficha ficha) {
        switch (nomeClasse) {
            case "Barbaro":
                ficha.setForca(true);
                ficha.setCostituicao(true);
                break;
            case "Bardo":
                ficha.setDestreza(true);
                ficha.setCarisma(true);
                break;
            case "Bruxo":
                ficha.setSabedoria(true);
                ficha.setCarisma(true);
                break;
            case "Clerigo":
                ficha.setSabedoria(true);
                ficha.setCarisma(true);
                break;
            case "Druida":
                ficha.setSabedoria(true);
                ficha.setInteligencia(true);
                break;
            case "Feiticeiro":
                ficha.setCostituicao(true);
                ficha.setCarisma(true);
                break;
            case "Guerreiro":
                ficha.setForca(true);
                ficha.setCostituicao(true);
                break;
            case "Ladino":
                ficha.setDestreza(true);
                ficha.setInteligencia(true);
                break;
            case "Mago":
                ficha.setInteligencia(true);
                ficha.setSabedoria(true);
                break;
            case "Monge":
                ficha.setForca(true);
                ficha.setDestreza(true);
                break;
            case "Paladino":
                ficha.setSabedoria(true);
                ficha.setCarisma(true);
                break;
            case "Patrulheiro":
                ficha.setForca(true);
                ficha.setDestreza(true);
                break;
        }
    }
}
