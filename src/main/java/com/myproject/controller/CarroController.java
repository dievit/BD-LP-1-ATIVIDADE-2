package com.myproject.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CarroController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private SplitPane splitPane;

    @FXML
    private AnchorPane menuPane;

    @FXML
    private AnchorPane conteudoPane;

    private void carregarTela(String caminhoFXML) {
        try {
            AnchorPane novaTela = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(caminhoFXML)));

            AnchorPane.setTopAnchor(novaTela, 0.0);
            AnchorPane.setBottomAnchor(novaTela, 0.0);
            AnchorPane.setLeftAnchor(novaTela, 0.0);
            AnchorPane.setRightAnchor(novaTela, 0.0);
            conteudoPane.getChildren().setAll(novaTela);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirFrota() {
        carregarTela("/view/frota.fxml");
    }

    @FXML
    private void abrirMotoristas() {
        carregarTela("/view/motoristas.fxml");
    }

    @FXML
    void initialize() {
        Platform.runLater(() -> {
            SplitPane.setResizableWithParent(menuPane, false);
        });
    }

    @FXML
    private void cadastrarCarro() {
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        String placa = txtPlaca.getText();
        int kmRodado = Integer.parseInt(txtKmRodado.getText());
        int consumo = Integer.parseInt(txtConsumo.getText());
        double capacidadeTanque = Double.parseDouble(txtCapacidadeTanque.getText());

    }



}
