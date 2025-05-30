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

public class FrotaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnFrotaDisponiveis;

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
    private Button btnFrotaManutencao;

    @FXML
    private TableView<Carro> tabelaFrota;

    @FXML
    void initialize() {
        assert btnFrotaDisponiveis != null : "fx:id=\"btnFrotaDisponiveis\" was not injected: check your FXML file 'Frota.fxml'.";
        assert btnFrotaEmRota != null : "fx:id=\"btnFrotaEmRota\" was not injected: check your FXML file 'Frota.fxml'.";
        assert btnFrotaManutencao != null : "fx:id=\"btnFrotaManutencao\" was not injected: check your FXML file 'Frota.fxml'.";

        assert colId != null : "fx:id=\"colId\" was not injected: check your FXML file 'Frota.fxml'.";
        assert colModelo != null : "fx:id=\"colModelo\" was not injected: check your FXML file 'Frota.fxml'.";
        assert colPlaca != null : "fx:id=\"colPlaca\" was not injected: check your FXML file 'Frota.fxml'.";
        assert colStatus != null : "fx:id=\"colStatus\" was not injected: check your FXML file 'Frota.fxml'.";

        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
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
