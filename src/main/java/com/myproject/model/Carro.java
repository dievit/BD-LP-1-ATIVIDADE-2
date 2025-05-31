package com.myproject.model;

import java.util.UUID;

public class Carro {
    private int id;
    private String marca;
    private String modelo;
    private String placa;
    private int kmRodado;
    private double consumo;
    private int capacidadeTanque;
    private int nivelCombustivel;
    private int disponibilidade;
    private String tipo; // Tipo do carro (pode ser SUV, Sedan, Hatch, etc.)
    private String image;

    //construtor sem id
    public Carro(String modelo, String marca, String placa, String tipo, int kmRodado, double consumo, int capacidadeTanque, int nivelCombustivel, int disponibilidade, String image) {
        this.modelo = modelo;
        this.marca = marca;
        this.tipo = tipo;
        this.placa = placa;
        this.kmRodado = kmRodado;
        this.consumo = consumo;
        this.capacidadeTanque = capacidadeTanque;
        this.nivelCombustivel = nivelCombustivel;
        this.disponibilidade = disponibilidade;
        this.image = image;
    }

    //construtor com id
    public Carro(int id, String modelo, String marca, String tipo, String placa, int kmRodado, double consumo, int capacidadeTanque, int nivelCombustivel, int disponibilidade, String image) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
        this.placa = placa;
        this.kmRodado = kmRodado;
        this.consumo = consumo;
        this.capacidadeTanque = capacidadeTanque;
        this.nivelCombustivel = nivelCombustivel;
        this.disponibilidade = disponibilidade;
        this.image = image;
    }

    //construtor vazio
    public Carro() {
        this.modelo = "";
        this.marca = "";
        this.tipo = "";
        this.placa = "";
        this.kmRodado = 0;
        this.consumo = 0;
        this.capacidadeTanque = 0;
        this.nivelCombustivel = 0;
        this.disponibilidade = 0; // 0 para disponível, 1 para em manutenção e 2 para em rota
        this.image = "";
    }

    public int getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(int disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getStatus() {
        return switch (this.disponibilidade) {
            case 0 -> "Disponível";
            case 1 -> "Em manutenção";
            case 2 -> "Em rota";
            default -> "Desconhecido";
        };
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
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
