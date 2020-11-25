package com.example.ppi;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import model.Jogador;

public class JogadorRepository extends AppCompatActivity {

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth jogadorAuth = FirebaseAuth.getInstance();

    public void cadastrarJogador(Jogador jogador, final Context context){
        try{
            jogadorAuth.createUserWithEmailAndPassword(jogador.getEmail(),jogador.getSenha())
                    .addOnCompleteListener(JogadorRepository.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                cadastradoMensagem("0",context);
                            }else{
                                cadastradoMensagem("1",context);
                            }
                        }
                    });
        }catch (Exception e){
            Log.e("CreateUser", e.getMessage());
        }
    }

    public void cadastradoMensagem(String codigo, Context context){
        if(codigo.equals("0")){
            Toast.makeText(context,"Jogador cadastrado com sucesso", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Falha ao cadastrar, tente outro email", Toast.LENGTH_SHORT).show();
        }
    }


}
