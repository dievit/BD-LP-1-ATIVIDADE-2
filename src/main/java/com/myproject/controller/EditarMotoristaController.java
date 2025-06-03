package com.myproject.controller;

import com.myproject.model.Motorista;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import com.myproject.dao.MotoristaDAO;

import java.net.URL;
import java.time.LocalDate;
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
    void limpar(ActionEvent event) {
        limparCampos();
    }

    @FXML
    private javafx.scene.control.DatePicker dpValidade;


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
        assert dpValidade != null : "fx:id=\"txtValidade\" was not injected: check your FXML file 'EditarMotorista.fxml'.";
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
        dpValidade.setValue(null);
        txtTelefone.clear();
        txtEmail.clear();
        txtNome.clear();
        txtRua.clear();
        txtCnh.clear();
        txtNum.clear();
        txtCidade.clear();
    }

    @FXML
    private void onBuscarMotorista() {
        String cnh = txtCnh.getText();

        if (cnh.isEmpty()) {
            System.out.println("Por favor, insira o número da CNH para buscar.");
            return;
        }

        Motorista motorista = MotoristaDAO.buscarMotoristaPorCNH(cnh);

        if (motorista != null) {
            txtNome.setText(motorista.getNome());
            txtCnh.setText(motorista.getCnh());
            dpValidade.setValue(motorista.getValidadeCNH());
            txtCategoriaCnh.setText(motorista.getCategoriaCNH());
            txtRua.setText(motorista.getEndRua());
            txtNum.setText(motorista.getEndNumero());
            txtCidade.setText(motorista.getEndCidade());
            txtTelefone.setText(motorista.getTelefone());
            txtEmail.setText(motorista.getEmail());
        } else {
            System.out.println("Motorista não encontrado com a CNH informada.");
        }
    }

    public void preencherCampos(Motorista motorista) {
        if (motorista != null) {
            txtNome.setText(motorista.getNome());
            txtCnh.setText(motorista.getCnh());
            dpValidade.setValue(motorista.getValidadeCNH());
            txtCategoriaCnh.setText(motorista.getCategoriaCNH());
            txtRua.setText(motorista.getEndRua());
            txtNum.setText(motorista.getEndNumero());
            txtCidade.setText(motorista.getEndCidade());
            txtTelefone.setText(motorista.getTelefone());
            txtEmail.setText(motorista.getEmail());
        }
    }

    @FXML
    private void onEditarMotorista() {
        String nome = txtNome.getText();
        String cnh = txtCnh.getText();
        LocalDate validade = dpValidade.getValue();
        String categoriaCNH = txtCategoriaCnh.getText();
        String endRua = txtRua.getText();
        String endNumero = txtNum.getText();
        String endCidade = txtCidade.getText();
        String telefone = txtTelefone.getText();
        String email = txtEmail.getText();

        if (nome.isEmpty() || cnh.isEmpty() || validade == null || categoriaCNH.isEmpty() || endRua.isEmpty() || endNumero.isEmpty() || endCidade.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            System.out.println("Todos os campos devem ser preenchidos.");
            return;
        }
        try {
            Motorista motoristaAtualizado = new Motorista(nome, validade, categoriaCNH, endRua, endNumero, endCidade, telefone, email

            );
            motoristaAtualizado.setCnh(cnh);

            boolean sucesso = MotoristaDAO.atualizarMotorista(motoristaAtualizado);

            if (sucesso) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText(null);
                alert.setContentText("Motorista atualizado com sucesso!");
                alert.showAndWait();
                System.out.println("Motorista atualizado com sucesso!");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Erro ao atualizar");
                alert.setContentText("Ocorreu um problema ao atualizar os dados do carro.");
                alert.showAndWait();
                System.out.println("Erro ao atualizar o carro.");
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de formato");
            alert.setHeaderText("Valores inválidos");
            alert.setContentText("Certifique-se de preencher os campos numéricos corretamente.");
            alert.showAndWait();
        }
        limparCampos();
    }
}






