package com.myproject.controller;

import com.myproject.dao.AlunoDAO;
import com.myproject.model.Aluno;
import com.myproject.util.ConexaoDB;
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
import java.sql.Connection;
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
        private TextField txtMatricula;

        @FXML
        private TextField txtCurso;

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

        @FXML
        private Button btnSalvar;

        @FXML
        private void salvarAluno() {
            String nome = txtNome.getText();
            String matrícula = txtMatricula.getText();
            String curso = txtCurso.getText();

            Connection connection = null;
            try {
                connection = ConexaoDB.getConnection();
                Aluno aluno = new Aluno(nome, matrícula, curso);
                AlunoDAO alunoDAO = new AlunoDAO(connection);
                alunoDAO.inserirAluno(aluno);
                txtResultado.setText("Aluno salvo com sucesso!");
            } catch (Exception e) {
                e.printStackTrace();
                txtResultado.setText("Erro ao salvar aluno: " + e.getMessage());
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        @FXML
        private void removerAluno() {
            String matricula = txtMatricula.getText();

            Connection connection = null;
            try {
                connection = ConexaoDB.getConnection();
                Aluno aluno = new Aluno(null, matricula, null);
                AlunoDAO alunoDAO = new AlunoDAO(connection);
                alunoDAO.removerAluno(aluno);
                txtResultado.setText("Aluno removido com sucesso!");
            } catch (Exception e) {
                e.printStackTrace();
                txtResultado.setText("Erro ao remover aluno: " + e.getMessage());
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @FXML
        private void editarAluno() {
            String nome = txtNome.getText();
            String matricula = txtMatricula.getText();
            String curso = txtCurso.getText();

            Connection connection = null;
            try {
                connection = ConexaoDB.getConnection();
                Aluno aluno = new Aluno(nome, matricula, curso);
                AlunoDAO alunoDAO = new AlunoDAO(connection);
                alunoDAO.editarAluno(aluno);
                txtResultado.setText("Aluno editado com sucesso!");
            } catch (Exception e) {
                e.printStackTrace();
                txtResultado.setText("Erro ao editar aluno: " + e.getMessage());
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @FXML
        private void buscarAluno() {
            String matricula = txtMatricula.getText();

            Connection connection = null;
            try {
                connection = ConexaoDB.getConnection();
                AlunoDAO alunoDAO = new AlunoDAO(connection);
                Aluno aluno = alunoDAO.buscarAluno(matricula);
                if (aluno != null) {
                    txtNome.setText(aluno.getNome());
                    txtCurso.setText(aluno.getCurso());
                    txtResultado.setText("Aluno encontrado!");
                } else {
                    txtResultado.setText("Aluno não encontrado.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                txtResultado.setText("Erro ao buscar aluno: " + e.getMessage());
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }