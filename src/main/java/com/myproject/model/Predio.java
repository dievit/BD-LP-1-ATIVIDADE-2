package com.myproject.model;

import java.util.UUID;

public class Predio {
    private String id;
    public String nome;
    public String endereco;
    private int qtdAndares;
    private int areaTotal;
    public int anoConstrucao;

    public Predio(String nome, String endereco, int qtdAndares, int areaTotal, int anoConstrucao) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.endereco = endereco;
        this.qtdAndares = qtdAndares;
        this.areaTotal = areaTotal;
        this.anoConstrucao = anoConstrucao;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getQtdAndares() {
        return qtdAndares;
    }

    public void setQtdAndares(int qtdAndares) {
        this.qtdAndares = qtdAndares;
    }

    public int getAreaTotal() {
        return areaTotal;
    }

    public void setAreaTotal(int areaTotal) {
        this.areaTotal = areaTotal;
    }

    public int getAnoConstrucao() {
        return anoConstrucao;
    }

    public void setAnoConstrucao(int anoConstrucao) {
        this.anoConstrucao = anoConstrucao;
    }

    public void exibirInformacoes() {
        System.out.println("Prédio: ");
        System.out.println("Nome: " + nome);
        System.out.println("Endereço: " + endereco);
        System.out.println("Andares: " + qtdAndares);
        System.out.println("Area total: " + areaTotal);
        System.out.println("Ano de construção: " + anoConstrucao);
    }
}
