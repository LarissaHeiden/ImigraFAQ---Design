package com.example.login;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Categorias extends AppCompatActivity {

    private Button btnSaude;
    private Button btnEducacao;
    private Button btnEmpregos;
    private Button btnCras;
    private Button btnContatos;
    private Button btnEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        btnSaude = (Button) findViewById(R.id.btnSaude);
        btnEducacao = (Button) findViewById(R.id.btnEducacao);
        btnEmpregos = (Button) findViewById(R.id.btnEmpregos);
        btnCras = (Button) findViewById(R.id.btnCras);
        btnContatos = (Button) findViewById(R.id.btnContatos);
        btnEventos = (Button) findViewById(R.id.btnEventos);

        btnSaude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AbrirTopico("saude");

            }
        });

        btnEducacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AbrirTopico("educacao");

            }
        });

        btnEmpregos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AbrirTopico("empregos");

            }
        });

        btnCras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AbrirTopico("cras");

            }
        });

        btnContatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AbrirTopico("contatos");

            }
        });

        btnEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AbrirTopico("eventos");

            }
        });

    }


    private void AbrirTopico(String categoria) {

        Intent intent = new Intent(Categorias.this, ListarTopicos.class);

        intent.putExtra("CATEGORIA", categoria);

        startActivity(intent);

    }

}
