package com.myproject.model;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class Aluno {
    private String id;
    public String nome;
    public String matricula;
    public String curso;

    public Aluno(String nome, String matricula, String curso) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
    }

    public static final Aluno Joao = new Aluno("João da Silva", "123456", "Engenharia de Software");
    public static final Aluno Maria = new Aluno("Maria Oliveira", "654321", "Ciência da Computação");

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso(){
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }


    public void exibirInformacoes() {
        System.out.println("Aluno: ");
        System.out.println("Matricula: " + matricula);
        System.out.println("Nome: " + nome);
        System.out.println("Curso: " + curso);
    }

    public String fazerProva() {
        String passou = "Parabéns, valeu a pena estudar!";
        String reprovado = "Todo mundo falou que deveria ter estudado!";
        Scanner scanner = new Scanner(System.in);
        String estudou = scanner.next();
        if (estudou.equalsIgnoreCase("sim")) {
            return passou;
        } else {
            return reprovado;
        }
    }

    public String getResumo() {
        return String.format("Nome: %s\nMatrícula: %s\nCurso: %s\nE-mail: %s\nTelefone: %s",
                nome, matricula, curso);
    }


    public String frequentarAulas() {
        String frequente = "Parabéns, você é um aluno exemplar!";
        String ausente = "Você precisa comparecer mais às aulas!";
        Scanner scanner = new Scanner(System.in);
        String compareceu = scanner.next();
        if (compareceu.equalsIgnoreCase("sim")) {
            return frequente;
        } else {
            return ausente;
        }
    }
}

