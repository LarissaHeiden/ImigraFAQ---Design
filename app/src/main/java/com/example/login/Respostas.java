package com.example.login;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.login.R.layout.activity_respostas;

public class Respostas extends AppCompatActivity {

    private String resposta, id, pergunta, categoria;
    Button btn_editar,btn_enviar;
    TextView txt_resposta,txt_comentario;
    private FirebaseDatabase database;
    private DatabaseReference comentarioReference;
    ArrayList<Comentario> comentarios;
    ListView listComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_respostas);
        database = FirebaseDatabase.getInstance();

        comentarios = new ArrayList<>();

        txt_comentario = (TextView) (findViewById(R.id.editTxtComentario));
        txt_resposta =(TextView) (findViewById(R.id.txt_resposta));
        btn_editar =  (Button) (findViewById(R.id.btn_editar));
        btn_enviar = (Button) (findViewById(R.id.btnEnviar));
        listComentario = (ListView) (findViewById(R.id.listViewComentarios));

        Intent intent = getIntent();
        id = (String) intent.getSerializableExtra("ID");
        pergunta = (String) intent.getSerializableExtra("PERGUNTA");
        resposta = (String) intent.getSerializableExtra("RESPOSTA");
        categoria= (String) intent.getSerializableExtra("CATEGORIA");

        comentarioReference = database.getReference("categorias/" + categoria + '/' + id + "/comentarios");

        txt_resposta.setText(resposta);

        btn_editar.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Respostas.this, UpdateTopico.class);
                intent.putExtra("ID", id);
                intent.putExtra("PERGUNTA", pergunta);
                intent.putExtra("RESPOSTA", resposta);
                intent.putExtra("CATEGORIA", categoria);
                startActivity(intent);
                finish();
            }
        });

        btn_enviar.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cadastrarComentario();

            }
        });

        comentarioReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                recarregarLista(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Ocorreu um erro", Toast.LENGTH_LONG).show();
            }
        });
        //deletar comentário
        listComentario.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Comentario comentario = comentarios.get(position);
                comentarioReference.child(comentario.getId()).removeValue();
                Toast.makeText(getApplicationContext(), "Comentário removido", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

        private void cadastrarComentario(){

            Comentario comentario = new Comentario();
            String idComentario = comentarioReference.push().getKey();

            comentario.setId(idComentario);
            comentario.setComentario(txt_comentario.getText().toString());

            comentarioReference.child(idComentario).setValue(comentario);

            txt_comentario.setText("");

        }


    private void recarregarLista(DataSnapshot dataSnapshot) {

        comentarios.clear();


        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

            Comentario comentario = postSnapshot.getValue(Comentario.class);

            comentarios.add(comentario);

        }

        ComentarioAdapter comentarioAdapter = new ComentarioAdapter(this, comentarios);

        listComentario.setAdapter(comentarioAdapter);

    }

}






