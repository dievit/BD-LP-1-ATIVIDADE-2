package com.myproject.controller;

import java.time.LocalDate;

import com.myproject.dao.MotoristaDAO;
import com.myproject.model.Motorista;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

        aplicarMascaraTelefone(txtTelefone);
        configurarMascaras();
    }

    private void configurarMascaras() {
        txtCnh.setTextFormatter(new TextFormatter<>(change -> {
            return (change.getControlNewText().length() <= 11 && change.getText().matches("\\d*")) ? change : null;
        }));

        txtTelefone.setTextFormatter(new TextFormatter<>(change -> {
            return (change.getControlNewText().length() <= 11 && change.getText().matches("\\d*")) ? change : null;
        }));
    }

    private void aplicarMascaraTelefone(TextField campo) {
        campo.textProperty().addListener((obs, oldText, newText) -> {
            String digits = newText.replaceAll("[^\\d]", ""); // Remove tudo que não é número

            if (digits.length() > 11) {
                digits = digits.substring(0, 11); // Limita a 11 dígitos
            }

            StringBuilder formatted = new StringBuilder();
            int len = digits.length();

            if (len >= 1) formatted.append("(").append(digits.substring(0, Math.min(2, len)));
            if (len >= 3) formatted.append(") ").append(digits.substring(2, Math.min(7, len)));
            if (len >= 8) formatted.append("-").append(digits.substring(7));

            campo.setText(formatted.toString());
            campo.positionCaret(formatted.length()); // Mantém o cursor no final
        });
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
            boolean sucesso = motoristaDAO.cadastrarMotorista(motorista);
            motoristaDAO.cadastrarMotorista(motorista);

            if (sucesso) {
                limparCampos();
                if (carroController != null) {
                    carroController.carregarTela("/view/MainMotoristas.fxml");
                }
            }

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


    }
}
