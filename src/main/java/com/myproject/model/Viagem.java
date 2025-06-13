package com.myproject.model;

import java.sql.Date;
import java.time.LocalDate;

public class Viagem {
    private int id;
    private Motorista motorista;
    private Carro carro;
    private Destino destino;
    private double distanciaKm;
    private double custoEstimado;
    private boolean emRota;
    private LocalDate dataViagem;

    public Viagem(int id, Motorista motorista, Carro carro, Destino destino, double distanciaKm, double custoEstimado, LocalDate dataViagem) {
        this.id = id;
        this.motorista = motorista;
        this.carro = carro;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
        this.custoEstimado = custoEstimado;
        this.emRota = false;
        this.dataViagem = dataViagem;

    }

    //constutor sem id
    public Viagem(Motorista motorista, Carro carro, Destino destino, double distanciaKm, double custoEstimado, LocalDate dataViagem) {
        this.motorista = motorista;
        this.carro = carro;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
        this.custoEstimado = custoEstimado;
        this.emRota = false;
        this.dataViagem = dataViagem;
    }

    //construtor vazio

    public Viagem() {

    }

    public Viagem(int id, Motorista motorista, Carro carro, Destino destino, double distanciaKm, double custoEstimado, boolean emRota, LocalDate dataViagem) {
        this.id = id;
        this.motorista = motorista;
        this.carro = carro;
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

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
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
        return String.format(
                "Viagem{id=%d, motorista=%s, carro=%s, destino=%s, distancia=%.2f km, custo=R$%.2f, emRota=%s, data=%s}",
                id,
                motorista != null ? motorista.getNome() : "N/A",
                carro != null ? carro.getModelo() : "N/A",
                destino != null ? destino.getNome() : "N/A",
                distanciaKm,
                custoEstimado,
                emRota ? "Sim" : "Não",
                dataViagem
        );
    }

}
