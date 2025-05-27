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

    @FXML
    private Button btnReativarMatricula;

    @FXML
    private Button btnSalvar;

    @FXML
    private void salvarAluno() {
        String nome = txtNome.getText();
        String matricula = txtMatricula.getText();
        String curso = txtCurso.getText();

        if(nome.isEmpty() || matricula.isEmpty() || curso.isEmpty()) {
            txtResultado.setText("Preencha todos os campos.");
            return;
        }
        Connection connection = null;
        try {
            connection = ConexaoDB.getConnection();
            connection.setAutoCommit(true);
            Aluno aluno = new Aluno(nome, matricula, curso);
            AlunoDAO alunoDAO = new AlunoDAO(connection);
            alunoDAO.inserirAluno(aluno);

            limparCampos();
            txtResultado.setText("Aluno salvo com sucesso! ID: ");
        } catch (Exception e) {
            e.printStackTrace();
            txtResultado.setText("Erro ao salvar aluno. Veja o console para detalhes.");
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
    private void desabilitarAluno() {
        String matricula = txtMatricula.getText();
        if (!AlunoDAO.matriculaExiste(matricula)) {
            txtResultado.setText("Matrícula não encontrada.");
            return;
        }
        if(matricula.isEmpty()) {
            txtResultado.setText("Digite a matrícula do aluno poder desativá-lo.");
            return;
        }

        Connection connection = null;
        try {
            connection = ConexaoDB.getConnection();
            Aluno aluno = new Aluno(null, matricula, null);
            AlunoDAO alunoDAO = new AlunoDAO(connection);
            alunoDAO.removerAluno(aluno);
            txtResultado.setText("Aluno removido com sucesso!");

            limparCampos();
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
    private void buscarAluno() {
        String matricula = txtMatricula.getText();

        Connection connection = null;

        if (matricula.isEmpty()) {
            txtResultado.setText("Digite a matrícula do aluno, para buscar.");
            return;
        }
        if (!AlunoDAO.matriculaExiste(matricula)) {
            txtResultado.setText("Matrícula não encontrada.");
            return;
        }
        try {
            connection = ConexaoDB.getConnection();
            AlunoDAO alunoDAO = new AlunoDAO(connection);
            Aluno aluno = alunoDAO.buscarAluno(matricula);

            limparCampos();
            if (aluno != null) {
                txtMatricula.setText(aluno.getMatricula());
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

    @FXML
    private void reativarMatricula() {
        String matricula = txtMatricula.getText();

        if (!AlunoDAO.matriculaExiste(matricula)) {
            txtResultado.setText("Matrícula não encontrada.");
            return;
        }
        if (matricula.isEmpty()) {
            txtResultado.setText("Digite a matrícula do aluno para reativá-lo.");
            return;
        }

        Connection connection = null;
        try {
            connection = ConexaoDB.getConnection();
            Aluno aluno = new Aluno(null, matricula, null);
            AlunoDAO alunoDAO = new AlunoDAO(connection);
            alunoDAO.reativarMatricula(aluno);
            txtResultado.setText("Matrícula reativada com sucesso!");

            limparCampos();
        } catch (Exception e) {
            e.printStackTrace();
            txtResultado.setText("Erro ao reativar matrícula: " + e.getMessage());
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
    private void listarAlunosInativos() {
        Connection conn = null;
        try {
            conn = ConexaoDB.getConnection();
            AlunoDAO alunoDAO = new AlunoDAO(conn);
            StringBuilder res = new StringBuilder("Alunos Inativos:\n");

            for (Aluno aluno : alunoDAO.listarMatriculasInativas()) {
                res.append("******************************************" + ("\n") +
                        "Matrícula: " + aluno.getMatricula() + ("\n") +
                        " Nome: " + aluno.getNome() + ("\n") +
                        " Curso: " + aluno.getCurso() + ("\n"));
            }
            limparCampos();
            txtResultado.setText(res.toString());
        } catch (Exception e) {
            e.printStackTrace();
            txtResultado.setText("Erro ao listar alunos inativos: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void listarAlunosAtivos() {
        Connection connection = null;
        try {
            connection = ConexaoDB.getConnection();
            AlunoDAO alunoDAO = new AlunoDAO(connection);
            StringBuilder resultado = new StringBuilder("Alunos Ativos:\n");

            for (Aluno aluno : alunoDAO.listarAlunosAtivos()) {
                resultado.append("******************************************" + ("\n") +
                        "Matrícula: " + aluno.getMatricula() + ("\n") +
                        " Nome: " + aluno.getNome() + ("\n") +
                        " Curso: " + aluno.getCurso() + ("\n"));
            }
            limparCampos();
            txtResultado.setText(resultado.toString());
        } catch (Exception e) {
            e.printStackTrace();
            txtResultado.setText("Erro ao listar alunos: " + e.getMessage());
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
    private void updateAluno() {
        String nome = txtNome.getText();
        String matricula = txtMatricula.getText();
        String curso = txtCurso.getText();

        if (!AlunoDAO.matriculaExiste(matricula)) {
            txtResultado.setText("Matrícula não cadastrada");
            return;
        }

        if(nome.isEmpty() || matricula.isEmpty() || curso.isEmpty()) {
            txtResultado.setText("Preencha todos os campos.");
            return;
        }
        Connection connection = null;
        try {
            connection = ConexaoDB.getConnection();
            Aluno aluno = new Aluno(nome, matricula, curso);
            AlunoDAO alunoDAO = new AlunoDAO(connection);
            alunoDAO.atualizarAluno(aluno);

            limparCampos();
            txtResultado.setText("Aluno atualizado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            txtResultado.setText("Erro ao atualizar aluno: " + e.getMessage());
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
    private void limparCampos() {
        txtNome.clear();
        txtMatricula.clear();
        txtCurso.clear();
    }
}