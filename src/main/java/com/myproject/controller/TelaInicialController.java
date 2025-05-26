package com.myproject.controller;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TelaInicialController {

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
    private Button btnBicicletaClass;


    @FXML
    private Button btnAnimalClass;

    @FXML
    private Button btnFechar;

    @FXML
    private Button btnCarroClass;

    @FXML
    private Button btnAlunoClass;

    @FXML
    private Button btnArvoreClass;

    @FXML
    void initialize() {
        assert btnBicicletaClass != null : "fx:id=\"btnBicicletaClass\" was not injected: check your FXML file 'TelaInicial.fxml'.";
        assert btnAnimalClass != null : "fx:id=\"btnAnimalClass\" was not injected: check your FXML file 'TelaInicial.fxml'.";
        assert btnFechar != null : "fx:id=\"btnFechar\" was not injected: check your FXML file 'TelaInicial.fxml'.";
        assert btnCarroClass != null : "fx:id=\"btnCarroClass\" was not injected: check your FXML file 'TelaInicial.fxml'.";
        assert btnAlunoClass != null : "fx:id=\"btnAlunoClass\" was not injected: check your FXML file 'TelaInicial.fxml'.";
        assert btnArvoreClass != null : "fx:id=\"btnArvoreClass\" was not injected: check your FXML file 'TelaInicial.fxml'.";

    }

    @FXML
    void openBicicleta() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Bicicleta.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void openAluno() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Aluno.fxml"));
        Parent root = loader.load();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();

        Stage atual = (Stage) btnAlunoClass.getScene().getWindow();
        atual.close();

        newStage.setOnHidden(e -> {
            try {
                FXMLLoader loaderInicio = new FXMLLoader(getClass().getResource("/view/TelaInicial.fxml"));
                Parent rootInicio = loaderInicio.load();

                Stage stageInicio = new Stage();
                stageInicio.setTitle("Tela Inicial");
                stageInicio.setScene(new Scene(rootInicio));
                stageInicio.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    @FXML
    void openAnimal() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Animal.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void openArvore() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Arvore.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void openCarro() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Carro.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
