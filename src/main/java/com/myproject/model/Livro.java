package com.myproject.model;

import java.time.LocalDate;
import java.util.UUID;

public class Livro {
    private String id;
    public String titulo;
    public String autor;
    public String genero;
    public int qtdPaginas;
    private String editora;
    private LocalDate anoPublicacao;
    public String idioma;

    public Livro(String titulo, String autor, String genero, int qtdPaginas, String editora, LocalDate anoPublicacao, String idioma) {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.qtdPaginas = qtdPaginas;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.idioma = idioma;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getQtdPaginas() {
        return qtdPaginas;
    }

    public void setQtdPaginas(int qtdPaginas) {
        this.qtdPaginas = qtdPaginas;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public LocalDate getAnoPublicacao() {
        return getAnoPublicacao();
    }

    public void setAnoPublicacao(LocalDate anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void exibirInformacoes() {
        System.out.println("Livro: ");
        System.out.println("Marca: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Genero: " + genero);
        System.out.println("Número de páginas: " + qtdPaginas);
        System.out.println("Editora: " + editora);
        System.out.println("Publicação: " + anoPublicacao);
        System.out.println("Idioma: " + idioma);
    }
}
