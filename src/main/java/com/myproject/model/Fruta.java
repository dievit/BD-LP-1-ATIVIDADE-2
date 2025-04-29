package com.myproject.model;

import java.util.UUID;

public class Fruta {
    private String id;
    public String nome;
    public String cor;
    public int peso;
    private String origem;
    public String sabor;

    public Fruta(String nome, String cor, int peso, String origem, String sabor) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.cor = cor;
        this.peso = peso;
        this.origem = origem;
        this.sabor = sabor;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }
}
