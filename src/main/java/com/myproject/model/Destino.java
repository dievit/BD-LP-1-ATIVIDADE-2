package com.myproject.model;

public class Destino {
    private int id;
    private String nome;
    private String cidade;
    private String estado;
    private double distanciaKm;

    public Destino(int id, String nome, String cidade, String estado, double distanciaKm) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.distanciaKm = distanciaKm;
    }

    //construtor sem id
    public Destino(String nome, String cidade, String estado, double distanciaKm) {
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.distanciaKm = distanciaKm;
    }

    public Destino(int id) {
        this.id = id;
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
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    @Override
    public String toString() {
        return String.format("Destino [ID: %d, Nome: %s, Cidade: %s, Estado: %s, Distância: %.2f km]",
                id, nome, cidade, estado, distanciaKm);
    }

}
