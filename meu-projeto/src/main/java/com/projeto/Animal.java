package projeto;

import java.util.UUID;

public class Animal {
    private String id;
    public String nome;
    public String especie;
    public String raca;
    private int idade;
    public String sexo;
    private int peso;

    public Animal(String nome, String especie, String raca, int idade, String sexo, int peso) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.sexo = sexo;
        this.peso = peso;
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

    public void setEspecie() {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade() {
        this.idade = idade;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso() {
        this.peso = peso;
    }

    public void exibirInformacoes() {
        System.out.println("Animal: ");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Espécie: " + especie);
        System.out.println("Raça: " + raca);
        System.out.println("Sexo: " + sexo);
        System.out.println("Peso: " + peso);
    }
}
