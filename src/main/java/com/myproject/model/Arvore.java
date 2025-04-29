package com.myproject.model;

import java.util.UUID;

public class Arvore {
    private String id;
    public String nome;
    public String especie;
    public int altura;
    private int idade;
    public String origem;

    public Arvore(String nome, String especie, int altura, int idade, String origem) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.especie = especie;
        this.altura = altura;
        this.idade = idade;
        this.origem = origem;
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

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String exibirInformacoes() {
        return "Nome: " + nome + "\n" +
                "Espécie: " + especie + "\n" +
                "Altura: " + altura + "\n" +
                "Idade: " + idade + "\n" +
                "Origem: " + origem;
    }


    public void crescer(int metros) {
        if (metros > 0) {
            altura += metros;
        }
    }

    public void podar(int metros) {
        if (metros > 0 && metros < altura) {
            altura -= metros;
        } else if (metros >= altura) {
            altura = 0;
        }
    }

    public void envelhecer(int anos) {
        if (anos > 0) {
            idade += anos;
        }
    }

    public void replantar(String replantar) {
        this.origem = replantar;
    }

    @Override
    public String toString() {
        return nome;
    }
}
