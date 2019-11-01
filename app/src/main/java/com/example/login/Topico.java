package com.example.login;

public class Topico {

    private String pergunta;
    private String resposta;
    private String id;


    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Topico() {

    }

    public Topico(String pergunta, String resposta, String id) {
        this.pergunta = pergunta;
        this.resposta = resposta;
        this.id = id;

    }

}