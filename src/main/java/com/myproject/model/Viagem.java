package com.myproject.model;

import java.sql.Date;
import java.time.LocalDate;

public class Viagem {
    private int id;
    private Motorista motorista;
    private Carro carro;
    private String partida;
    private String destino;
    private double distanciaKm;
    private double custoEstimado;
    private boolean emRota;
    private LocalDate dataViagem;

    public Viagem(int id, Motorista motorista, Carro carro, String partida, String destino, double distanciaKm, double custoEstimado, LocalDate dataViagem) {
        this.id = id;
        this.motorista = motorista;
        this.carro = carro;
        this.partida = partida;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
        this.custoEstimado = custoEstimado;
        this.emRota = false;
        this.dataViagem = dataViagem;

    }

    //constutor sem id
    public Viagem(Motorista motorista, Carro carro, String partida, String destino, double distanciaKm, double custoEstimado, LocalDate dataViagem) {
        this.motorista = motorista;
        this.carro = carro;
        this.partida = partida;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
        this.custoEstimado = custoEstimado;
        this.emRota = false;
        this.dataViagem = dataViagem;
    }

    //construtor vazio

    public Viagem() {

    }

    public Viagem(int id, Object motoristaId, Object carroId, String partida, String destino, double distanciaKm, double custoEstimado, boolean emRota, LocalDate dataViagem) {
        this.id = id;
        this.motorista = new Motorista((int) motoristaId);
        this.carro = new Carro((int) carroId);
        this.partida = partida;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
        this.custoEstimado = custoEstimado;
        this.emRota = emRota;
        this.dataViagem = dataViagem;
    }

    //metodos de acesso

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public double getCustoEstimado() {
        return custoEstimado;
    }

    public void setCustoEstimado(double custoEstimado) {
        this.custoEstimado = custoEstimado;
    }

    public boolean isEmRota() {
        return emRota;
    }

    public void setEmRota(boolean emRota) {
        this.emRota = emRota;
    }

    public LocalDate getDataViagem() {
        return dataViagem;
    }

    public void setDataViagem(LocalDate dataViagem) {
        this.dataViagem = dataViagem;
    }

    @Override
    public String toString() {
        return "Viagem{" +
                "id=" + id +
                ", motorista=" + motorista +
                ", carro=" + carro +
                ", partida='" + partida + '\'' +
                ", destino='" + destino + '\'' +
                ", distanciaKm=" + distanciaKm +
                ", custoEstimado=" + custoEstimado +
                ", emRota=" + emRota +
                ", dataViagem=" + dataViagem +
                '}';
    }
}
