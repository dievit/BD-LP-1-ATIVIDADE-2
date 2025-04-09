package projeto;

import java.util.UUID;

public class Arvore {
    private String id;
    public String nome;
    public String especie;
    public int altura;
    private int idade;
    public String origem;

    public Arvore(String nome, String especie, int altura, int idade, String origem) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.especie = especie;
        this.altura = altura;
        this.idade = idade;
        this.origem = origem;
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

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public void exibirInformacoes() {
        System.out.println("Arvore: ");
        System.out.println("Nome: " + nome);
        System.out.println("Especie: " + especie);
        System.out.println("Altura: " + altura);
        System.out.println("Idade: " + idade);
        System.out.println("origem: " + origem);
    }
}
