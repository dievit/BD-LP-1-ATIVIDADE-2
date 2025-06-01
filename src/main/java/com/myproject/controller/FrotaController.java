package com.myproject.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.myproject.dao.CarroDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.myproject.model.Carro;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FrotaController implements ControladorFilho{
    private CarroController carroController;

    @Override
    public void setCarroController(CarroController carroController) {
        this.carroController = carroController;
    }

    @FXML
    void abrirCadastroCarro() {
        carroController.carregarTela("/view/CrudCarro.fxml");
    }

    @FXML
    void abrirEditarCarro() {
        carroController.carregarTela("/view/CrudCarro.fxml");
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;



    @FXML
    private Button btnFrotaEmRota;

    @FXML
    private TableColumn<Carro, Integer> colId;

    @FXML
    private TableColumn<Carro, String> colModelo;

    @FXML
    private TableColumn<Carro, String> colPlaca;

    @FXML
    private TableColumn<Carro, String> colStatus;

    @FXML
    private TableColumn<Carro, String> colMarca;

    @FXML
    private TableColumn<Carro, String> colTipo;

    @FXML
    private Button btnFrotaManutencao;

    @FXML
    private TableView<Carro> tabelaFrota;

    @FXML
    private Button btnFrotaDisponiveis;

    @FXML
    void initialize() {
        assert btnFrotaDisponiveis != null : "fx:id=\"btnFrotaDisponiveis\" was not injected: check your FXML file 'Frota.fxml'.";
        assert btnFrotaEmRota != null : "fx:id=\"btnFrotaEmRota\" was not injected: check your FXML file 'Frota.fxml'.";
        assert btnFrotaManutencao != null : "fx:id=\"btnFrotaManutencao\" was not injected: check your FXML file 'Frota.fxml'.";


        assert colId != null : "fx:id=\"colId\" was not injected: check your FXML file 'Frota.fxml'.";
        assert colMarca != null : "fx:id=\"colMarca\" was not injected: check your FXML file 'Frota.fxml'.";
        assert colModelo != null : "fx:id=\"colModelo\" was not injected: check your FXML file 'Frota.fxml'.";
        assert colPlaca != null : "fx:id=\"colPlaca\" was not injected: check your FXML file 'Frota.fxml'.";
        assert colTipo != null : "fx:id=\"colTipo\" was not injected: check your FXML file 'Frota.fxml'.";
        assert colStatus != null : "fx:id=\"colStatus\" was not injected: check your FXML file 'Frota.fxml'.";

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        btnFrotaDisponiveis.setOnAction(event -> {


            List<Carro> carrosDisponiveis = CarroDAO.listarCarrosDisponiveis();
            System.out.println("Carros disponíveis: " + carrosDisponiveis.size());
            carrosDisponiveis.forEach(c -> System.out.println(c.getModelo() + " - " + c.getStatus()));

            ObservableList<Carro> observableCarros = FXCollections.observableArrayList(carrosDisponiveis);
            tabelaFrota.setItems(observableCarros);

        });
    }
}
