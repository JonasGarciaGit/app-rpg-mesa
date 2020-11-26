package com.example.ppi;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import model.Jogador;

public class TelaInicial extends AppCompatActivity {

    LinearLayout menuconfig;
    Button btnGerenciarFichas;
    public String id;

    Button singOut;
    GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth jogadorAuth = FirebaseAuth.getInstance();
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private MediaPlayer mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial);
        singOut = findViewById(R.id.singOut);

        btnGerenciarFichas = findViewById(R.id.btGerenciarFichas);
        menuconfig = findViewById(R.id.menuConfig);
        menuconfig.setVisibility(View.GONE);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if (acct != null) {
            CriarJogador("google", acct);
            Toast.makeText(this, "NOME::" + acct.getDisplayName() + " EMAIL::" + acct.getEmail(), Toast.LENGTH_SHORT).show();
        }

        if (jogadorAuth.getCurrentUser() != null) {
            CriarJogador("app", null);
        }


        singOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    // ...
                    case R.id.singOut:
                        signOut();
                        if (jogadorAuth.getCurrentUser() != null) {
                            jogadorAuth.signOut();
                        } else {
                            Log.i("JogadorSignOut", "Jogador não está logado!");
                        }
                        break;
                    // ...
                }
            }
        });

        //Passando id para a tela de fichas
        btnGerenciarFichas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMenuPrincipal = new Intent(TelaInicial.this, GerenciamentoFichas.class);
                Bundle parametros = new Bundle();
                parametros.putString("id_usuario", id);
                intentMenuPrincipal.putExtras(parametros);
                startActivity(intentMenuPrincipal);
            }
        });

//        mdp = MediaPlayer.create(TelaInicial.this, R.raw.yggdrasil);
//        mdp.start();
//        mdp.setLooping(true);
    }


    public void sessao(View view) {
        Bundle param = new Bundle();
        param.putString("id_usuario", id);
        Intent intent = new Intent(getApplicationContext(), MenuSessao.class);
        intent.putExtras(param);
        startActivity(intent);
    }


    public void config(View view) {

        menuconfig.setVisibility(View.VISIBLE);
    }


    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(TelaInicial.this, "Deslogando...", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }


    public void CriarJogador(String tipoConta, GoogleSignInAccount acct) {

        DatabaseReference jogadores = FirebaseDatabase.getInstance().getReference().child("jogadores");

        jogadores.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> jogadores = new ArrayList<>();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    jogadores.add(ds.getKey());
                }


                if (tipoConta.equals("google")) {
                    id = acct.getId();
                } else {
                    id = jogadorAuth.getUid();
                }


                if (!jogadores.contains(id)) {
                    if (tipoConta.equals("google")) {
                        Jogador jogador = new Jogador();
                        jogador.setEmail(acct.getEmail());
                        jogador.setUsuario(acct.getDisplayName());
                        jogador.setSenha("***");

                        referencia.child("jogadores").child(acct.getId()).setValue(jogador);
                        referencia.child("jogadores").child(acct.getId()).child("fichas").setValue("lista de ficha");
                    }

                    if (tipoConta.equals("app")) {
                        Jogador jogador = new Jogador();
                        jogador.setEmail(jogadorAuth.getCurrentUser().getEmail());
                        jogador.setUsuario(jogadorAuth.getCurrentUser().getDisplayName());
                        jogador.setSenha("***");

                        referencia.child("jogadores").child(jogadorAuth.getCurrentUser().getUid()).setValue(jogador);
                        referencia.child("jogadores").child(jogadorAuth.getCurrentUser().getUid()).child("fichas").setValue("lista de ficha");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("erroCriarJogador .: ", error.getMessage());
            }
        });

    }

    public void voltar(View view) {
        menuconfig.setVisibility(View.GONE);
    }

}