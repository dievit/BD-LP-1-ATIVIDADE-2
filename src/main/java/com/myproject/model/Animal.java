package com.myproject.model;

import java.util.UUID;

public class Animal {
    private String nome;
    private String tipo;
    private String som;
    private String origem;
    private String imagem;
    private String imageOrigem;

    public Animal(String nome, String origem, String tipo, String som, String imagem, String imageOrigem) {
        this.nome = nome;
        this.tipo = tipo;
        this.som = som;
        this.origem = origem;
        this.imagem = imagem;
        this.imageOrigem = imageOrigem;

    }
    public String getImageOrigem() {
        return imageOrigem;
    }

    public void setImageOrigem(String imageOrigem) {
        this.imageOrigem = imageOrigem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSom() {
        return som;
    }

    public void setSom(String som) {
        this.som = som;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return nome; // ComboBox vai mostrar apenas o nome
    }
}

