package com.myproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

    public class AlunoController {


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
        private TextField txtNome;

        @FXML
        private TextField txtEstudou;

        @FXML
        private TextField txtFezProva;

        @FXML
        private Button btnVoltar;

        @FXML
        private TextArea txtResultado;

        private String nome;
        private boolean estudou;
        private boolean fezProva;

        private void atualizarVariaveis() {
            nome = txtNome.getText();
            estudou = txtEstudou.getText().equalsIgnoreCase("sim");
            fezProva = txtFezProva.getText().equalsIgnoreCase("sim");
        }

        @FXML
        private void exibirInfo() {
            atualizarVariaveis(); // Pega os valores digitados

            String resultado = "Aluno: " + nome +
                    "\nEstudou: " + (estudou ? "Sim" : "Não") +
                    "\nFez prova: " + (fezProva ? "Sim" : "Não");
            txtResultado.setText(resultado);
        }


        @FXML
        private void fazerProva() {
            atualizarVariaveis(); // Atualiza as variáveis de instância com os valores mais recentes

            if (estudou && fezProva) {
                txtResultado.setText(nome + " foi bem na prova!");
            } else if (estudou && !fezProva) {
                txtResultado.setText(nome + " reprovou, pois não fez a prova.");
            } else if (!estudou && fezProva) {
                txtResultado.setText(nome +  " eve dificuldades na prova.");
            } else {
                txtResultado.setText(nome + " não estudou nem fez a prova.");
            }

        }
        @FXML
        void voltarTelaInicial(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/view/TelaInicial.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }