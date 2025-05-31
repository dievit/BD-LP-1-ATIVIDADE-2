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

    //ATENCAO O CARROCONTROLLER ESTA RESPONSAVEL POR CARREGAR AS TELAS DO MENU PRINCIPAL
    //E NÃO DEVE SER CONFUNDIDO COM O CRUD DO CARRO

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

    void carregarTela(String caminhoFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
            AnchorPane novaTela = loader.load();

            Object controller = loader.getController();

            if(controller instanceof ControladorFilho) {
                ((ControladorFilho) controller).setCarroController(this);
            }

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
    private void abrirManutencao() {
        carregarTela("/view/manutencao.fxml");
    }

    @FXML
    void initialize() {
        Platform.runLater(() -> {
            SplitPane.setResizableWithParent(menuPane, false);
        });
    }
}
