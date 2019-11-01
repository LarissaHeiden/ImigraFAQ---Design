package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;


public class Login extends AppCompatActivity {

    private EditText edtEmail, edtSenha;
    private FirebaseAuth mAuth;
    private Button btnLogin, btnCadastro;
    private ProgressDialog progressDialog;

//    PersmissaoController persmissaoController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        edtEmail = findViewById(R.id.editTextEmail);
        edtSenha = findViewById(R.id.editTextSenha);
        btnCadastro = findViewById(R.id.buttonCadastro);
        btnLogin = findViewById(R.id.buttonLogin);
        progressDialog = new ProgressDialog(this);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            getUserInfo();
            abreActivity();
//
//            persmissaoController =new PersmissaoController();
//            persmissaoController.setmAuth(mAuth);
        }
//        if( persmissaoController.usuarioTemPermissao("usuario_cadastro"))
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastro();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public void cadastro() {
        Intent i = new Intent(getApplicationContext(), Cadastro.class);
        startActivity(i);
    }

    public void Login() {

        String email = edtEmail.getText().toString().trim();
        String senha = edtSenha.getText().toString().trim();

        if (email.equals("")) {
            Toast.makeText(getApplicationContext(), "Campo 'email' obrigatório", Toast.LENGTH_LONG).show();
            return;
        }

        if (senha.equals("")) {
            Toast.makeText(getApplicationContext(), "Campo 'senha' obrigatório", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Acessando a conta...");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), Categorias.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Usuário e/ou senha não encontrado", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public void abreActivity() {

        Intent i = new Intent(getApplicationContext(), Categorias.class);
        startActivity(i);

    }

    public void getUserInfo() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();
        boolean emailVerified = user.isEmailVerified();
    }

}

