package com.example.ppi;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import model.Sessoes;
import model.Slot;

public class MenuSessao extends AppCompatActivity {


    RadioButton ficha1;
    RadioButton ficha2;
    RadioButton ficha3;
    RadioButton mestre;
    LinearLayout codPage;
    String idUsuario;
    String idFicha = null;
    private EditText inputCodSessao;
    private boolean jaInserido;
    private MediaPlayer mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_sessao);
        idUsuario = getIntent().getStringExtra("id_usuario");

        ficha1 = findViewById(R.id.radioFicha1);
        ficha2 = findViewById(R.id.radioFicha2);
        ficha3 = findViewById(R.id.radioFicha3);
        mestre = findViewById(R.id.radioMestre);


        inputCodSessao = findViewById(R.id.inputCodigoSessao);
        codPage = findViewById(R.id.codPage);
        codPage.setVisibility(View.GONE);
    }

    public void voltarInicio(View view){
        Intent intent = new Intent(getApplicationContext(), TelaInicial.class);
        finish();
        startActivity(intent);
    }

    public void fecharCodigoPage(View view){codPage.setVisibility(View.GONE); inputCodSessao.setText("");}

    public void codigoPage(View view){
        codPage.setVisibility(View.VISIBLE);
    }

    public void criarSessao(View view){
            selecionarFicha();

            if(idFicha == null){
                Toast.makeText(getApplicationContext(), "Por favor, selecione uma opção acima", Toast.LENGTH_LONG).show();
            }else {
                DatabaseReference ficha = FirebaseDatabase.getInstance().getReference("jogadores")
                        .child(idUsuario)
                        .child("fichas")
                        .child(idFicha);


                ficha.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (idFicha.equals("mestre") || snapshot.hasChildren()) {
                            DatabaseReference sessoes = FirebaseDatabase.getInstance().getReference().child("sessoes");

                            //Gerando código da sessão com 6 digitos
                            Random random = new Random();
                            Integer codSessao = random.nextInt((((999999 - 100000) + 1)) + 100000);

                            //Criando sessao e slots para os jogadores
                            DatabaseReference sessao = sessoes.child(codSessao.toString());

                            sessao.child("musicaAtual").setValue("");
                            sessao.child("valorDado").setValue("0");

                            DatabaseReference slot1 = sessao.child("slot1");
                            slot1.child("status").setValue("vazio");

                            DatabaseReference slot2 = sessao.child("slot2");
                            slot2.child("status").setValue("vazio");

                            DatabaseReference slot3 = sessao.child("slot3");
                            slot3.child("status").setValue("vazio");

                            DatabaseReference slot4 = sessao.child("slot4");
                            slot4.child("status").setValue("vazio");

                            DatabaseReference slot5 = sessao.child("slot5");
                            slot5.child("status").setValue("vazio");

                            //Este slot sempre pertencera ao mestre
                            DatabaseReference slot6 = sessao.child("slot6");
                            slot6.child("status").setValue("vazio");

                            //carregando ficha no slot
                            carregarFichaNoSlot(codSessao.toString(), idFicha.equals("mestre") ? "slot6" : "slot1");
                        } else {
                            Toast.makeText(getApplicationContext(), "Não existe uma ficha criada nesse slot", Toast.LENGTH_LONG).show();
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

    }

    public void entrarSessao(View view){
        selecionarFicha();
        String codSessao = inputCodSessao.getText().toString();
        DatabaseReference sessoes = FirebaseDatabase.getInstance().getReference().child("sessoes");

        if(idFicha == null){
            Toast.makeText(getApplicationContext(), "Por favor, selecione uma função entre jogador ou mestre", Toast.LENGTH_LONG).show();
        }else {


            sessoes.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Sessao sessao = null;
                    String slot = "";
                    jaInserido = false;


                    for (DataSnapshot ds : snapshot.getChildren()) {
                        if (ds.getKey().equals(codSessao)) {
                            sessao = (Sessao) ds.getValue(Sessao.class);
                        }
                    }

                    if (sessao != null) {

                        if (sessao.getSlot1().getStatus().equals("vazio") && jaInserido == false && !idFicha.equals("mestre")) {
                            slot = "slot1";
                            jaInserido = true;
                        } else if (sessao.getSlot2().getStatus().equals("vazio") && jaInserido == false && !idFicha.equals("mestre")) {
                            slot = "slot2";
                            jaInserido = true;
                        } else if (sessao.getSlot3().getStatus().equals("vazio") && jaInserido == false && !idFicha.equals("mestre")) {
                            slot = "slot3";
                            jaInserido = true;
                        } else if (sessao.getSlot4().getStatus().equals("vazio") && jaInserido == false && !idFicha.equals("mestre")) {
                            slot = "slot4";
                            jaInserido = true;
                        } else if (sessao.getSlot5().getStatus().equals("vazio") && jaInserido == false && !idFicha.equals("mestre")) {
                            slot = "slot5";
                            jaInserido = true;
                        } else if (sessao.getSlot6().getStatus().equals("vazio") && jaInserido == false && idFicha.equals("mestre")) {
                            slot = "slot6";
                            jaInserido = true;
                        } else{
                            Toast.makeText(getApplicationContext(), "Não há espaço nessa sessão", Toast.LENGTH_LONG).show();
                        }

                        if (jaInserido == true) {
                            if(slot.equals("slot6") && sessao.getSlot6().getStatus().equals("ocupado")){
                                Toast.makeText(getApplicationContext(), "Já existe um mestre nessa sessão", Toast.LENGTH_LONG).show();
                            }else{
                                carregarFichaNoSlot(codSessao, slot);
                            }
                        }

                    } else {
                        //printar algo sessao n econtrada
                        Toast.makeText(getApplicationContext(), "Sessão não encontrada", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("erroEntrarSessao", error.getMessage());
                }
            });
        }

    }

    public void carregarFichaNoSlot(final String codSessao, final String slot){
        DatabaseReference slotN =  FirebaseDatabase.getInstance().getReference().child("sessoes").child(codSessao).child(slot);
        DatabaseReference slot6 =  FirebaseDatabase.getInstance().getReference().child("sessoes").child(codSessao).child("slot6");

        if(!idFicha.equals("mestre")) {
            DatabaseReference ficha = FirebaseDatabase.getInstance().getReference("jogadores")
                    .child(idUsuario)
                    .child("fichas")
                    .child(idFicha);

            try {
                ficha.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(snapshot.hasChildren()){
                            Ficha fichaModel = snapshot.getValue(Ficha.class);

                            Bundle informacoesSessao = new Bundle();
                            informacoesSessao.putString("id_jogador", idUsuario);
                            informacoesSessao.putString("id_ficha", idFicha == null ? "" : idFicha);
                            informacoesSessao.putString("cod_sessao", codSessao);
                            informacoesSessao.putString("slot_jogador", slot);
                            Intent intentIrParaSessao = new Intent(MenuSessao.this, TelaSessao.class);
                            intentIrParaSessao.putExtras(informacoesSessao);

                            slotN.child("status").setValue("ocupado");
                            slotN.child("ficha").setValue(fichaModel);

                            intentIrParaSessao.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            finish();
                            startActivity(intentIrParaSessao);
                        }else{
                            Toast.makeText(getApplicationContext(),"Não existe uma ficha criada nesse slot" , Toast.LENGTH_LONG).show();
                        }

                       }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("erroCarregarFicha", error.getMessage());
                    }
                });

            } catch (Exception e) {
                Log.e("erroCarregarFicha", e.getMessage());
            }
        }else {
            Bundle informacoesSessao = new Bundle();
            informacoesSessao.putString("id_jogador", idUsuario);
            informacoesSessao.putString("cod_sessao", codSessao);
            informacoesSessao.putString("tipo_usuario", "mestre");
            informacoesSessao.putString("slot_jogador", slot);
            Intent intentIrParaSessao = new Intent(MenuSessao.this, TelaSessao.class);
            intentIrParaSessao.putExtras(informacoesSessao);

            slot6.child("status").setValue("ocupado");

            intentIrParaSessao.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(intentIrParaSessao);
        }

    }

    public void selecionarFicha(){
        if(ficha1.isChecked()){
            this.idFicha = "001";
        }else if(ficha2.isChecked()){
            this.idFicha = "002";
        }else if(ficha3.isChecked()){
            this.idFicha = "003";
        }else if(mestre.isChecked()){
            idFicha = "mestre";
        }

    }

    @Override
    public void onBackPressed() {

    }
}