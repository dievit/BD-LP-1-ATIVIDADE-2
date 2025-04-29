package com.myproject.controller;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import com.myproject.model.Arvore;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;

public class ArvoreController {
    @FXML
    void voltarTelaInicial(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/TelaInicial.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void fecharAplicacao(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtOrigem;

    @FXML
    private TextArea txtDisplay;

    @FXML
    private TextField txtEspecie;

    @FXML
    private ImageView imageField;

    @FXML
    private TextField txtNome;

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField txtAltura;

    @FXML
    private TextField txtIdade;

    @FXML
    private ComboBox<Arvore> dropdown;

    @FXML
    private Button btnExibirInfo;

    @FXML
    private Button btnRegar;

    @FXML
    private Button btnPodar;

    @FXML
    void initialize() {
        assert txtOrigem != null : "fx:id=\"txtOrigem\" was not injected: check your FXML file 'Arvore.fxml'.";
        assert txtDisplay != null : "fx:id=\"txtDisplay\" was not injected: check your FXML file 'Arvore.fxml'.";
        assert txtEspecie != null : "fx:id=\"txtEspecie\" was not injected: check your FXML file 'Arvore.fxml'.";
        assert imageField != null : "fx:id=\"imageField\" was not injected: check your FXML file 'Arvore.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Arvore.fxml'.";
        assert btnSubmit != null : "fx:id=\"btnSubmit\" was not injected: check your FXML file 'Arvore.fxml'.";
        assert txtAltura != null : "fx:id=\"txtAltura\" was not injected: check your FXML file 'Arvore.fxml'.";
        assert txtIdade != null : "fx:id=\"txtIdade\" was not injected: check your FXML file 'Arvore.fxml'.";
        assert dropdown != null : "fx:id=\"dropdown\" was not injected: check your FXML file 'Arvore.fxml'.";
        assert btnExibirInfo != null : "fx:id=\"btnExibirInfo\" was not injected: check your FXML file 'Arvore.fxml'.";

        Arvore carvalho = new Arvore("Carvalho", "Quercus robur", 20, 80, "Europa");
        Arvore pinheiro = new Arvore("Pinheiro-do-Paraná", "Araucaria angustifolia", 30, 60, "Brasil");
        Arvore palmeira = new Arvore("Palmeira-imperial", "Roystonea oleracea", 25, 50, "Caribe");
        Arvore bordo = new Arvore("Bordo", "Acer saccharum", 18, 70, "Canadá");
        Arvore ipe = new Arvore("Ipê-amarelo", "Handroanthus albus", 10, 40, "Brasil");
        Arvore cerejeira = new Arvore("Cerejeira", "Prunus serrulata", 12, 35, "Japão");


        dropdown.getItems().addAll(carvalho, pinheiro, palmeira, bordo, ipe, cerejeira);
    }

    @FXML
    void criarArvore() {
        String nome = txtNome.getText();
        String especie = txtEspecie.getText();
        String origem = txtOrigem.getText();
        int altura = Integer.parseInt(txtAltura.getText());
        int idade = Integer.parseInt(txtIdade.getText());
        Arvore novaArvore = new Arvore(nome, especie, altura, idade, origem);

        dropdown.getItems().add(novaArvore);

        txtNome.clear();
        txtEspecie.clear();
        txtAltura.clear();
        txtIdade.clear();
        txtOrigem.clear();
    }

    @FXML
    void exibirInfo() {
        Arvore arvoreSelecionada = dropdown.getSelectionModel().getSelectedItem();
        if (arvoreSelecionada != null) {
            txtDisplay.setText(arvoreSelecionada.exibirInformacoes());
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma árvore para exibir as informações.");
        }
    }

    @FXML
    void regar() {
        Arvore arvoreSelecionada = dropdown.getSelectionModel().getSelectedItem();
        if (arvoreSelecionada != null) {
            arvoreSelecionada.crescer(1);
            txtDisplay.setText("A árvore " + arvoreSelecionada.getNome() + " foi regada e cresceu 1 metro." + "\n" + arvoreSelecionada.exibirInformacoes()) ;
        }
    }

    @FXML
    void podar() {
        Arvore arvoreSelecionada = dropdown.getSelectionModel().getSelectedItem();
        if (arvoreSelecionada != null) {
            arvoreSelecionada.podar(1);
            txtDisplay.setText("A árvore " + arvoreSelecionada.getNome() + " foi podada e diminuiu 1 metro." + "\n" + arvoreSelecionada.exibirInformacoes());
        }
    }
}
