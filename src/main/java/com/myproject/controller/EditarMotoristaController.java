package com.myproject.controller;

import com.myproject.model.Motorista;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import com.myproject.dao.MotoristaDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class EditarMotoristaController implements ControladorFilho<CarroController> {
    private CarroController carroController;

    @Override
    public void setControladorPai(CarroController controller) {
        this.carroController = controller;
    }


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtCategoriaCnh;

    @FXML
    private TextField txtValidade;

    @FXML
    private Button btnCadastrar;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtRua;

    @FXML
    private TextField txtCnh;

    @FXML
    private Button btnUpImg;

    @FXML
    private TextField txtNum;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField txtCidade;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnBuscar;

    @FXML
    void voltar(ActionEvent event) {
        if (carroController != null) {
            carroController.carregarTela("/view/MainMotoristas.fxml");
        }
    }

    @FXML
    void initialize() {
        assert txtCategoriaCnh != null : "fx:id=\"txtCategoriaCnh\" was not injected: check your FXML file 'EditarMotorista.fxml'.";
        assert txtValidade != null : "fx:id=\"txtValidade\" was not injected: check your FXML file 'EditarMotorista.fxml'.";
        assert btnCadastrar != null : "fx:id=\"btnCadastrar\" was not injected: check your FXML file 'EditarMotorista.fxml'.";
        assert txtTelefone != null : "fx:id=\"txtTelefone\" was not injected: check your FXML file 'EditarMotorista.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'EditarMotorista.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'EditarMotorista.fxml'.";
        assert txtRua != null : "fx:id=\"txtRua\" was not injected: check your FXML file 'EditarMotorista.fxml'.";
        assert txtCnh != null : "fx:id=\"txtCnh\" was not injected: check your FXML file 'EditarMotorista.fxml'.";
        assert btnUpImg != null : "fx:id=\"btnUpImg\" was not injected: check your FXML file 'EditarMotorista.fxml'.";
        assert txtNum != null : "fx:id=\"txtNum\" was not injected: check your FXML file 'EditarMotorista.fxml'.";
        assert btnVoltar != null : "fx:id=\"btnVoltar\" was not injected: check your FXML file 'EditarMotorista.fxml'.";
        assert txtCidade != null : "fx:id=\"txtCidade\" was not injected: check your FXML file 'EditarMotorista.fxml'.";
        assert btnLimpar != null : "fx:id=\"btnLimpar\" was not injected: check your FXML file 'EditarMotorista.fxml'.";
        assert btnBuscar != null : "fx:id=\"btnBuscar\" was not injected: check your FXML file 'EditarMotorista.fxml'.";

    }

    @FXML
    private void limparCampos() {
        txtCategoriaCnh.clear();
        txtValidade.clear();
        txtTelefone.clear();
        txtEmail.clear();
        txtNome.clear();
        txtRua.clear();
        txtCnh.clear();
        txtNum.clear();
        txtCidade.clear();
    }

    @FXML
    private void buscarMotorista() {
        String cnh = txtCnh.getText();

        if (cnh.isEmpty()) {
            System.out.println("Por favor, insira o número da CNH para buscar.");
            return;
        }

        Motorista motorista = MotoristaDAO.buscarMotoristaPorCNH(cnh);
    }
}




