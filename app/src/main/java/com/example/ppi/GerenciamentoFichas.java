package com.example.ppi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import model.Ficha;

public class GerenciamentoFichas extends AppCompatActivity {

    private List<Ficha> fichas;
    private Bundle parametros;

    //componentes da tela
    private TextView nomePersonagem1;
    private TextView nomePersonagem2;
    private TextView nomePersonagem3;

    private TextView classePersonagem1;
    private TextView classePersonagem2;
    private TextView classePersonagem3;

    private TextView racaPersonagem1;
    private TextView racaPersonagem2;
    private TextView racaPersonagem3;

    private TextView levelPersonagem1;
    private TextView levelPersonagem2;
    private TextView levelPersonagem3;

    private Button btDeletar1;
    private Button btDeletar2;
    private Button btDeletar3;

    private Button btEditar1;
    private Button btEditar2;
    private Button btEditar3;

    private Button btCriarFicha1;
    private Button btCriarFicha2;
    private Button btCriarFicha3;

    private String idPersonagem1;
    private String idPersonagem2;
    private String idPersonagem3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gerenciamento_fichas);
        fichas = new ArrayList<>();

        //componentes da tela
        nomePersonagem1 = findViewById(R.id.nomePersonagem1);
        nomePersonagem2 = findViewById(R.id.nomePersonagem2);
        nomePersonagem3 = findViewById(R.id.nomePersonagem3);

        classePersonagem1 = findViewById(R.id.classePersonagem1);
        classePersonagem2 = findViewById(R.id.classePersonagem2);
        classePersonagem3 = findViewById(R.id.classePersonagem3);

        racaPersonagem1 = findViewById(R.id.racaPersonagem1);
        racaPersonagem2 = findViewById(R.id.racaPersonagem2);
        racaPersonagem3 = findViewById(R.id.racaPersonagem3);

        levelPersonagem1 = findViewById(R.id.levelPersonagem1);
        levelPersonagem2 = findViewById(R.id.levelPersonagem2);
        levelPersonagem3 = findViewById(R.id.levelPersonagem3);

        btDeletar1 = findViewById(R.id.btnDeletarPersonagem1);
        btDeletar2 = findViewById(R.id.btnDeletarPersonagem2);
        btDeletar3 = findViewById(R.id.btnDeletarPersonagem3);

        btEditar1 = findViewById(R.id.btnEditarPersonagem1);
        btEditar2 = findViewById(R.id.btnEditarPersonagem2);
        btEditar3 = findViewById(R.id.btnEditarPersonagem3);


        btCriarFicha1 = findViewById(R.id.btIncluirFicha1);
        btCriarFicha2 = findViewById(R.id.btIncluirFicha2);
        btCriarFicha3 = findViewById(R.id.btIncluirFicha3);


        btEditar1.setVisibility(View.INVISIBLE);
        btEditar2.setVisibility(View.INVISIBLE);
        btEditar3.setVisibility(View.INVISIBLE);

        btDeletar1.setVisibility(View.INVISIBLE);
        btDeletar2.setVisibility(View.INVISIBLE);
        btDeletar3.setVisibility(View.INVISIBLE);

        //Recebendo e printando o id
        Intent gerenciamentoFichas = getIntent();
        parametros = gerenciamentoFichas.getExtras();
        final String idJogador = parametros.getString("id_usuario");
        this.carregarFichas(idJogador);

        btDeletar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletar(idPersonagem1, idJogador);
            }
        });

        btDeletar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletar(idPersonagem2, idJogador);
            }
        });

        btDeletar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletar(idPersonagem3, idJogador);
            }
        });

        btCriarFicha1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarFicha(idJogador);
            }
        });


        btCriarFicha2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarFicha(idJogador);
            }
        });


        btCriarFicha3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarFicha( idJogador);
            }
        });


    }

    public void deletar(final String idFicha, final String idJogador){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja mesmo deletar essa ficha?")
                .setPositiveButton("Sim" ,new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference jogadores = FirebaseDatabase.getInstance().getReference("jogadores");
                        DatabaseReference ficha = jogadores.child(idJogador).child("fichas").child(idFicha.toString());

                        Log.i("removeFicha", ficha.toString());

                        ficha.removeValue();
                        finish();
                        startActivity(getIntent());
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

    public void carregarFichas(String id){

        DatabaseReference jogadores = FirebaseDatabase.getInstance().getReference("jogadores");
        DatabaseReference fichas = jogadores.child(id).child("fichas");

        try{
            fichas.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<Ficha> listaFichas =  new ArrayList<>();

                    for(DataSnapshot f: snapshot.getChildren()){
                        Ficha ficha = f.getValue(Ficha.class);
                        listaFichas.add(ficha);
                    }


                    if(listaFichas == null || listaFichas.isEmpty()){
                        Log.e("erroBuscarFichas", "Falha ao buscar fichas");
                    }else{
                        int verificarFicha = 1;
                        for(Ficha f: listaFichas){
                            if(verificarFicha == 1){
                                nomePersonagem1.setText(f.getNomeDoPersonagem().isEmpty() || f.getNomeDoPersonagem() == null ? "---" : f.getNomeDoPersonagem());
                                classePersonagem1.setText("Classe: " + (f.getClasse().isEmpty() || f.getClasse() == null ? "---" : f.getClasse()));
                                racaPersonagem1.setText("Raça: " + (f.getRaca().isEmpty() || f.getRaca() == null ? "---" : f.getRaca()));
                                levelPersonagem1.setText("Level: " + (f.getNivel().toString().isEmpty() || f.getNivel() == null ? "---" : f.getNivel()));
                                idPersonagem1 = f.getId().isEmpty() ? null : f.getId();
                                btCriarFicha1.setVisibility(View.INVISIBLE);
                                btEditar1.setVisibility(View.VISIBLE);
                                btDeletar1.setVisibility(View.VISIBLE);
                                btEditar1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("id_jogador", parametros.getString("id_usuario"));
                                        bundle.putSerializable("ficha", f);
                                        Intent intentGerenciarFicha = new Intent(GerenciamentoFichas.this , FichaPersonagem.class);
                                        intentGerenciarFicha.putExtras(bundle);
                                        startActivity(intentGerenciarFicha);
                                    }
                                });
                            }
                            if(verificarFicha == 2){
                                nomePersonagem2.setText(f.getNomeDoPersonagem().isEmpty() || f.getNomeDoPersonagem() == null ? "---" : f.getNomeDoPersonagem());
                                classePersonagem2.setText("Classe: " + (f.getClasse().isEmpty() || f.getClasse() == null ? "---" : f.getClasse()));
                                racaPersonagem2.setText("Raça: " + (f.getRaca().isEmpty() || f.getRaca() == null ? "---" : f.getRaca()));
                                levelPersonagem2.setText("Level: " + (f.getNivel().toString().isEmpty() || f.getNivel() == null ? "---" : f.getNivel()));
                                idPersonagem2 = f.getId().isEmpty() ? null : f.getId();
                                btCriarFicha2.setVisibility(View.INVISIBLE);
                                btEditar2.setVisibility(View.VISIBLE);
                                btDeletar2.setVisibility(View.VISIBLE);
                                btEditar2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("ficha", f);
                                        bundle.putString("id_jogador", parametros.getString("id_usuario"));
                                        Intent intentGerenciarFicha = new Intent(GerenciamentoFichas.this , FichaPersonagem.class);
                                        intentGerenciarFicha.putExtras(bundle);
                                        startActivity(intentGerenciarFicha);
                                    }
                                });
                            }
                            if(verificarFicha == 3){
                                nomePersonagem3.setText(f.getNomeDoPersonagem().isEmpty() || f.getNomeDoPersonagem() == null ? "---" : f.getNomeDoPersonagem());
                                classePersonagem3.setText("Classe: " + (f.getClasse().isEmpty() || f.getClasse() == null ? "---" : f.getClasse()));
                                racaPersonagem3.setText("Raça: " + (f.getRaca().isEmpty() || f.getRaca() == null ? "---" : f.getRaca()));
                                levelPersonagem3.setText("Level: " + (f.getNivel().toString().isEmpty() || f.getNivel() == null ? "---" : f.getNivel()));
                                idPersonagem3 = f.getId().isEmpty() ? null : f.getId();
                                btCriarFicha3.setVisibility(View.INVISIBLE);
                                btEditar3.setVisibility(View.VISIBLE);
                                btDeletar3.setVisibility(View.VISIBLE);
                                btEditar3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("ficha", f);
                                        bundle.putString("id_jogador", parametros.getString("id_usuario"));
                                        Intent intentGerenciarFicha = new Intent(GerenciamentoFichas.this , FichaPersonagem.class);
                                        intentGerenciarFicha.putExtras(bundle);
                                        startActivity(intentGerenciarFicha);
                                    }
                                });
                            }
                            verificarFicha++;
                        }

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("ErroFichaPeloIdFirebase", "pegarFichaPeloIdFirebase: " + error.getMessage());
                }
            });

        }catch (Exception e){
            Log.e("ErroFichaPeloIdFirebase", "pegarFichaPeloIdFirebase: "+ e.getMessage());
        }
    }

    public void criarFicha( String idJogador){
        Bundle param = new Bundle();
        param.putString("id_jogador", idJogador);
        Intent intent = new Intent(GerenciamentoFichas.this, FichaPersonagem.class);
        intent.putExtras(param);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), TelaInicial.class);
        finish();
        startActivity(intent);
    }
}
