package com.myproject.model;

import java.util.UUID;

public class Carro {
    private int id;
    private String marca;
    private String modelo;
    private String placa;
    private int kmRodado;
    private int consumo;
    private int capacidadeTanque;
    private int nivelCombustivel;
    private String image;

    //construtor sem id
    public Carro(String modelo, String marca, String placa, int kmRodado, int consumo, int capacidadeTanque, int nivelCombustivel, String image) {
        this.modelo = modelo;
        this.marca = marca;
        this.placa = placa;
        this.kmRodado = kmRodado;
        this.consumo = consumo;
        this.capacidadeTanque = capacidadeTanque;
        this.nivelCombustivel = nivelCombustivel;
        this.image = image;
    }

    //construtor com id
    public Carro(int id, String modelo, String marca, int kmRodado, int consumo, int capacidadeTanque, int nivelCombustivel, String image) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.kmRodado = kmRodado;
        this.consumo = consumo;
        this.capacidadeTanque = capacidadeTanque;
        this.nivelCombustivel = nivelCombustivel;
        this.image = image;
    }

    //construtor vazio
    public Carro() {
        this.modelo = "";
        this.marca = "";
        this.placa = "";
        this.kmRodado = 0;
        this.consumo = 0;
        this.capacidadeTanque = 0;
        this.nivelCombustivel = 0;
        this.image = "";
    }


    public int getId() {
        return id;
    }

    public String getMarca(){
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }



    public String getModelo() {
        return modelo;
    }

    public String getPlaca() {return placa;}

    public void setPlaca(String placa) {
        this.placa = placa;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return modelo;
    }
}
