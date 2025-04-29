package com.myproject.model;

import java.util.UUID;

public class Pessoa {
    private String id;
    public String nome;
    private int idade;
    private String cpf;
    public String sexo;
    public String email;
    private String telefone;

    public Pessoa(String nome, int idade, String cpf, String sexo, String email, String telefone) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.sexo = sexo;
        this.email = email;
        this.telefone = telefone;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void exibirInformacoes() {
        System.out.println("Pessoa: ");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("CPF: " + cpf);
        System.out.println("Sexo: " + sexo);
        System.out.println("Email: " + email);
        System.out.println("Telefone: " + telefone);
    }
}
