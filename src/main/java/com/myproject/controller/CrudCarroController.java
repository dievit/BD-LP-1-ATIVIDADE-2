package com.myproject.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CrudCarroController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnUpImg;

    @FXML
    private TextField txtPlaca;

    @FXML
    private TextField txtTanque;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtConsumo;

    @FXML
    private TextField txtTipo;

    @FXML
    private TextField txtKm;

    @FXML
    private TextField txtMarca;

    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnLimpar;

    @FXML
    void initialize() {
        assert txtPlaca != null : "fx:id=\"txtPlaca\" was not injected: check your FXML file 'CrudCarro.fxml'.";
        assert txtTanque != null : "fx:id=\"txtTanque\" was not injected: check your FXML file 'CrudCarro.fxml'.";
        assert txtModelo != null : "fx:id=\"txtModelo\" was not injected: check your FXML file 'CrudCarro.fxml'.";
        assert txtConsumo != null : "fx:id=\"txtConsumo\" was not injected: check your FXML file 'CrudCarro.fxml'.";
        assert txtTipo != null : "fx:id=\"txtTipo\" was not injected: check your FXML file 'CrudCarro.fxml'.";
        assert txtKm != null : "fx:id=\"txtKm\" was not injected: check your FXML file 'CrudCarro.fxml'.";
        assert txtMarca != null : "fx:id=\"txtMarca\" was not injected: check your FXML file 'CrudCarro.fxml'.";
        assert btnUpImg != null : "fx:id=\"btnUpImg\" was not injected: check your FXML file 'CrudCarro.fxml'.";
        assert btnSubmit != null : "fx:id=\"btnSubmit\" was not injected: check your FXML file 'CrudCarro.fxml'.";
        assert btnLimpar != null : "fx:id=\"btnLimpar\" was not injected: check your FXML file 'CrudCarro.fxml'.";

    }


}
