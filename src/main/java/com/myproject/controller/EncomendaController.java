package com.myproject.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.myproject.model.Encomenda;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EncomendaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfCodRastreio;

    @FXML
    private TextField tfRemetente;

    @FXML
    private TextField tfEndereco;

    @FXML
    private Button btnConfirmarEntrega;

    @FXML
    private TextArea taRastreio;

    @FXML
    private DatePicker dpDataEnvio;

    @FXML
    private TextField tfPeso;

    @FXML
    private Button btnSairRas;

    @FXML
    private Button btnSairCad;

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField tfCodRas;

    @FXML
    private TextField tfDestinatario;

    @FXML
    private Button btnBuscar;

    @FXML
    private DatePicker dpConfirmar;

    @FXML
    private List<Encomenda> listaEncomendas = new ArrayList<>();

    @FXML
    private Encomenda encomenda;

    @FXML
    public void setEncomenda(Encomenda encomenda) {
        this.encomenda = encomenda;
    }

    @FXML
    void initialize() {
        assert tfCodRastreio != null : "fx:id=\"tfCodRastreio\" was not injected: check your FXML file 'Encomenda.fxml'.";
        assert tfRemetente != null : "fx:id=\"tfRemetente\" was not injected: check your FXML file 'Encomenda.fxml'.";
        assert tfEndereco != null : "fx:id=\"tfEndereco\" was not injected: check your FXML file 'Encomenda.fxml'.";
        assert btnConfirmarEntrega != null : "fx:id=\"btnConfirmarEntrega\" was not injected: check your FXML file 'Encomenda.fxml'.";
        assert taRastreio != null : "fx:id=\"taRastreio\" was not injected: check your FXML file 'Encomenda.fxml'.";
        assert dpDataEnvio != null : "fx:id=\"dpDataEnvio\" was not injected: check your FXML file 'Encomenda.fxml'.";
        assert tfPeso != null : "fx:id=\"tfPeso\" was not injected: check your FXML file 'Encomenda.fxml'.";
        assert btnSairRas != null : "fx:id=\"btnSairRas\" was not injected: check your FXML file 'Encomenda.fxml'.";
        assert btnSairCad != null : "fx:id=\"btnSairCad\" was not injected: check your FXML file 'Encomenda.fxml'.";
        assert btnSubmit != null : "fx:id=\"btnSubmit\" was not injected: check your FXML file 'Encomenda.fxml'.";
        assert tfCodRas != null : "fx:id=\"tfCodRas\" was not injected: check your FXML file 'Encomenda.fxml'.";
        assert tfDestinatario != null : "fx:id=\"tfDestinatario\" was not injected: check your FXML file 'Encomenda.fxml'.";
        assert btnBuscar != null : "fx:id=\"btnBuscar\" was not injected: check your FXML file 'Encomenda.fxml'.";
        assert dpConfirmar != null : "fx:id=\"dpConfirmar\" was not injected: check your FXML file 'Encomenda.fxml'.";

    }
    @FXML
    public static Encomenda encomenda(String codRastreio, String remetente, String destinatario,
            String enderecoDestino, int peso, String status, String dataEnvio, String dataEntrega) {
        return new Encomenda(codRastreio, remetente, destinatario, enderecoDestino, peso, status, dataEnvio,
                dataEntrega);
    }

    @FXML
    public void confirmarEntrega() {
        if ("Em rota de entrega".equals(encomenda.getStatus())) {
            LocalDate dataSelecionada = dpConfirmar.getValue(); // Corrigido aqui
            if (dataSelecionada != null) {
                encomenda.setDataEntrega(dataSelecionada.toString());
                encomenda.setStatus("Entregue");
                System.out.println("Entrega confirmada");
            } else {
                taRastreio.setText("Selecione uma data de entrega.");
            }
        } else {
            taRastreio.setText("A encomenda ainda não está em rota de entrega.");
        }
    }


}
