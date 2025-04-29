package com.myproject.model;

import java.util.UUID;

public class Bicicleta {
    private String id;
    private String marca;
    private String modelo;
    private int aro;
    private int peso;
    private String tipo;
    private String cor;
    private int qtdMarcha;
    private int calibragemAtual;
    private int calibragemIdeal;
    private String imagem;

    public Bicicleta(String marca, String modelo, int aro, int peso, String tipo, String cor, int qtdMarcha, int calibragemAtual, int calibragemIdeal, String imagem) {
        this.id = UUID.randomUUID().toString();
        this.marca = marca;
        this.modelo = modelo;
        this.aro = aro;
        this.peso = peso;
        this.tipo = tipo;
        this.cor = cor;
        this.qtdMarcha = qtdMarcha;
        this.calibragemAtual = calibragemAtual;
        this.calibragemIdeal = calibragemIdeal;
        this.imagem = imagem;
    }
    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getId(){
        return id;
    }

    public String getMarca() {
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

    public int getAro() {
        return aro;
    }

    public void setAro(int aro) {
        this.aro = aro;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getQtdMarcha() {
        return qtdMarcha;
    }

    public void setQtdMarcha(int qtdMarcha) {
        this.qtdMarcha = qtdMarcha;
    }

    public int getCalibragemAtual() {
        return calibragemAtual;
    }

    public void setCalibragemAtual(int calibragemAtual) {
        this.calibragemAtual = calibragemAtual;
    }

    public int getCalibragemIdeal() {
        return calibragemIdeal;
    }

    public void setCalibragemIdeal(int calibragemIdeal) {
        this.calibragemIdeal = calibragemIdeal;
    }

    public void exibirInformacoes() {
        System.out.println("Bicicleta: ");
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Aro: " + aro);
        System.out.println("Peso: " + peso);
        System.out.println("Cor: " + cor);
        System.out.println("Marchas: " + qtdMarcha);
        System.out.println("Calibragem Atual: " + calibragemAtual + " PSI");
        System.out.println("Calibragem Ideal: " + calibragemIdeal + " PSI");
    }

    @Override
    public String toString() {
        return modelo;
    }
}
