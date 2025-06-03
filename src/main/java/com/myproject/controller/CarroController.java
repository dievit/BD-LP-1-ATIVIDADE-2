package com.myproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CarroController {
    private FrotaController frotaController;

    public void setControladorPai(FrotaController controller) {
        this.frotaController = controller;
    }

    @FXML
    private void voltar() {
        if (frotaController != null) {
            frotaController.carregarTela("/view/frota.fxml");
        }
    }


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
            if (controller instanceof FrotaController frota) {
                frota.setCarroController(this); // <<< Isso aqui resolve seu problema
            }

            if(controller instanceof ControladorFilho) {
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
    private void abrirManutencao() {
        carregarTela("/view/manutencao.fxml");
    }

    public AnchorPane getConteudoPane() {
        return conteudoPane;
    }

    @FXML
    void initialize() {
//        Platform.runLater(() -> {
//            if(menuPane != null && splitPane != null) {
//                SplitPane.setResizableWithParent(menuPane, false);
//            } else {
//                System.err.println("MenuPane or SplitPane is null. Check your FXML file.");
//            }
//        });
    }


}
