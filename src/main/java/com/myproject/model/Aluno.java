package com.myproject.model;

import java.util.Scanner;


public class Aluno {
    private Integer id;
    private String nome;
    private String matricula;
    private String curso;

    public Aluno(String nome, String matricula, String curso) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
    }

    public Aluno() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCurso() {
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

