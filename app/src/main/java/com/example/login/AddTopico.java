package com.example.login;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddTopico extends AppCompatActivity {

    EditText pergunta, resposta;
    Button enviar;

    private FirebaseDatabase database;
    private DatabaseReference topicosReference;

    ListView listTopicos;

    ArrayList<Topico> topicos;

    String categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_topico);

        database = FirebaseDatabase.getInstance();
        categoria = getIntent().getStringExtra("CATEGORIA");
        topicosReference = database.getReference("categorias/" + categoria);

        pergunta = findViewById(R.id.Pergunta);
        resposta = findViewById(R.id.Resposta);
        enviar = findViewById(R.id.btnEnviar);

        topicos = new ArrayList<>();
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarComentario();
            }
        });

    }
    //busca no firebase o usuario conectado e se ele tem permissão
    private void cadastrarComentario(){
        String pergunta = this.pergunta.getText().toString().trim();
        String resposta = this.resposta.getText().toString().trim();

        if(!TextUtils.isEmpty(pergunta) && !TextUtils.isEmpty(resposta)){

            String id = topicosReference.push().getKey();


            Topico topico = new Topico();
            topico.setPergunta(pergunta);
            topico.setResposta(resposta);
            topico.setId(id);

            topicosReference.child(id).setValue(topico);

            Toast.makeText(this, "Pergunta submetida", Toast.LENGTH_LONG).show();

            finish();

        }else{
            Toast.makeText(this, "Não deixe nenhum campo em branco", Toast.LENGTH_LONG).show();
        }

    }

}