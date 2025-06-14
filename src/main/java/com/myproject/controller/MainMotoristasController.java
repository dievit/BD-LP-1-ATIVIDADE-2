package com.myproject.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.myproject.dao.MotoristaDAO;
import com.myproject.model.Motorista;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class MainMotoristasController implements ControladorFilho<CarroController> {
    private CarroController carroController;

    @Override
    public void setControladorPai(CarroController carroController) {
        this.carroController = carroController;
    }

    @FXML
    void abrirCadastroMotorista() {
        carroController.carregarTela("/view/CrudMotorista.fxml");
    }

    @FXML
    void abrirEditarMotorista() {
        carroController.carregarTela("/view/EditarMotorista.fxml");
    }

    @FXML
    void voltar() {
        if (carroController != null) {
            carroController.carregarTela("/view/Main.fxml");
        } else {
            System.out.println("CarroController não está definido.");
        }
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCadastroMotorista;

    @FXML
    private TableColumn<Motorista, String> colNome;

    @FXML
    private TableColumn<Motorista, String> colTelefone;

    @FXML
    private Button btnMotoristas;

    @FXML
    private TableColumn<Motorista, String> colCNH;

    @FXML
    private TableColumn<Motorista, String> colDisponibilidade;

    @FXML
    private TableColumn<Motorista, Integer> colId;

    @FXML
    private TableColumn<Motorista, String> colStatusCNH;

    @FXML
    private Button btnEditarMotorista;

    @FXML
    private TableView<Motorista> tabelaMotoristas;

    @FXML
    private AnchorPane conteudoPane;


    @FXML
    void initialize() {
        tabelaMotoristas.setRowFactory(tv -> {
            TableRow<Motorista> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Motorista motorista = row.getItem();
                    abrirTelaEdicao(motorista);
                }
            });
            return row;
        });

        assert btnMotoristas != null : "fx:id=\"btnMotoristas\" was not injected: check your FXML file 'MainMotoristas.fxml'.";
        assert btnCadastroMotorista != null : "fx:id=\"btnCadastroMotorista\" was not injected: check your FXML file 'MainMotoristas.fxml'.";
        assert btnEditarMotorista != null : "fx:id=\"btnEditarMotorista\" was not injected: check your FXML file 'MainMotoristas.fxml'.";

        assert colNome != null : "fx:id=\"colNome\" was not injected: check your FXML file 'MainMotoristas.fxml'.";
        assert colTelefone != null : "fx:id=\"colTelefone\" was not injected: check your FXML file 'MainMotoristas.fxml'.";
        assert colCNH != null : "fx:id=\"colCNH\" was not injected: check your FXML file 'MainMotoristas.fxml'.";
        assert colDisponibilidade != null : "fx:id=\"colDisponibilidade\" was not injected: check your FXML file 'MainMotoristas.fxml'.";
        assert colId != null : "fx:id=\"colId\" was not injected: check your FXML file 'MainMotoristas.fxml'.";
        assert colStatusCNH != null : "fx:id=\"colStatusCNH\" was not injected: check your FXML file 'MainMotoristas.fxml'.";
        assert tabelaMotoristas != null : "fx:id=\"tabelaMotoristas\" was not injected: check your FXML file 'MainMotoristas.fxml'.";

        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colCNH.setCellValueFactory(new PropertyValueFactory<>("cnh"));
        colDisponibilidade.setCellValueFactory(new PropertyValueFactory<>("disponibilidade"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colStatusCNH.setCellValueFactory(new PropertyValueFactory<>("statusCNH"));

        btnMotoristas.setOnAction(event -> carregarListaMotoristas());

    }

    public void carregarListaMotoristas() {
        List<Motorista> motoristasDisponiveis = MotoristaDAO.listarMotoristas();
        System.out.println("Motoristas disponíveis: " + motoristasDisponiveis.size());
        motoristasDisponiveis.forEach(c -> System.out.println(c.getNome() + " - " + c.getDisponibilidade()));

        ObservableList<Motorista> observableMotoristas = FXCollections.observableArrayList(motoristasDisponiveis);
        tabelaMotoristas.setItems(observableMotoristas);
    }


    private void abrirTelaEdicao(Motorista motorista) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditarMotorista.fxml"));
            AnchorPane novaTela = loader.load();

            EditarMotoristaController controller = loader.getController();
            controller.setControladorPai(carroController);
            controller.preencherCampos(motorista);

            conteudoPane.getChildren().setAll(novaTela);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}