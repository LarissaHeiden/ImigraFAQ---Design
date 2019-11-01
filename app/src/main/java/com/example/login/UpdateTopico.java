package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateTopico extends AppCompatActivity {
    EditText edt_pergunta, edt_resposta;
    Button btn_enviar;
    String id, pergunta, resposta, categoria;

    private FirebaseDatabase database;
    private DatabaseReference topicosReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_topico);

        database = FirebaseDatabase.getInstance();
        categoria = getIntent().getStringExtra("CATEGORIA");
        topicosReference = database.getReference("categorias/" + categoria);

        //busca no firebase o usuario conectado e se ele tem permissão
        edt_pergunta = findViewById(R.id.Pergunta);
        edt_resposta = findViewById(R.id.Resposta);
        btn_enviar = findViewById(R.id.btnEnviar);

        Intent intent = getIntent();
        id = (String) intent.getSerializableExtra("ID");

        pergunta = (String) intent.getSerializableExtra("PERGUNTA");
        resposta = (String) intent.getSerializableExtra("RESPOSTA");

        edt_pergunta.setText(pergunta);
        edt_resposta.setText(resposta);

        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Topico topico = new Topico();

            topico.setId(id);
            topico.setResposta(edt_resposta.getText().toString());
            topico.setPergunta(edt_pergunta.getText().toString());

            topicosReference.child(id).setValue(topico);

            Toast.makeText(UpdateTopico.this, "Tópico alterado", Toast.LENGTH_SHORT).show();

            finish();

            }
        });

    }

    }

