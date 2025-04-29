package com.myproject.controller;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.myproject.model.Carro;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static java.lang.Integer.parseInt;

public class CarroController {
    @FXML
    void voltarTelaInicial(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/TelaInicial.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void fecharAplicacao(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAbastecer;

    @FXML
    private Button btnVerificar;

    @FXML
    private Button btnViajar;

    @FXML
    private TextField input2;

    @FXML
    private TextField input1;

    @FXML
    private Button btnSair;

    @FXML
    private ChoiceBox<Carro> comboChoice;

    @FXML
    private TableView<?> table;

    @FXML
    private TextArea textField;

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnAutonomia;


    @FXML
    void initialize() {
        Carro carro1 = new Carro("Fusca", "Volkswagen", 120000, 10, 50, 20);
        Carro carro2 = new Carro("Civic", "Honda", 80000, 12, 40, 30);
        Carro carro3 = new Carro("Corolla", "Toyota", 60000, 11, 45, 25);
        Carro carro4 = new Carro("Onix", "Chevrolet", 50000, 15, 35, 20);
        Carro carro5 = new Carro("Palio", "Fiat", 70000, 14, 38, 22);
        Carro carro6 = new Carro("Gol", "Volkswagen", 90000, 13, 42, 28);
        Carro carro7 = new Carro("HB20", "Hyundai", 55000, 16, 37, 24);
        Carro carro8 = new Carro("Kwid", "Renault", 45000, 18, 30, 20);
        Carro carro9 = new Carro("Argo", "Fiat", 65000, 17, 36, 26);
        Carro carro10 = new Carro("Sandero", "Renault", 75000, 19, 32, 22);

        comboChoice.getItems().addAll(carro1, carro2, carro3, carro4, carro5, carro6, carro7, carro8, carro9, carro10);

        comboChoice.setOnAction(event -> {
            Carro selecionado = comboChoice.getValue();

            if (selecionado != null) {
                String info = "Marca: " + selecionado.getMarca() + "\n" +
                        "Modelo: " + selecionado.getModelo() + "\n" +
                        "Km Rodado: " + selecionado.getKmRodado() + "\n" +
                        "Consumo: " + selecionado.getConsumo() + "Km/L" + "\n" +
                        "Capacidade do Tanque: " + selecionado.getCapacidadeTanque() + "L" + "\n" +
                        "Nível de Combustível: " + selecionado.getNivelCombustivel() + "L";

                textField.setText(info);
            }
        });
    }

    @FXML
    void verificarCombustivel() {
        Carro selecionado = comboChoice.getValue();
        if (selecionado != null) {
            int nivelCombustivel = selecionado.getNivelCombustivel();
            double distanciaViagem = Double.parseDouble(input1.getText());
            int autonomia = nivelCombustivel * selecionado.getConsumo();

            if (autonomia < distanciaViagem) {
                double combustivelNecessario = distanciaViagem / selecionado.getConsumo();
                int litrosFaltando = (int) Math.ceil(combustivelNecessario - nivelCombustivel);
                textField.setText("Combustível insuficiente para a viagem!\n" +
                        "Você precisará abastecer pelo menos " + litrosFaltando + "L para completar a viagem.");
            } else {
                textField.setText("Combustível suficiente para a viagem!");
            }
        } else {
            textField.setText("<--- Selecione um carro primeiro!");
        }
    }

    @FXML
    void setBtnAbastecer() {
        Carro selecionado = comboChoice.getValue();
        if (selecionado != null) {
            int nivelCombustivel = selecionado.getNivelCombustivel();
            int combustivelAbastecer = parseInt(input2.getText());
            int novoNivelCombustivel = nivelCombustivel + combustivelAbastecer;

            if (novoNivelCombustivel > selecionado.getCapacidadeTanque()) {
                textField.setText("Não é possível abastecer mais do que a capacidade do tanque!");
            } else {
                selecionado.setNivelCombustivel(novoNivelCombustivel);
                textField.setText("Abastecido com sucesso! Novo nível de combustível: " + novoNivelCombustivel + "L");
            }
        } else {
            textField.setText("<--- Selecione um carro primeiro!");
        }
    }

    @FXML
    void exibirInfo() {
        Carro selecionado = comboChoice.getValue();
        if (selecionado != null) {
            String info = "Marca: " + selecionado.getMarca() + "\n" +
                    "Modelo: " + selecionado.getModelo() + "\n" +
                    "Km Rodado: " + selecionado.getKmRodado() + "\n" +
                    "Consumo: " + selecionado.getConsumo() + "Km/L" + "\n" +
                    "Capacidade do Tanque: " + selecionado.getCapacidadeTanque() + "L" + "\n" +
                    "Nível de Combustível: " + selecionado.getNivelCombustivel() + "L";

            textField.setText(info);
        } else {
            textField.setText("<--- Selecione um carro primeiro!");
        }
    }

    @FXML
    void sair() {
        System.exit(0);

    }

    @FXML
    void setBtnViajar() {
        Carro selecionado = comboChoice.getValue();
        if (selecionado != null) {
            if (selecionado.getNivelCombustivel() <= 0) {
                textField.setText("Nível de combustível zerado! Abasteça antes de viajar.");
                return;
            }

            int kmViajado = parseInt(input1.getText());
            int nivelAtual = selecionado.getNivelCombustivel();
            int consumo = selecionado.getConsumo();

            int autonomiaAtual = nivelAtual * consumo;

            if (autonomiaAtual < kmViajado) {
                textField.setText("Combustível insuficiente para a viagem!\n" +
                        "Você precisará abastecer antes de viajar.\n" +
                        "Você pode rodar até " + autonomiaAtual + " km com o combustível atual.");
                return;
            }
            int litrosConsumidos = kmViajado / consumo;
            int novoNivelCombustivel = nivelAtual - litrosConsumidos;
            int novoKmRodado = selecionado.getKmRodado() + kmViajado;

            selecionado.setNivelCombustivel(novoNivelCombustivel);
            selecionado.setKmRodado(novoKmRodado);


            textField.setText(
                    "Viagem concluída!\n" +
                            "Km viajado: " + kmViajado + " km\n" +
                            "Novo km rodado: " + novoKmRodado + " km\n" +
                            "Nível de combustível atual: " + novoNivelCombustivel + "L"
            );
        } else {
        textField.setText("<--- Selecione um carro primeiro!");
        }
    }

    @FXML
    void exibirAutonomia() {
        Carro selecionado = comboChoice.getValue();
        if (selecionado != null) {
            int nivelCombustivel = selecionado.getNivelCombustivel();
            int autonomiaAtual = nivelCombustivel * selecionado.getConsumo();
            int autonomiaTotal = selecionado.getCapacidadeTanque()* selecionado.getConsumo();
            textField.setText("A autonomia do carro é: " + autonomiaAtual + " Km com a quantidade de combustível atual."
                    + '\n' + "A autonomia total com um taque completo é de " + autonomiaTotal + " Km.");
        } else {
            textField.setText("<--- Selecione um carro primeiro!");
        }
    }
}
