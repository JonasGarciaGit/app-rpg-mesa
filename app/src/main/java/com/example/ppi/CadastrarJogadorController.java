package com.example.ppi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import model.Jogador;

public class CadastrarJogadorController extends AppCompatActivity {

    private TextInputLayout usuario, email, senha, senhaConfirmar;
    private JogadorRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro);

        usuario = findViewById(R.id.inputUsuario);
        email = findViewById(R.id.inputEmail);
        senha = findViewById(R.id.inputSenha);
        senhaConfirmar = findViewById(R.id.inputConfirmarSenha);
        repository = new JogadorRepository();
    }

    public void cadastrarJogador(View view){

        String codigoRetorno = this.validaCampos();

        if("0".equals(codigoRetorno)){
            Jogador jogador = new Jogador(
                    usuario.getEditText().getText().toString(),
                    email.getEditText().getText().toString(),
                    senha.getEditText().getText().toString());

                repository.cadastrarJogador(jogador, this);
                limparCampos();
        }
    }

    public String validaCampos(){
        if(usuario.getEditText().getText().toString().isEmpty()){
            Toast.makeText(this,"Campo usuario precisa ser preenchido",Toast.LENGTH_SHORT).show();
            return "1";
        }
        if(email.getEditText().getText().toString().isEmpty()){
            Toast.makeText(this,"Campo email precisa ser preenchido",Toast.LENGTH_SHORT).show();
            return "1";
        }
        if(senha.getEditText().getText().toString().isEmpty()){
            Toast.makeText(this,"Campo senha deve ser preenchido",Toast.LENGTH_SHORT).show();
            return "1";
        }
        if(senhaConfirmar.getEditText().getText().toString().isEmpty()){
            Toast.makeText(this,"Campo confirmar senha deve ser preenchido",Toast.LENGTH_SHORT).show();
            return "1";
        }
        if(!senha.getEditText().getText().toString().equals(senhaConfirmar.getEditText().getText().toString())){
            Toast.makeText(this,"As senhas não coincidem!",Toast.LENGTH_SHORT).show();
            return "1";
        }
        return "0";
    }

    public void limparCampos(){
        usuario.getEditText().setText("");
        email.getEditText().setText("");
        senha.getEditText().setText("");
        senhaConfirmar.getEditText().setText("");
    }
}
