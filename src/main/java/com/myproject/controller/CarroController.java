package com.myproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class CarroController implements ControladorFilho<FrotaController> {

    //ATENCAO O CARROCONTROLLER ESTA RESPONSAVEL POR CARREGAR AS TELAS DO MENU PRINCIPAL
    //E NÃO DEVE SER CONFUNDIDO COM O CRUD DO CARRO

    private CarroController carroController;

    @Override
    public void setControladorPai(CarroController controller) {
        this.carroController = controller;
    }

    @FXML
    private void voltar() {
        carroController.carregarTela("frota.fxml");
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

            if(controller instanceof ControladorFilho) {
                ((ControladorFilho<?>) controller).setControladorPai(this);
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
//        Platform.runLater(() -> {
//            if(menuPane != null && splitPane != null) {
//                SplitPane.setResizableWithParent(menuPane, false);
//            } else {
//                System.err.println("MenuPane or SplitPane is null. Check your FXML file.");
//            }
//        });
    }


}
