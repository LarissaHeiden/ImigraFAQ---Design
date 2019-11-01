package com.example.login;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListarTopicos extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference topicosReference;

    ListView listTopicos;
    ArrayList<Topico> topicos;

    String categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_topicos);
        database = FirebaseDatabase.getInstance();

        categoria = getIntent().getStringExtra("CATEGORIA");
        topicosReference = database.getReference("categorias/" + categoria);

        FloatingActionButton fab = findViewById(R.id.btn_Cadastrar);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ListarTopicos.this, AddTopico.class);

                intent.putExtra("CATEGORIA", categoria);

                startActivity(intent);
            }
        });

        listTopicos = (ListView) findViewById(R.id.listTopicos);

        topicos = new ArrayList<>();

        topicosReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                recarregarLista(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Ocorreu um erro", Toast.LENGTH_LONG).show();
            }
        });
        //abrir a resposta da pergunta selecionada
        listTopicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListarTopicos.this, Respostas.class);
                intent.putExtra("ID", topicos.get(position).getId());
                intent.putExtra("PERGUNTA", topicos.get(position).getPergunta());
                intent.putExtra("RESPOSTA", topicos.get(position).getResposta());
                intent.putExtra("CATEGORIA", categoria);

                startActivity(intent);

            }
        });

        //deletar tópico
        listTopicos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Topico topico = topicos.get(position);
                topicosReference.child(topico.getId()).removeValue();
                Toast.makeText(getApplicationContext(), "Tópico removido", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    private void recarregarLista(DataSnapshot dataSnapshot) {

        topicos.clear();

        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

            Topico topico = postSnapshot.getValue(Topico.class);

            topicos.add(topico);

        }

        TopicoAdapter lancamentoAdapter = new TopicoAdapter(this, topicos);

        listTopicos.setAdapter(lancamentoAdapter);
    }
}
