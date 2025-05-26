package com.myproject.dao;

import com.myproject.model.Aluno;
import com.myproject.util.ConexaoDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AlunoDAO {

    public boolean matriculaExiste(String matricula) {
        String sql = "SELECT COUNT(*) FROM aluno WHERE matricula = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricula);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar matrícula: " + e.getMessage());
        }
        return false;
    }

    public AlunoDAO(Connection connection) {
    }

    public void inserirAluno(Aluno aluno) {

        if (matriculaExiste(aluno.getMatricula())) {
            System.out.println("Matricula já existe!");
            return;
        }

        String sql = "INSERT INTO aluno (nome, matricula, curso) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.setString(3, aluno.getCurso());

            stmt.executeUpdate();

            System.out.println("Aluno inserido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir aluno: " + e.getMessage());
            e.printStackTrace();

        }
    }

    public void removerAluno(Aluno aluno) {
        String sql = "UPDATE aluno SET removido = 1 WHERE matricula = ?";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getMatricula());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Aluno removido com sucesso!");

            } else {
                System.out.println("Aluno não foi removido!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover aluno: " + e.getMessage());
        }
    }

    public void reativarMatricula(Aluno aluno) {
        String sql = "UPDATE aluno SET removido = 0 WHERE matricula = ?";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getMatricula());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Matrícula ativada com sucesso!");
            } else {
                System.out.println("Matrícula não encontrada.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao ativar matrícula: " + e.getMessage());
        }
    }

    public List<Aluno> listarAlunosAtivos() {
        List<Aluno> alunosAtivos = new ArrayList<>();
        String sql = "SELECT * FROM aluno WHERE removido = 0";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setMatricula(rs.getString("matricula"));
                aluno.setCurso(rs.getString("curso"));

                alunosAtivos.add(aluno);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar alunos ativos: " + e.getMessage());
        }
        return alunosAtivos;
    }

    public List<Aluno> listarMatriculasInativas() {
        List<Aluno> alunosInativos = new ArrayList<>();
        String sql = "SELECT * FROM aluno WHERE removido = 1";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setMatricula(rs.getString("matricula"));
                aluno.setCurso(rs.getString("curso"));

                alunosInativos.add(aluno);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar alunos inativos: " + e.getMessage());
        }
        return alunosInativos;
    }

    public Aluno buscarAluno(String matricula) {
        String sql = "SELECT * FROM aluno WHERE matricula = ?";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            Aluno aluno = new Aluno();
            stmt.setString(1, matricula);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setMatricula(rs.getString("matricula"));
                aluno.setCurso(rs.getString("curso"));

                System.out.println("Aluno encontrado: " + aluno);
            } else {
                System.out.println("Aluno não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar aluno: " + e.getMessage());
        }
        return null;
    }

    public void atualizarAluno(Aluno aluno) {
        String sql = "UPDATE aluno SET nome = ?, curso = ? WHERE matricula = ?";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCurso());
            stmt.setString(3, aluno.getMatricula());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Aluno editado com sucesso!");
            } else {
                System.out.println("Aluno não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao editar aluno: " + e.getMessage());
        }
    }


}