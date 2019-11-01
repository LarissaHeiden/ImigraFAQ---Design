package com.example.login;

public class Comentario {

    String id;
    String comentario;
    String user;

    public String getId() {
        return id;
    }

    public String getComentario() {
        return comentario;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public Comentario(){

    }

    public Comentario(String IdComentario, String txtComentario){
        this.id = IdComentario;
        this.comentario = txtComentario;
    }

}


