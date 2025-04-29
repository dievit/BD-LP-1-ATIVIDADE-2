package com.myproject.model;

import java.util.UUID;

public class Encomenda {
    private String id;
    public String codRastreio;
    public String remetente;
    public String destinatario;
    public String enderecoDestino;
    public int peso;
    public String status;
    public String dataEnvio;
    public String dataEntrega;

    public Encomenda(String codRastreio, String remetente, String destinatario, String enderecoDestino, int peso, String status, String dataEnvio, String dataEntrega) {
        this.id = UUID.randomUUID().toString();
        this.codRastreio = codRastreio;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.enderecoDestino = enderecoDestino;
        this.peso = peso;
        this.status = status;
        this.dataEnvio = dataEnvio;
        this.dataEntrega = dataEntrega;
    }

    public void exibirInformacoes() {
        System.out.println("Encomenda: ");
        System.out.println("Código de Rastreio: " + codRastreio);
        System.out.println("Remetente: " + remetente);
        System.out.println("Destinatário: " + destinatario);
        System.out.println("Peso: " + peso);
        System.out.println("Status: " + status);
        System.out.println("Data de Envio: " + dataEnvio);
        System.out.println("Data de Entrega: " + dataEntrega);
    }

    public String getId(){
        return id;
    }

    public String getCodRastreio() {
        return codRastreio;
    }

    public void setCodRastreio(String codRastreio) {
        this.codRastreio = codRastreio;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getEnderecoDestino() {
        return enderecoDestino;
    }

    public void setEnderecoDestino(String enderecoDestino) {
        this.enderecoDestino = enderecoDestino;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(String dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
}