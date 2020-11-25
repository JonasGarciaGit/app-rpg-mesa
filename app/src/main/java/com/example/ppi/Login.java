package com.example.ppi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private TextInputLayout email, senha;
    private JogadorRepository repository;
    Button signInButton;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 0;
    private FirebaseAuth jogadorAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        email = findViewById(R.id.loginUsuario);
        senha = findViewById(R.id.loginSenha);
        signInButton = findViewById(R.id.google_login);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.google_login:
                        signIn();
                        break;
                    // ...
                }
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Intent intent = new Intent(Login.this,TelaInicial.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("ERROR.:", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    public void validarLogin(View view){
        String codigoRetorno = this.validaLoginSenha();
        if("0".equals(codigoRetorno)) {
            jogadorAuth.signInWithEmailAndPassword(email.getEditText().getText().toString(), senha.getEditText().getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                isLoginSucesso("0");
                            }else{
                                isLoginSucesso("1");
                            }
                        }
                    });
        }
    }

    public void isLoginSucesso(String codigo){
        if(codigo.equals("0")){
            Toast.makeText(this,"Login efetuado com sucesso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Login.this,TelaInicial.class);
            startActivity(intent);
        }else{
            Toast.makeText(this,"Email/Senha não encontrado", Toast.LENGTH_SHORT).show();
        }
    }

    public String validaLoginSenha(){
        if(email.getEditText().getText().toString().isEmpty()){
            Toast.makeText(this,"Campo email precisa ser preenchido!", Toast.LENGTH_SHORT).show();
            return "1";
        }
        if(senha.getEditText().getText().toString().isEmpty()){
            Toast.makeText(this,"Campo senha precisa ser preenchido!", Toast.LENGTH_SHORT).show();
            return "1";
        }
        return "0";
    }

    public void trocarTela(View view){
        Intent intent = new Intent(Login.this, CadastrarJogadorController.class);
        startActivity(intent);
    }


}