package com.example.nmpetry.myapp.models;

import java.io.Serializable;

public class Palestrante implements Serializable {
    private int id;
    private String nome;

    public Palestrante(int id, String nome)
    {
        this.nome=nome;
        this.id=id;
    }

    public Palestrante()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString()
    {
        return this.nome;
    }
}
