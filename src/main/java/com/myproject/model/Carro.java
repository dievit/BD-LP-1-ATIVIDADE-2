package com.myproject.model;

import java.util.UUID;

public class Carro {
    private String id;
    private String marca;
    private String modelo;
    private int kmRodado;
    private int consumo;
    private int capacidadeTanque;
    private int nivelCombustivel;
    private String image;

    public Carro(String modelo, String marca, int kmRodado, int consumo, int capacidadeTanque, int nivelCombustivel, String image) {
        this.id = UUID.randomUUID().toString();
        this.modelo = modelo;
        this.marca = marca;
        this.kmRodado = kmRodado;
        this.consumo = consumo;
        this.capacidadeTanque = capacidadeTanque;
        this.nivelCombustivel = nivelCombustivel;
        this.image = image;
    }

    public void exibirInformacoes() {
        System.out.println("Carro: ");
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Km Rodado: " + kmRodado);
        System.out.println("Consumo: " + consumo);
        System.out.println("Capacidade do Tanque: " + capacidadeTanque);
    }


    public String getId() {
        return id;
    }

    public String getMarca(){
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getKmRodado() {
        return kmRodado;
    }

    public void setKmRodado(int kmRodado) {
        this.kmRodado = kmRodado;
    }

    public int getConsumo() {
        return consumo;
    }

    public int getCapacidadeTanque() {
        return capacidadeTanque;
    }

    public int getNivelCombustivel() {
        return nivelCombustivel;
    }

    public void setNivelCombustivel(int nivelCombustivel) {
        this.nivelCombustivel = nivelCombustivel;
    }

    public String getImage() {
        return image;
    }


    @Override
    public String toString() {
        return modelo;
    }
}
