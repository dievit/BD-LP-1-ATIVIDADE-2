package com.myproject.dao;

import com.myproject.model.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlunoDAO {
    private Connection connection;

    public AlunoDAO(Connection connection) {
        this.connection = connection;
    }


    public void inserirAluno(Aluno aluno) {
        String sql = "INSERT INTO alunos (nome, matricula, curso) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, aluno.getNome());
            statement.setString(2, aluno.getMatricula());
            statement.setString(3, aluno.getCurso());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerAluno(Aluno aluno) {
        String sql = "DELETE FROM alunos WHERE matricula = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, aluno.getMatricula());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarAluno(Aluno aluno) {
        String sql = "UPDATE alunos SET nome = ?, curso = ? WHERE matricula = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, aluno.getNome());
            statement.setString(2, aluno.getCurso());
            statement.setString(3, aluno.getMatricula());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Aluno buscarAluno(String matricula) {
        String sql = "SELECT * FROM alunos WHERE matricula = ?";
        Aluno aluno = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, matricula);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String curso = resultSet.getString("curso");
                aluno = new Aluno(nome, matricula, curso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aluno;
    }
}