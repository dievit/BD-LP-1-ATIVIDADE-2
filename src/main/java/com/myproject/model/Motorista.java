package com.myproject.model;

import java.time.LocalDate;
import java.util.Date;

public class Motorista {
    private int id;
    private String nome;
    private String cnh;
    private LocalDate validadeCNH;
    private String categoriaCNH;
    private String endRua;
    private String endNumero;
    private String endCidade;
    private String telefone;
    private String email;

    // Construtor sem id
    public Motorista(String nome, String cnh, LocalDate validadeCNH, String categoriaCNH, String endRua, String endNumero, String endCidade, String telefone, String email) {
        this.nome = nome;
        this.cnh = cnh;
        this.validadeCNH = validadeCNH;
        this.categoriaCNH = categoriaCNH;
        this.endRua = endRua;
        this.endNumero = endNumero;
        this.endCidade = endCidade;
        this.telefone = telefone;
        this.email = email;
    }

    // Construtor com id
        public Motorista(int id, String nome, String cnh, LocalDate validadeCNH, String categoriaCNH, String endRua, String endNumero, String endCidade, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.cnh = cnh;
        this.validadeCNH = validadeCNH;
        this.categoriaCNH = categoriaCNH;
        this.endRua = endRua;
        this.endNumero = endNumero;
        this.endCidade = endCidade;
        this.telefone = telefone;
        this.email = email;
    }

    // Construtor vazio
    public Motorista() {
        this.nome = "";
        this.cnh = "";
        this.validadeCNH = null;
        this.categoriaCNH = "";
        this.endRua = "";
        this.endNumero = "";
        this.endCidade = "";
        this.telefone = "";
        this.email = "";
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCnh() {
        return cnh;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getValidadeCNH() {
        return validadeCNH;
    }

    public void setValidadeCNH(LocalDate validadeCNH) {
        this.validadeCNH = validadeCNH;
    }

    public String getCategoriaCNH() {
        return categoriaCNH;
    }

    public void setCategoriaCNH(String categoriaCNH) {
        this.categoriaCNH = categoriaCNH;
    }

    public String getEndRua() {
        return endRua;
    }

    public void setEndRua(String endRua) {
        this.endRua = endRua;
    }

    public String getEndNumero() {
        return endNumero;
    }

    public void setEndNumero(String endNumero) {
        this.endNumero = endNumero;
    }

    public String getEndCidade() {
        return endCidade;
    }

    public void setEndCidade(String endCidade) {
        this.endCidade = endCidade;
    }
}
