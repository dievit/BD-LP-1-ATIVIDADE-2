package com.myproject.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CarroController {

    @FXML
    private AnchorPane conteudoPane;


    void carregarTela(String caminhoFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
            AnchorPane novaTela = loader.load();

            Object controller = loader.getController();
            if (controller instanceof FrotaController frota) {
                frota.setCarroController(this); // <<< Isso aqui resolve seu problema
            }

            if (controller instanceof ControladorFilho) {
                @SuppressWarnings("unchecked")
                ControladorFilho<CarroController> filho = (ControladorFilho<CarroController>) controller;
                filho.setControladorPai(this);
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
        carregarTela("/view/MainMotoristas.fxml");
    }

    @FXML
    private void abrirViagens() {
        carregarTela("/view/ViagemMain.fxml");
    }


    @FXML
    private void fecharAplicacao() {
        Platform.exit();
    }

}
