package com.myproject.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.myproject.dao.MotoristaDAO;
import com.myproject.model.Motorista;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CrudMotoristaController implements ControladorFilho<CarroController> {
    private CarroController carroController;

    @Override
    public void setControladorPai(CarroController carroController) {
        this.carroController = carroController;
    }

    @FXML
    void voltar() {
        if (carroController != null) {
            carroController.carregarTela("/view/MainMotoristas.fxml");
        } else {
            System.out.println("CarroController não está definido.");
        }
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnUpImg;

    @FXML
    private TextField txtCategoriaCnh;

    @FXML
    private DatePicker dpValidade;

    @FXML
    private TextField txtNum;

    @FXML
    private Button btnCadastrar;

    @FXML
    private TextField txtCidade;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtRua;

    @FXML
    private Button btnLimpar;

    @FXML
    private TextField txtCnh;

    @FXML
    void initialize() {
        assert btnUpImg != null : "fx:id=\"btnUpImg\" was not injected: check your FXML file 'CrudMotorista.fxml'.";
        assert txtCategoriaCnh != null : "fx:id=\"txtCategoriaCnh\" was not injected: check your FXML file 'CrudMotorista.fxml'.";
        assert dpValidade != null : "fx:id=\"dpValidade\" was not injected: check your FXML file 'CrudMotorista.fxml'.";
        assert txtNum != null : "fx:id=\"txtNum\" was not injected: check your FXML file 'CrudMotorista.fxml'.";
        assert btnCadastrar != null : "fx:id=\"btnCadastrar\" was not injected: check your FXML file 'CrudMotorista.fxml'.";
        assert txtCidade != null : "fx:id=\"txtCidade\" was not injected: check your FXML file 'CrudMotorista.fxml'.";
        assert txtTelefone != null : "fx:id=\"txtTelefone\" was not injected: check your FXML file 'CrudMotorista.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'CrudMotorista.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'CrudMotorista.fxml'.";
        assert txtRua != null : "fx:id=\"txtRua\" was not injected: check your FXML file 'CrudMotorista.fxml'.";
        assert btnLimpar != null : "fx:id=\"btnLimpar\" was not injected: check your FXML file 'CrudMotorista.fxml'.";
        assert txtCnh != null : "fx:id=\"txtCnh\" was not injected: check your FXML file 'CrudMotorista.fxml'.";

    }

    @FXML
    private void limparCampos() {
        txtNome.clear();
        txtCnh.clear();
        dpValidade.setValue(null);
        txtCategoriaCnh.clear();
        txtRua.clear();
        txtNum.clear();
        txtCidade.clear();
        txtTelefone.clear();
        txtEmail.clear();
    }

    @FXML
    private void onCadastrarMotorista() {
        String nome = txtNome.getText();
        String cnh = txtCnh.getText();
        LocalDate validade = dpValidade.getValue();
        String categoriaCnh = txtCategoriaCnh.getText();
        String rua = txtRua.getText();
        String numero = txtNum.getText();
        String cidade = txtCidade.getText();
        String telefone = txtTelefone.getText();
        String email = txtEmail.getText();

        if(nome.isEmpty() || cnh.isEmpty() || validade == null || categoriaCnh.isEmpty() ||
            rua.isEmpty() || numero.isEmpty() || cidade.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            System.out.println("Preencha todos os campos.");
            return;
        }

        try {
            Motorista motorista = new Motorista(nome, cnh, validade, categoriaCnh, rua, numero, cidade, telefone, email);
            MotoristaDAO motoristaDAO = new MotoristaDAO();
            motoristaDAO.cadastrarMotorista(motorista);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro");
            alert.setHeaderText(null);
            alert.setContentText("Carro cadastrado com sucesso!");
            alert.showAndWait();
            System.out.println("Motorista cadastrado com sucesso!");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao cadastrar veículo");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            System.err.println("Erro ao cadastrar motorista: " + e.getMessage());
        }

        limparCampos();
    }
}
