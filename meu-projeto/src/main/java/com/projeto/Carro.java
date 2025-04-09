package projeto;

import java.util.UUID;

public class Carro {
    private String id;
    public String marca;
    public String modelo;
    public int ano;
    private String placa;
    private int kmRodado;

    public Carro(String marca, String modelo, int ano, String placa, int kmRodado) {
        this.id = UUID.randomUUID().toString();
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.kmRodado = kmRodado;
    }

    public void exibirInformacoes() {
        System.out.println("Carro: ");
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Ano: " + ano);
        System.out.println("Placa: " + placa);
        System.out.println("Km Rodado: " + kmRodado);
    }


    public String getId() {
        return id;
    }

    public String getMarca(){
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getPlaca(){
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getKmRodado() {
        return kmRodado;
    }

    public void setKmRodado(int kmRodado) {
        this.kmRodado = kmRodado;
    }


}
