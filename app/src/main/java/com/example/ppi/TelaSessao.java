package com.example.ppi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import model.Sessao;
import model.Slot;

public class TelaSessao extends AppCompatActivity {

    TextView nome1;
    TextView level1;
    TextView hp1;
    TextView mp1;
    Button btEditar1;

    TextView nome2;
    TextView level2;
    TextView hp2;
    TextView mp2;
    Button btEditar2;

    TextView nome3;
    TextView level3;
    TextView hp3;
    TextView mp3;
    Button btEditar3;

    TextView nome4;
    TextView level4;
    TextView hp4;
    TextView mp4;
    Button btEditar4;

    TextView nome5;
    TextView level5;
    TextView hp5;
    TextView mp5;
    Button btEditar5;

    TextView codSessao;
    String idJogador;
    String idFicha;
    String slotJogador;
    Button btSairSessao;
    TextView resultadoDado;

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
        mp1 = (TextView) findViewById(R.id.mp1);
        btEditar1 = (Button) findViewById(R.id.btEditar1);

        // Fim do jogador 1

        // inicio jogador 2
        nome2 = (TextView) findViewById(R.id.nome2);
        level2 = (TextView) findViewById(R.id.level2);
        hp2 = (TextView) findViewById(R.id.hp2);
        mp2 = (TextView) findViewById(R.id.mp2);
        btEditar2 = (Button) findViewById(R.id.btEditar2);
        // Fim do jogador 2

        // Inicio jogador 3
        nome3 = (TextView) findViewById(R.id.nome3);
        level3 = (TextView) findViewById(R.id.level3);
        hp3 = (TextView) findViewById(R.id.hp3);
        mp3 = (TextView) findViewById(R.id.mp3);
        btEditar3 = (Button) findViewById(R.id.btEditar3);
        // Fim jogador 3

        // Inicio jogador 4
        nome4 = (TextView) findViewById(R.id.nome4);
        level4 = (TextView) findViewById(R.id.level4);
        hp4 = (TextView) findViewById(R.id.hp4);
        mp4 = (TextView) findViewById(R.id.mp4);
        btEditar4 = (Button) findViewById(R.id.btEditar4);
        // Fim jogador 4

        // Inicio jogador 5
        nome5 = (TextView) findViewById(R.id.nome5);
        level5 = (TextView) findViewById(R.id.level5);
        hp5 = (TextView) findViewById(R.id.hp5);
        mp5 = (TextView) findViewById(R.id.mp5);
        btEditar5 = (Button) findViewById(R.id.btEditar5);
        // Fim jogador 5

        resultadoDado = (TextView) findViewById(R.id.resultadoDado);
        btSairSessao = (Button) findViewById(R.id.btSair);
        carregarFichas(getIntent().getStringExtra("cod_sessao"));
        esconderEdicaoJogadores(slotJogador);
    }

    public void carregarFichas(String codSessao) {
        DatabaseReference sessao = FirebaseDatabase.getInstance().getReference("sessoes").child(codSessao);

        try {

            sessao.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<Slot> slots = new ArrayList<>();

                    for (DataSnapshot ds : snapshot.getChildren()) {
                        if(!ds.getKey().equals("valorDado")){
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
                                    nome1.setText("---");
                                    level1.setText("---");
                                    hp1.setText("---");
                                    mp1.setText("---");
                                } else {
                                    nome1.setText(slot.getFicha() == null ? "---" : slot.getFicha().getNomeDoPersonagem());
                                    level1.setText(slot.getFicha() == null ? "---" : slot.getFicha().getNivel().toString());
                                    hp1.setText(slot.getFicha() == null ? "---" : slot.getFicha().getPvAtuais().toString());
                                    mp1.setText("---");
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
                                    nome2.setText("---");
                                    level2.setText("---");
                                    hp2.setText("---");
                                    mp2.setText("---");
                                } else {
                                    nome2.setText(slot.getFicha() == null ? "---" : slot.getFicha().getNomeDoPersonagem());
                                    level2.setText(slot.getFicha() == null ? "---" : slot.getFicha().getNivel().toString());
                                    hp2.setText(slot.getFicha() == null ? "---" : slot.getFicha().getPvAtuais().toString());
                                    mp2.setText("---");
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
                                    nome3.setText("---");
                                    level3.setText("---");
                                    hp3.setText("---");
                                    mp3.setText("---");
                                } else {
                                    nome3.setText(slot.getFicha() == null ? "---" : slot.getFicha().getNomeDoPersonagem());
                                    level3.setText(slot.getFicha() == null ? "---" : slot.getFicha().getNivel().toString());
                                    hp3.setText(slot.getFicha() == null ? "---" : slot.getFicha().getPvAtuais().toString());
                                    mp3.setText("---");
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
                                    nome4.setText("---");
                                    level4.setText("---");
                                    hp4.setText("---");
                                    mp4.setText("---");
                                } else {
                                    nome4.setText(slot.getFicha() == null ? "---" : slot.getFicha().getNomeDoPersonagem());
                                    level4.setText(slot.getFicha() == null ? "---" : slot.getFicha().getNivel().toString());
                                    hp4.setText(slot.getFicha() == null ? "---" : slot.getFicha().getPvAtuais().toString());
                                    mp4.setText("---");
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
                                    nome5.setText("---");
                                    level5.setText("---");
                                    hp5.setText("---");
                                    mp5.setText("---");
                                } else {
                                    nome5.setText(slot.getFicha() == null ? "---" : slot.getFicha().getNomeDoPersonagem());
                                    level5.setText(slot.getFicha() == null ? "---" : slot.getFicha().getNivel().toString());
                                    hp5.setText(slot.getFicha() == null ? "---" : slot.getFicha().getPvAtuais().toString());
                                    mp5.setText("---");
                                    btEditar5.setOnClickListener((v) -> {
                                        editarFicha(idJogador, idFicha);
                                    });
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
        Bundle parametros = new Bundle();
        parametros.putString("id_jogador", idJogador);
        parametros.putString("id_ficha", idFicha);

        Intent intentParaEditar = new Intent(TelaSessao.this, TelaEditar.class);
        intentParaEditar.putExtras(parametros);
        startActivity(intentParaEditar);
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
                        slot.child("ficha").removeValue();
                        slot.child("status").setValue("vazio");

                        Log.i("sairSessao", "Ajustei os valores");

                        Intent intentParaMenuPrincipal = new Intent(TelaSessao.this, TelaInicial.class);
                        destruirSessao(codSessao);
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

}