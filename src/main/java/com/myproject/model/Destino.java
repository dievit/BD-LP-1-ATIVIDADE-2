package com.myproject.model;

public class Destino {
    private int id;
    private String nome;
    private String cidade;
    private String estado;
    private double distanciakm;

    public Destino(int id, String nome, String cidade, String estado, double distanciakm) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.distanciakm = distanciakm;
    }

    //construtor sem id
    public Destino(String nome, String cidade, String estado, double distanciakm) {
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.distanciakm = distanciakm;
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getDistanciakm() {
        return distanciakm;
    }

    public void setDistanciakm(double distanciakm) {
        this.distanciakm = distanciakm;
    }

    @Override
    public String toString() {
        return "Destino{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", distanciakm=" + distanciakm +
                '}';
    }
}
