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

    public Carro(String modelo, String marca, int kmRodado, int consumo, int capacidadeTanque, int nivelCombustivel) {
        this.id = UUID.randomUUID().toString();
        this.modelo = modelo;
        this.marca = marca;
        this.kmRodado = kmRodado;
        this.consumo = consumo;
        this.capacidadeTanque = capacidadeTanque;
        this.nivelCombustivel = nivelCombustivel;
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

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
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

    public void setConsumo(int consumo) {
        this.consumo = consumo;
    }

    public int getCapacidadeTanque() {
        return capacidadeTanque;
    }

    public void setCapacidadeTanque(int capacidadeTanque) {
        this.capacidadeTanque = capacidadeTanque;
    }

    public int getNivelCombustivel() {
        return nivelCombustivel;
    }

    public void setNivelCombustivel(int nivelCombustivel) {
        this.nivelCombustivel = nivelCombustivel;
    }

    @Override
    public String toString() {
        return modelo;
    }
}
