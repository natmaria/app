package com.example.nmpetry.myapp.models;

import java.io.Serializable;

public class Evento implements Serializable {
    private String data;
    private String titulo;
    private String descricao;
    private int vagas;
    private int id;
    private int id_palestrante;

    public Evento(int id, String data, String titulo, String descricao, int vagas, int palestrante)
    {
        this.data=data;
        this.titulo=titulo;
        this.descricao=descricao;
        this.vagas=vagas;
        this.id=id;
        this.id_palestrante=palestrante;
    }

    public Evento()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId_palestrante() {
        return id_palestrante;
    }

    public void setId_palestrante(int id_palestrante) {
        this.id_palestrante = id_palestrante;
    }

    public String toString()
    {
        return this.data + " - " + this.titulo;
    }
}
