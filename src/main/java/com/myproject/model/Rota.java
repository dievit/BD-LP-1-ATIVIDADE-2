package com.myproject.model;

public class Rota {
    private String partida;
    private String destino;
    private double distanciaKm;


    public Rota(String partida, String destino, double distanciaKm) {
        this.partida = partida;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
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
}
