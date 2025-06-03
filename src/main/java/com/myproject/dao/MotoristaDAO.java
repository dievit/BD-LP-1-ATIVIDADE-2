package com.myproject.dao;

import com.myproject.model.Motorista;
import com.myproject.util.ConexaoDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MotoristaDAO {
    private static Connection conn;

    public MotoristaDAO() {
        this.conn = conn;
    }

    public static boolean cnhExiste(String cnh) {
        String sql = "SELECT COUNT(*) FROM motorista WHERE cnh = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cnh);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar CNH: " + e.getMessage());
        }
        return false;
    }

    public static boolean cnhValida(String cnh) {
        String sql = "SELECT COUNT(*) FROM motorista WHERE cnh = ? AND validade_cnh >= CURRENT_DATE";

        try(Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cnh);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Date validade = rs.getDate("validade_cnh");
                LocalDate dataValidade = validade.toLocalDate();
                return !dataValidade.isBefore(LocalDate.now());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar validade da CNH: " + e.getMessage());
        }
        return false;
    }

    //inicio metodos CRUD

    public void cadastrarMotorista(Motorista motorista) {
        if (MotoristaDAO.cnhExiste(motorista.getCnh())) {
            System.out.println("CNH já cadastrada");
            return;
        }

        String sql = "INSERT INTO motorista (nome, cnh, validade_cnh, categoria_cnh, end_rua, end_numero, end_cidade, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, motorista.getNome());
            stmt.setString(2, motorista.getCnh());
            stmt.setDate(3, java.sql.Date.valueOf(motorista.getValidadeCNH()));
            stmt.setString(4, motorista.getCategoriaCNH());
            stmt.setString(5, motorista.getEndRua());
            stmt.setString(6, motorista.getEndNumero());
            stmt.setString(7, motorista.getEndCidade());
            stmt.setString(8, motorista.getTelefone());
            stmt.setString(9, motorista.getEmail());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar motorista: " + e.getMessage());
        }
    }

    public void removerMotorista(Motorista motorista) {
        String sql = "UPDATE motorista SET removido = 1 WHERE cnh = ?";

        if(!MotoristaDAO.cnhExiste(motorista.getCnh())) {
            System.out.println("CNH não encontrada");
            return;
        }

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, motorista.getCnh());
            stmt.executeUpdate();

            System.out.println("Motorista removido com sucesso!");
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Motorista removido com sucesso!");
            } else {
                System.out.println("Nenhum motorista encontrado com a CNH informada.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover motorista: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Motorista buscarMotoristaPorCNH(String cnh) {
        String sql = "SELECT * FROM motorista WHERE cnh = ? AND removido = 0";
        Motorista motorista = null;

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cnh);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                motorista = new Motorista(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cnh"),
                        rs.getDate("validade_cnh").toLocalDate(),
                        rs.getString("categoria_cnh"),
                        rs.getString("end_rua"),
                        rs.getString("end_numero"),
                        rs.getString("end_Cidade"),
                        rs.getString("telefone"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar motorista: " + e.getMessage());
        }
        return motorista;
    }

    public void atualizarMotorista(Motorista motorista) {

        if (!MotoristaDAO.cnhExiste(motorista.getCnh())) {
            System.out.println("CNH não encontrada.");
            return;
        }
        Motorista motoristaAtual = buscarMotoristaPorCNH(motorista.getCnh());

        if (motoristaAtual == null) {
            System.out.println("Motorista não encontrado.");
            return;
        }

        String nome = motorista.getNome() != null && !motorista.getNome().isEmpty() ? motorista.getNome() : motoristaAtual.getNome();
        LocalDate validade = motorista.getValidadeCNH() != null ? motorista.getValidadeCNH() : motoristaAtual.getValidadeCNH();
        String categoria = motorista.getCategoriaCNH() != null && !motorista.getCategoriaCNH().isEmpty() ? motorista.getCategoriaCNH() : motoristaAtual.getCategoriaCNH();
        String rua = motorista.getEndRua() != null && !motorista.getEndRua().isEmpty() ? motorista.getEndRua() : motoristaAtual.getEndRua();
        String numero = motorista.getEndNumero() != null && !motorista.getEndNumero().isEmpty() ? motorista.getEndNumero() : motoristaAtual.getEndNumero();
        String cidade = motorista.getEndCidade() != null && !motorista.getEndCidade().isEmpty() ? motorista.getEndCidade() : motoristaAtual.getEndCidade();
        String telefone = motorista.getTelefone() != null && !motorista.getTelefone().isEmpty() ? motorista.getTelefone() : motoristaAtual.getTelefone();
        String email = motorista.getEmail() != null && !motorista.getEmail().isEmpty() ? motorista.getEmail() : motoristaAtual.getEmail();

        String sql = "UPDATE motorista SET nome = ?, validade_cnh = ?, categoria_cnh = ?, end_Rua = ?, end_numero = ?, end_cidade = ?, telefone = ?, email = ? WHERE cnh = ?";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setDate(2, java.sql.Date.valueOf(validade));
            stmt.setDate(3, java.sql.Date.valueOf(motorista.getValidadeCNH()));
            stmt.setString(4, categoria);
            stmt.setString(5, rua);
            stmt.setString(6, numero);
            stmt.setString(7, cidade);
            stmt.setString(8, telefone);
            stmt.setString(9, email);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar motorista: " + e.getMessage());
        }
    }

    // fim dos metodos CRUD

    //inicio metodos para listagem
    public static List<Motorista> listarMotoristas() {
        List<Motorista> motoristas = new ArrayList<>();
        String sql = "SELECT * FROM motorista WHERE removido = 0";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Motorista motorista = new Motorista(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cnh"),
                        rs.getDate("validade_cnh").toLocalDate(),
                        rs.getString("categoria_cnh"),
                        rs.getString("end_rua"),
                        rs.getString("end_numero"),
                        rs.getString("end_cidade"),
                        rs.getString("telefone"),
                        rs.getString("email")
                );
                motoristas.add(motorista);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar motoristas: " + e.getMessage());
        }
        return motoristas;
    }

    public List<Motorista> listarMotoristasAptos() {
        List<Motorista> motoristasAptos = new ArrayList<>();
        String sql = "SELECT * FROM motorista WHERE removido = 0 AND validade_cnh >= CURRENT_DATE";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Motorista motorista = new Motorista(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cnh"),
                        rs.getDate("validade_cnh").toLocalDate(),
                        rs.getString("categoria_cnh"),
                        rs.getString("end_rua"),
                        rs.getString("end_numero"),
                        rs.getString("end_cidade"),
                        rs.getString("telefone"),
                        rs.getString("email")
                );
                motoristasAptos.add(motorista);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar motoristas aptos: " + e.getMessage());
        }
        return motoristasAptos;
    }
}
