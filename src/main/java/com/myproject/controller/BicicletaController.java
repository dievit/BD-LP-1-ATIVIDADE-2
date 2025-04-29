package com.myproject.controller;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.myproject.model.Bicicleta;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class BicicletaController {
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
    private TextArea infoDisplay;

    @FXML
    private Button calibrarPneu;

    @FXML
    private ChoiceBox<Bicicleta> choiceBox;

    @FXML
    private Button btnVoltar;

    @FXML
    private ImageView imageField;

    @FXML
    void initialize() {
        Bicicleta trekDomane = new Bicicleta("Trek", "Domane SL6", 28, 8, "Speed", "Preto/Vermelho", 22, 90, 100, "/images/trek_domane.png");
        Bicicleta specializedRockhopper = new Bicicleta("Specialized", "Rockhopper", 29, 13, "Mountain Bike", "Azul", 18, 85, 95, "/images/specialized_rock_hopper.png");
        Bicicleta cannondaleTopstone = new Bicicleta("Cannondale", "Topstone", 27, 9, "Gravel", "Cinza", 22, 88, 95, "/images/cannondale_top_stone.png");
        Bicicleta scottAspect950 = new Bicicleta("Scott", "Aspect 950", 29, 14, "Mountain Bike", "Preto/Verde", 18, 87, 95, "/images/scott_aspect_950.png");
        Bicicleta caloiEliteCarbon = new Bicicleta("Caloi", "Elite Carbon", 29, 11, "Mountain Bike", "Vermelho", 22, 89, 100, "/images/caloi_elite_carbon.png");
        Bicicleta soulSL929 = new Bicicleta("Soul", "SL929", 29, 10, "Mountain Bike", "Branco/Azul", 20, 85, 100, "/images/Soul_SL929.png");
        Bicicleta senseImpactCarbon = new Bicicleta("Sense", "Impact Carbon", 29, 12, "Mountain Bike", "Preto/Branco", 20, 90, 100, "/images/sense_impact_carbon.png");
        Bicicleta gtAvalancheElite = new Bicicleta("GT", "Avalanche Elite", 29, 13, "Mountain Bike", "Laranja", 18, 85, 95, "/images/gt_avalanche_elite.png");
        Bicicleta oggiBigWheel7 = new Bicicleta("Oggi", "Big Wheel 7.0", 29, 12, "Mountain Bike", "Verde", 20, 90, 100, "/images/oggi_big_wheel.png");
        Bicicleta absoluteWild = new Bicicleta("Absolute", "Wild", 29, 14, "Mountain Bike", "Azul Claro", 21, 85, 95, "/images/absolute_wild.png");

        choiceBox.getItems().addAll(trekDomane, specializedRockhopper, cannondaleTopstone,
                scottAspect950, caloiEliteCarbon, soulSL929, senseImpactCarbon,
                gtAvalancheElite, oggiBigWheel7, absoluteWild);

        choiceBox.setOnAction(event -> {
            Bicicleta selecionada = choiceBox.getValue();

            if (selecionada != null) {
                int calibragemAtual = (selecionada.getCalibragemAtual());
                int calibragemIdeal = (selecionada.getCalibragemIdeal());

                String infoCalibragem;
                if (calibragemAtual > calibragemIdeal) {
                    infoCalibragem = "A calibragem do pneu está alta!";
                } else if (calibragemAtual < calibragemIdeal) {
                    infoCalibragem = "A calibragem do pneu está baixa!";
                } else {
                    infoCalibragem = "A calibragem do pneu está correta!";
                }


                String info = "Bicicleta: " + selecionada.getModelo() +
                        "\nMarca: " + selecionada.getMarca() +
                        "\nAro: " + selecionada.getAro() +
                        "\nPeso: " + selecionada.getPeso() +
                        "\nTipo: " + selecionada.getTipo() +
                        "\nCor: " + selecionada.getCor() +
                        "\nQuantidade de Marchas: " + selecionada.getQtdMarcha() +
                        "\nCalibragem Atual: " + selecionada.getCalibragemAtual() +
                        "\nCalibragem Ideal: " + selecionada.getCalibragemIdeal();


                infoDisplay.setText(info + "\n" + infoCalibragem);

                Image image1 = new Image(getClass().getResourceAsStream(selecionada.getImagem()));
                imageField.setImage(image1);
            }

        });

    }
    @FXML
    void calibrarPneu() {
        Bicicleta selecionada = choiceBox.getValue();

        if (selecionada !=null) {
            selecionada.setCalibragemAtual(selecionada.getCalibragemIdeal());

            infoDisplay.setText("Pneu calibrado com sucesso!\n" +
                    "Calibragem Atual: " + selecionada.getCalibragemAtual() +
                    "\nCalibragem Ideal: " + selecionada.getCalibragemIdeal());
        }
    }

    }



