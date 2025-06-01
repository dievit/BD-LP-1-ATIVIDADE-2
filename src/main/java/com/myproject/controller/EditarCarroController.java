package com.myproject.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.myproject.dao.CarroDAO;
import com.myproject.model.Carro;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditarCarroController {

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
    private Button btnCadastrar;

    @FXML
    private TextField txtKm;

    @FXML
    private TextField txtMarca;

    @FXML
    private Button btnLimpar;

    @FXML
    private TextField txtTipo;

    @FXML
    void initialize() {
        assert btnUpImg != null : "fx:id=\"btnUpImg\" was not injected: check your FXML file 'EditarCarro.fxml'.";
        assert txtPlaca != null : "fx:id=\"txtPlaca\" was not injected: check your FXML file 'EditarCarro.fxml'.";
        assert txtTanque != null : "fx:id=\"txtTanque\" was not injected: check your FXML file 'EditarCarro.fxml'.";
        assert txtModelo != null : "fx:id=\"txtModelo\" was not injected: check your FXML file 'EditarCarro.fxml'.";
        assert txtConsumo != null : "fx:id=\"txtConsumo\" was not injected: check your FXML file 'EditarCarro.fxml'.";
        assert btnCadastrar != null : "fx:id=\"btnCadastrar\" was not injected: check your FXML file 'EditarCarro.fxml'.";
        assert txtTipo != null : "fx:id=\"txtTipo\" was not injected: check your FXML file 'EditarCarro.fxml'.";
        assert txtKm != null : "fx:id=\"txtKm\" was not injected: check your FXML file 'EditarCarro.fxml'.";
        assert txtMarca != null : "fx:id=\"txtMarca\" was not injected: check your FXML file 'EditarCarro.fxml'.";
        assert btnLimpar != null : "fx:id=\"btnLimpar\" was not injected: check your FXML file 'EditarCarro.fxml'.";

    }

    @FXML
    private void limparCampos() {
        txtPlaca.clear();
        txtTanque.clear();
        txtModelo.clear();
        txtConsumo.clear();
        txtKm.clear();
        txtMarca.clear();
    }

    @FXML
    private void onEditarCarro() {
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        String placa = txtPlaca.getText();
        String consumo = txtConsumo.getText();
        String tipo = txtTipo.getText();
        String capacidadeTanque = txtTanque.getText();

        if (marca.isEmpty() || modelo.isEmpty() || tipo.isEmpty() || consumo.isEmpty() || capacidadeTanque.isEmpty()) {
            System.out.println("Todos os campos devem ser preenchidos.");
            return;
        }

        try {
            Carro carroAtualizado = new Carro(
                    modelo,
                    marca,
                    tipo,
                    Integer.parseInt(capacidadeTanque),
                    Double.parseDouble(consumo)
            );
            carroAtualizado.setPlaca(placa);

//            CarroDAO dao = new CarroDAO();
            boolean sucesso = CarroDAO.atualizarCarro(carroAtualizado);

            if (sucesso) {
                System.out.println("Carro atualizado com sucesso!");
            } else {
                System.out.println("Erro ao atualizar o carro.");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
