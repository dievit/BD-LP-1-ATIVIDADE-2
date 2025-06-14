package com.myproject.controller;

import com.myproject.dao.CarroDAO;
import com.myproject.model.Carro;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class CrudCarroController implements ControladorFilho<CarroController> {
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

        configurarMascaras();
    }

    private void configurarMascaras() {
        txtPlaca.setTextFormatter(new TextFormatter<>(change -> {
            String novoTexto = change.getControlNewText().toUpperCase();
            if (novoTexto.length() <= 7 && novoTexto.matches("[A-Z0-9]*")) {
                change.setText(change.getText().toUpperCase());
                return change;
            }
            return null;
        }));
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
        double consumo = Double.parseDouble(txtConsumo.getText());
        int capacidadeTanque = Integer.parseInt(txtTanque.getText());

        if (marca.isEmpty() || modelo.isEmpty() || placa.isEmpty() || tipo.isEmpty() || txtKm.getText().isEmpty() || txtConsumo.getText().isEmpty() || txtTanque.getText().isEmpty()) {
            System.out.println("Todos os campos devem ser preenchidos.");
            return;
        }
        if (!placa.matches("^[A-Z]{3}[0-9]{4}$")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Placa inválida");
            alert.setHeaderText(null);
            alert.setContentText("A placa deve estar no formato AAA1234, sem traços ou espaços.");
            alert.showAndWait();
            return;
        }
        try {
            Carro carro = new Carro(modelo, marca, placa, tipo, (int) kmRodado, (double) consumo, (int) capacidadeTanque);
            CarroDAO carroDAO = new CarroDAO();
            boolean sucesso = carroDAO.cadastrarCarro(carro);
            carroDAO.cadastrarCarro(carro);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro");
            alert.setHeaderText(null);
            alert.setContentText("Carro cadastrado com sucesso!");
            alert.showAndWait();

            System.out.println("Veículo cadastrado com sucesso!");

            if (sucesso) {
                limparCampos();
                if (carroController != null) {
                    carroController.carregarTela("/view/MainMotoristas.fxml");
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao cadastrar veículo");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            System.err.println("Erro ao cadastrar carro: " + e.getMessage());
        }
        limparCampos();
    }


}
