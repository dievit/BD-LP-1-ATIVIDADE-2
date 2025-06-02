package com.myproject.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.myproject.dao.CarroDAO;
import com.myproject.model.Carro;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CrudCarroController implements ControladorFilho<CarroController>{
    private CarroController carroController;

    @Override
    public void setControladorPai(CarroController controller) {
        this.carroController = controller;
    }

    @FXML
    private void voltar() {
        if (carroController != null) {
            carroController.carregarTela("/view/Frota.fxml");
        }
    }


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
    private Button btnVoltar;

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
    @FXML
    private void limparCampos() {
        txtPlaca.clear();
        txtTanque.clear();
        txtModelo.clear();
        txtConsumo.clear();
        txtTipo.clear();
        txtKm.clear();
        txtMarca.clear();
    }

    @FXML
    private void onCadastrarCarro() {
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        String placa = txtPlaca.getText();
        String tipo = txtTipo.getText();
        int kmRodado = Integer.parseInt(txtKm.getText());
        double consumo = Integer.parseInt(txtConsumo.getText());
        int capacidadeTanque = Integer.parseInt(txtTanque.getText());

        if(marca.isEmpty() || modelo.isEmpty() || placa.isEmpty() || tipo.isEmpty() || txtKm.getText().isEmpty() || txtConsumo.getText().isEmpty() || txtTanque.getText().isEmpty()) {
            System.out.println("Todos os campos devem ser preenchidos.");
            return;
        }
        try {
            Carro carro = new Carro(modelo, marca, placa, tipo, (int)kmRodado, (double)consumo, (int)capacidadeTanque);
            CarroDAO carroDAO = new CarroDAO();
            carroDAO.cadastrarCarro(carro);
            System.out.println("Carro cadastrado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar carro: " + e.getMessage());
        }
        limparCampos();
    }


}
