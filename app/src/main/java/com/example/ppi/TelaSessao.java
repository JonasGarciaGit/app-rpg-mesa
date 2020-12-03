package com.example.ppi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Ficha;
import model.Sessao;
import model.Slot;

public class TelaSessao extends AppCompatActivity{

    TextView nome1;
    TextView level1;
    TextView hp1;
    Button btEditar1;
    ImageView img1;

    TextView nome2;
    TextView level2;
    TextView hp2;
    Button btEditar2;
    ImageView img2;

    TextView nome3;
    TextView level3;
    TextView hp3;
    Button btEditar3;
    ImageView img3;

    TextView nome4;
    TextView level4;
    TextView hp4;
    Button btEditar4;
    ImageView img4;

    TextView nome5;
    TextView level5;
    TextView hp5;
    Button btEditar5;
    ImageView img5;

    TextView codSessao;
    String idJogador;
    String idFicha;
    String slotJogador;
    Button btSairSessao;
    TextView resultadoDado;
    private Toolbar toolbar;
    private MediaPlayer mdp;
    private ConstraintLayout telaSessao;




    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_sessao);

        idJogador = getIntent().getStringExtra("id_jogador");
        idFicha = getIntent().getStringExtra("id_ficha");
        slotJogador = getIntent().getStringExtra("slot_jogador");

        codSessao = (TextView) findViewById(R.id.codSessao);
        codSessao.setText("Código da sessão: " + getIntent().getStringExtra("cod_sessao"));

        // Inicio jogador 1
        nome1 = (TextView) findViewById(R.id.nome1);
        level1 = (TextView) findViewById(R.id.level1);
        hp1 = (TextView) findViewById(R.id.hp1);
        btEditar1 = (Button) findViewById(R.id.btEditar1);
        img1 = (ImageView) findViewById(R.id.imagemJogador1);

        // Fim do jogador 1

        // inicio jogador 2
        nome2 = (TextView) findViewById(R.id.nome2);
        level2 = (TextView) findViewById(R.id.level2);
        hp2 = (TextView) findViewById(R.id.hp2);
        btEditar2 = (Button) findViewById(R.id.btEditar2);
        img2 = (ImageView) findViewById(R.id.imagemJogador2);
        // Fim do jogador 2

        // Inicio jogador 3
        nome3 = (TextView) findViewById(R.id.nome3);
        level3 = (TextView) findViewById(R.id.level3);
        hp3 = (TextView) findViewById(R.id.hp3);
        btEditar3 = (Button) findViewById(R.id.btEditar3);
        img3 = (ImageView) findViewById(R.id.imagemJogador3);
        // Fim jogador 3

        // Inicio jogador 4
        nome4 = (TextView) findViewById(R.id.nome4);
        level4 = (TextView) findViewById(R.id.level4);
        hp4 = (TextView) findViewById(R.id.hp4);
        btEditar4 = (Button) findViewById(R.id.btEditar4);
        img4 = (ImageView) findViewById(R.id.imagemJogador4);
        // Fim jogador 4

        // Inicio jogador 5
        nome5 = (TextView) findViewById(R.id.nome5);
        level5 = (TextView) findViewById(R.id.level5);
        hp5 = (TextView) findViewById(R.id.hp5);
        btEditar5 = (Button) findViewById(R.id.btEditar5);
        img5 = (ImageView) findViewById(R.id.imagemJogador5);
        // Fim jogador 5

        resultadoDado = (TextView) findViewById(R.id.resultadoDado);
        btSairSessao = (Button) findViewById(R.id.btSair);
        carregarFichas(getIntent().getStringExtra("cod_sessao"));
        esconderEdicaoJogadores(slotJogador);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        telaSessao = findViewById(R.id.telaSessao);
        createDrawer();
        alterarFundoMusica();
    }

    public void carregarFichas(String codSessao) {
        DatabaseReference sessao = FirebaseDatabase.getInstance().getReference("sessoes").child(codSessao);

        try {

            sessao.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<Slot> slots = new ArrayList<>();

                    for (DataSnapshot ds : snapshot.getChildren()) {
                        if(!ds.getKey().equals("valorDado") && !ds.getKey().equals("musicaAtual")){
                            Slot slot = (Slot) ds.getValue(Slot.class);
                            slots.add(slot);
                        }else if(ds.getKey().equals("valorDado")){
                            resultadoDado.setText("Resultado do dado: " + ds.getValue().toString());
                        }
                    }

                    if (slots == null || slots.isEmpty()) {
                        Log.e("erroCarregarFichaSessao", "erro ao carregar fichas");
                    } else {
                        int numSlot = 1;

                        for (Slot slot : slots) {
                            if (numSlot == 1) {
                                if (slot.getStatus().equals("vazio")) {
                                    img1.setImageResource(carregaAvatar("Generico"));
                                    nome1.setText("---");
                                    level1.setText("---");
                                    hp1.setText("---");
                                } else {
                                    nome1.setText(slot.getFicha() == null ? "---" : slot.getFicha().getNomeDoPersonagem());
                                    level1.setText(slot.getFicha() == null ? "---" : "LEVEL " + slot.getFicha().getNivel().toString());
                                    hp1.setText(slot.getFicha() == null ? "---" : slot.getFicha().getPvAtuais().toString());
                                    img1.setImageResource(carregaAvatar(slot.getFicha() == null ? "Generico" : slot.getFicha().getClasse()));
                                    btEditar1.setOnClickListener((v) -> {
                                        editarFicha(idJogador, idFicha);
                                    });
                                    btSairSessao.setOnClickListener((v) -> {
                                        sairSessao(codSessao, slotJogador);
                                    });
                                }
                            }

                            if (numSlot == 2) {
                                if (slot.getStatus().equals("vazio")) {
                                    img2.setImageResource(carregaAvatar("Generico"));
                                    nome2.setText("---");
                                    level2.setText("---");
                                    hp2.setText("---");
                                } else {
                                    nome2.setText(slot.getFicha() == null ? "---" : slot.getFicha().getNomeDoPersonagem());
                                    level2.setText(slot.getFicha() == null ? "---" : "LEVEL " + slot.getFicha().getNivel().toString());
                                    hp2.setText(slot.getFicha() == null ? "---" : slot.getFicha().getPvAtuais().toString());
                                    img2.setImageResource(carregaAvatar(slot.getFicha() == null ? "Generico" : slot.getFicha().getClasse()));
                                    btEditar2.setOnClickListener((v) -> {
                                        editarFicha(idJogador, idFicha);
                                    });
                                    btSairSessao.setOnClickListener((v) -> {
                                        sairSessao(codSessao, slotJogador);
                                    });
                                }
                            }

                            if (numSlot == 3) {
                                if (slot.getStatus().equals("vazio")) {
                                    img3.setImageResource(carregaAvatar("Generico"));
                                    nome3.setText("---");
                                    level3.setText("---");
                                    hp3.setText("---");
                                } else {
                                    nome3.setText(slot.getFicha() == null ? "---" : slot.getFicha().getNomeDoPersonagem());
                                    level3.setText(slot.getFicha() == null ? "---" : "LEVEL " + slot.getFicha().getNivel().toString());
                                    hp3.setText(slot.getFicha() == null ? "---" : slot.getFicha().getPvAtuais().toString());
                                    img3.setImageResource(carregaAvatar(slot.getFicha() == null ? "Generico" : slot.getFicha().getClasse()));
                                    btEditar3.setOnClickListener((v) -> {
                                        editarFicha(idJogador, idFicha);
                                    });
                                    btSairSessao.setOnClickListener((v) -> {
                                        sairSessao(codSessao, slotJogador);
                                    });
                                }
                            }

                            if (numSlot == 4) {
                                if (slot.getStatus().equals("vazio")) {
                                    img4.setImageResource(carregaAvatar("Generico"));
                                    nome4.setText("---");
                                    level4.setText("---");
                                    hp4.setText("---");
                                } else {
                                    nome4.setText(slot.getFicha() == null ? "---" : slot.getFicha().getNomeDoPersonagem());
                                    level4.setText(slot.getFicha() == null ? "---" : "LEVEL " + slot.getFicha().getNivel().toString());
                                    hp4.setText(slot.getFicha() == null ? "---" : slot.getFicha().getPvAtuais().toString());
                                    img4.setImageResource(carregaAvatar(slot.getFicha() == null ? "Generico" : slot.getFicha().getClasse()));
                                        btEditar4.setOnClickListener((v) -> {
                                        editarFicha(idJogador, idFicha);
                                    });
                                    btSairSessao.setOnClickListener((v) -> {
                                        sairSessao(codSessao, slotJogador);
                                    });
                                }
                            }

                            if (numSlot == 5) {
                                if (slot.getStatus().equals("vazio")) {
                                    img5.setImageResource(carregaAvatar("Generico"));
                                    nome5.setText("---");
                                    level5.setText("---");
                                    hp5.setText("---");
                                } else {
                                    nome5.setText(slot.getFicha() == null ? "---" : slot.getFicha().getNomeDoPersonagem());
                                    level5.setText(slot.getFicha() == null ? "---" : "LEVEL " + slot.getFicha().getNivel().toString());
                                    hp5.setText(slot.getFicha() == null ? "---" : slot.getFicha().getPvAtuais().toString());
                                    img5.setImageResource(carregaAvatar(slot.getFicha() == null ? "Generico" : slot.getFicha().getClasse()));
                                    btEditar5.setOnClickListener((v) -> {
                                        editarFicha(idJogador, idFicha);
                                    });
                                    btSairSessao.setOnClickListener((v) -> {
                                        sairSessao(codSessao, slotJogador);
                                    });
                                }
                            }

                            if (numSlot == 6) {
                                if (slot.getStatus().equals("ocupado")) {
                                    btSairSessao.setOnClickListener((v) -> {
                                        sairSessao(codSessao, slotJogador);
                                    });
                                }
                            }


                            numSlot++;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("errocarregarFichas", error.getMessage());
                }
            });

        } catch (Exception e) {
            Log.e("errocarregarFichas", e.getMessage());
        }

    }

    public void editarFicha(String idJogador, String idFicha) {
        DatabaseReference referencia = FirebaseDatabase.getInstance().getReference().child("jogadores").child(idJogador).child("fichas");
        referencia.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Ficha> listaFichas =  new ArrayList<>();
                for(DataSnapshot ds : snapshot.getChildren()){
                    Ficha ficha = ds.getValue(Ficha.class);
                    listaFichas.add(ficha);

                    for(Ficha f : listaFichas){
                        if(f.getId().equals(idFicha)){
                            Bundle parametros = new Bundle();
                            parametros.putSerializable("ficha", f);
                            parametros.putString("id_jogador", idJogador);
                            parametros.putString("id_ficha", idFicha);
                            parametros.putString("cod_sessao", getIntent().getStringExtra("cod_sessao"));
                            parametros.putString("slot_jogador", getIntent().getStringExtra("slot_jogador"));
                            Intent intentParaEditar = new Intent(TelaSessao.this, FichaPersonagem.class);
                            intentParaEditar.putExtras(parametros);
                            startActivity(intentParaEditar);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
    public void sairSessao(final String codSessao, final String slotJogador) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja mesmo sair da sessão?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("sairSessao", "Iniciei a função");
                        DatabaseReference sessao = FirebaseDatabase.getInstance().getReference().child("sessoes").child(codSessao);
                        DatabaseReference slot = sessao.child(slotJogador);

                        Log.i("sairSessao", "Achei as referencias");

                        if(slot.child("ficha")!= null){
                            slot.child("ficha").removeValue();
                        }

                        slot.child("status").setValue("vazio");

                        Log.i("sairSessao", "Ajustei os valores");

                        Intent intentParaMenuPrincipal = new Intent(TelaSessao.this, TelaInicial.class);
                        destruirSessao(codSessao);
                        intentParaMenuPrincipal.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();
                        startActivity(intentParaMenuPrincipal);
                    }
                }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Não faz nada...
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void esconderEdicaoJogadores(String slotJogador) {
        if (slotJogador.equals("slot1")) {
            btEditar2.setVisibility(View.INVISIBLE);
            btEditar3.setVisibility(View.INVISIBLE);
            btEditar4.setVisibility(View.INVISIBLE);
            btEditar5.setVisibility(View.INVISIBLE);
        } else if (slotJogador.equals("slot2")) {
            btEditar1.setVisibility(View.INVISIBLE);
            btEditar3.setVisibility(View.INVISIBLE);
            btEditar4.setVisibility(View.INVISIBLE);
            btEditar5.setVisibility(View.INVISIBLE);
        } else if (slotJogador.equals("slot3")) {
            btEditar1.setVisibility(View.INVISIBLE);
            btEditar2.setVisibility(View.INVISIBLE);
            btEditar4.setVisibility(View.INVISIBLE);
            btEditar5.setVisibility(View.INVISIBLE);
        } else if (slotJogador.equals("slot4")) {
            btEditar1.setVisibility(View.INVISIBLE);
            btEditar2.setVisibility(View.INVISIBLE);
            btEditar3.setVisibility(View.INVISIBLE);
            btEditar5.setVisibility(View.INVISIBLE);
        } else if (slotJogador.equals("slot5")) {
            btEditar1.setVisibility(View.INVISIBLE);
            btEditar2.setVisibility(View.INVISIBLE);
            btEditar3.setVisibility(View.INVISIBLE);
            btEditar4.setVisibility(View.INVISIBLE);
        } else if(slotJogador.equals("slot6")){
            btEditar1.setVisibility(View.INVISIBLE);
            btEditar2.setVisibility(View.INVISIBLE);
            btEditar3.setVisibility(View.INVISIBLE);
            btEditar4.setVisibility(View.INVISIBLE);
            btEditar5.setVisibility(View.INVISIBLE);
        }
    }

    public void destruirSessao(String codSessao) {
        DatabaseReference sessoes = FirebaseDatabase.getInstance().getReference().child("sessoes");

        sessoes.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Sessao sessao = null;


                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.getKey().equals(codSessao)) {
                        sessao = (Sessao) ds.getValue(Sessao.class);
                    }
                }
                if (sessao != null) {
                    if (sessao.getSlot1().getStatus().equals("vazio") &&
                            sessao.getSlot2().getStatus().equals("vazio") &&
                            sessao.getSlot3().getStatus().equals("vazio") &&
                            sessao.getSlot4().getStatus().equals("vazio") &&
                            sessao.getSlot5().getStatus().equals("vazio") &&
                            sessao.getSlot6().getStatus().equals("vazio")) {

                        sessoes.child(codSessao).removeValue();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("erroDestruirSessao", error.getMessage());
            }
        });
    }

    public void roletarDado(View view){
        Intent intentSessaoParaDados =new Intent(TelaSessao.this, RoletagemDados.class);
        Bundle param = new Bundle();
        param.putString("cod_sessao" , getIntent().getStringExtra("cod_sessao"));
        intentSessaoParaDados.putExtras(param);
        startActivity(intentSessaoParaDados);
    }

    @Override
    public void onBackPressed() {

    }
    public int carregaAvatar(String nomeClasse) {
        Random randomAvatar = new Random();
        int imgRandom = randomAvatar.nextInt((((2 - 1) + 1)) + 1);
        switch (nomeClasse) {

            case "Barbaro":
                return R.drawable.barbaro;


            case "Bardo":
                return R.drawable.barda;


            case "Bruxo":
                return R.drawable.bruxo;


            case "Clerigo":
                return R.drawable.cleriga;


            case "Druida":
                return R.drawable.druida;


            case "Feiticeiro":
                    return R.drawable.feiticeiro;



            case "Guerreiro":
                return R.drawable.guerreiro;


            case "Ladino":
                return R.drawable.ladino;


            case "Mago":
                return R.drawable.maga;


            case "Monge":
                return R.drawable.monge;


            case "Paladino":
                return R.drawable.paladino;


            case "Patrulheiro":
                return R.drawable.patrulheira;

            case "Generico":
                return R.drawable.patrulheiro;

        }
        return 0;

    }

    private void createDrawer(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("sessoes").child(getIntent().getStringExtra("cod_sessao")).child("musicaAtual");
        //Itens do Drawer
        @SuppressLint("ResourceAsColor") PrimaryDrawerItem chuva1 = new PrimaryDrawerItem().withIdentifier(1).withName("Chuva 1").withIcon(R.drawable.icon_musica);
        PrimaryDrawerItem floresta1 = new PrimaryDrawerItem().withIdentifier(2).withName("Floresta 1").withIcon(R.drawable.icon_musica);
        PrimaryDrawerItem taverna1 = new PrimaryDrawerItem().withIdentifier(2).withName("Taverna 1").withIcon(R.drawable.icon_musica);
        PrimaryDrawerItem taverna2 = new PrimaryDrawerItem().withIdentifier(2).withName("Taverna 2").withIcon(R.drawable.icon_musica);
        PrimaryDrawerItem batalha1 = new PrimaryDrawerItem().withIdentifier(2).withName("Batalha 1").withIcon(R.drawable.icon_musica);
        PrimaryDrawerItem defaultMusica = new PrimaryDrawerItem().withIdentifier(2).withName("Desativar som").withIcon(R.drawable.icon_musica);

        //Definição do Drawer
        @SuppressLint("ResourceAsColor") Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .withSliderBackgroundColorRes(R.color.slider_musica)
                .withToolbar(toolbar)
                .addDrawerItems(
                        new SectionDrawerItem().withName("Padrão").withTextColor(R.color.white).withTextColorRes(R.color.white),
                        defaultMusica.withTextColor(R.color.white).withTextColorRes(R.color.white),
                        new SectionDrawerItem().withName("Sons de chuva").withTextColor(R.color.white).withTextColorRes(R.color.white),
                        chuva1.withTextColor(R.color.white).withTextColorRes(R.color.white),
                        new SectionDrawerItem().withName("Sons de ar livre").withTextColor(R.color.white).withTextColorRes(R.color.white),
                        floresta1.withTextColor(R.color.white).withTextColorRes(R.color.white),
                        new SectionDrawerItem().withName("Sons de taverna").withTextColor(R.color.white).withTextColorRes(R.color.white),
                        taverna1.withTextColor(R.color.white).withTextColorRes(R.color.white),
                        new DividerDrawerItem(),
                        taverna2.withTextColor(R.color.white).withTextColorRes(R.color.white),
                        new SectionDrawerItem().withName("Sons de batalha").withTextColor(R.color.white).withTextColorRes(R.color.white),
                        batalha1.withTextColor(R.color.white).withTextColorRes(R.color.white)

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        String musicaSelecionada = ((Nameable) drawerItem).getName().getText(TelaSessao.this);
                        switch (musicaSelecionada){
                            case "Desativar som":
                                if(mdp != null){
                                    mdp.stop();
                                    telaSessao.setBackground(ContextCompat.getDrawable(TelaSessao.this,R.drawable.fundo_sessao2));
                                    reference.setValue("Desativar som");
                                }
                                break;
                            case "Chuva 1":
                                trocarMusica(R.raw.chuva1,R.drawable.chuva_fundo);
                                reference.setValue("Chuva 1");
                                break;
                            case "Floresta 1":
                                trocarMusica(R.raw.floresta1,R.drawable.floresta_fundo);
                                reference.setValue("Floresta 1");
                                break;
                            case "Taverna 1":
                                trocarMusica(R.raw.taverna1,R.drawable.taverna_fundo);
                                reference.setValue("Taverna 1");
                                break;
                            case "Batalha 1":
                                trocarMusica(R.raw.batalha1,R.drawable.batalha_fundo);
                                reference.setValue("Batalha 1");
                                break;
                            default:
                                if(mdp != null){
                                    mdp.stop();
                                    telaSessao.setBackground(ContextCompat.getDrawable(TelaSessao.this,R.drawable.fundo_sessao2));
                                    reference.setValue("");
                                }
                                break;
                        }
                        return false;
                    }
                })
                .withSelectedItemByPosition(0)
                .build();
        toolbar.setNavigationIcon(R.drawable.ic_baseline_queue_music_24);
    }
    public void trocarMusica(int uri,int uriImagem){
        if(mdp != null){
            mdp.stop();
        }
        mdp = MediaPlayer.create(TelaSessao.this,uri);
        mdp.setVolume(1f,1f);
        mdp.setLooping(true);
        mdp.start();
        telaSessao.setBackground(ContextCompat.getDrawable(TelaSessao.this,uriImagem));
    }
    public void alterarFundoMusica(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("sessoes").child(getIntent().getStringExtra("cod_sessao")).child("musicaAtual");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue() != null){
                    switch (snapshot.getValue().toString()){
                        case "Desativar som":
                            if(mdp != null){
                                mdp.stop();
                                telaSessao.setBackground(ContextCompat.getDrawable(TelaSessao.this,R.drawable.fundo_sessao2));
                            }
                            break;
                        case "Chuva 1":
                            trocarMusica(R.raw.chuva1,R.drawable.chuva_fundo);
                            break;
                        case "Floresta 1":
                            trocarMusica(R.raw.floresta1,R.drawable.floresta_fundo);
                            break;
                        case "Taverna 1":
                            trocarMusica(R.raw.taverna1,R.drawable.taverna_fundo);
                            break;
                        case "Batalha 1":
                            trocarMusica(R.raw.batalha1,R.drawable.batalha_fundo);
                            break;
                        default:
                            if(mdp != null){
                                mdp.stop();
                                telaSessao.setBackground(ContextCompat.getDrawable(TelaSessao.this,R.drawable.fundo_sessao2));
                            }
                            break;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

}