package com.myproject.dao;

import com.myproject.model.Carro;
import com.myproject.util.ConexaoDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO {

    private Connection connection;

    public CarroDAO() throws SQLException {
        this.connection = ConexaoDB.getConnection();
    }

    public static boolean viagemAutorizada(Carro carro) {
        return carro.getNivelCombustivel() == carro.getCapacidadeTanque();
    }

    //metodo para verificar se a placa já existe no banco de dados
    public static boolean placaExiste(String placa) {
        String sql = "SELECT COUNT(*) FROM carro WHERE placa = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar placa: " + e.getMessage());
        }
        return false;
    }

    public static Carro buscarCarroPorId(int carroId) {
        String sql = "SELECT * FROM carro WHERE id = ? AND removido = 0";
        Carro carro = null;

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, carroId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                carro = new Carro();
                carro.setId(rs.getInt("id"));
                carro.setModelo(rs.getString("modelo"));
                carro.setMarca(rs.getString("marca"));
                carro.setPlaca(rs.getString("placa"));
                carro.setKmRodado(rs.getInt("km_rodado"));
                carro.setConsumo(rs.getDouble("consumo"));
                carro.setCapacidadeTanque(rs.getInt("capacidade"));
                carro.setNivelCombustivel(rs.getInt("nivel_combustivel"));
                carro.setImage(rs.getString("image"));
                carro.setTipo(rs.getString("tipo"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar veículo por ID: " + e.getMessage());
        }
        return carro;
    }

    //inicio metodos CRUD
    public boolean cadastrarCarro(Carro carro) {
        if (CarroDAO.placaExiste(carro.getPlaca())) {
            System.out.println("Placa já cadastrada");
            return false;
        }

        String sql = "INSERT INTO carro (modelo, marca, placa, km_rodado, consumo, capacidade, nivel_combustivel, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, carro.getModelo());
            stmt.setString(2, carro.getMarca());
            stmt.setString(3, carro.getPlaca());
            stmt.setInt(4, carro.getKmRodado());
            stmt.setDouble(5, carro.getConsumo());
            stmt.setInt(6, carro.getCapacidadeTanque());
            stmt.setInt(7, carro.getNivelCombustivel());
            stmt.setString(8, carro.getImage());
            stmt.executeUpdate();
            System.out.println("Veículo cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar veículo: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public static boolean removerCarro(Carro carro) {
        System.out.println("Tentando remover carro com placa: " + carro.getPlaca());

        if (!placaExiste(carro.getPlaca())) {
            System.out.println("Placa não encontrada no banco.");
            return false;
        }

        String sql = "UPDATE carro SET removido = 1 WHERE placa = ?";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, carro.getPlaca());
            int affectedRows = stmt.executeUpdate();

            System.out.println("Linhas afetadas: " + affectedRows);

            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao remover veículo: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static Carro buscarCarro(String placa) {
        String sql = "SELECT * FROM carro WHERE placa = ? AND removido = 0";
        Carro carro = null;

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                carro = new Carro();
                carro.setModelo(rs.getString("modelo"));
                carro.setMarca(rs.getString("marca"));
                carro.setPlaca(rs.getString("placa"));
                carro.setKmRodado(rs.getInt("km_rodado"));
                carro.setConsumo(rs.getDouble("consumo"));
                carro.setCapacidadeTanque(rs.getInt("capacidade"));
                carro.setNivelCombustivel(rs.getInt("nivel_combustivel"));
                carro.setImage(rs.getString("image"));
                carro.setTipo(rs.getString("tipo"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar veículo: " + e.getMessage());
        }
        return carro;
    }

    public static boolean atualizarCarro(Carro carro) {
        String sql = "UPDATE carro SET modelo = ?, marca = ?, km_rodado = ?, consumo = ?, capacidade = ?, nivel_combustivel = ?, image = ?, tipo = ? WHERE placa = ?";

        if (!placaExiste(carro.getPlaca())) {
            System.out.println("Placa não encontrada.");
            return false;
        }
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, carro.getModelo());
            stmt.setString(2, carro.getMarca());
            stmt.setInt(3, carro.getKmRodado());
            stmt.setDouble(4, carro.getConsumo());
            stmt.setInt(5, carro.getCapacidadeTanque());
            stmt.setInt(6, carro.getNivelCombustivel());
            stmt.setString(7, carro.getImage());
            stmt.setString(8, carro.getTipo());
            stmt.setString(9, carro.getPlaca());

            int affectedRows = stmt.executeUpdate();
            System.out.println("Linhas afetadas: " + affectedRows);


            if (affectedRows > 0) {
                System.out.println("Veículo atualizado com sucesso!");
                System.out.println("Atualizando carro: " + carro.getPlaca());

                return true;
            } else {
                System.out.println("Nenhum veículo encontrado com a placa: " + carro.getPlaca());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar veículo: " + e.getMessage());
        }
        return false;
    }
    //fim dos metodos CRUD

    //inicio metodos para listagem de carros
    public static List<Carro> listarCarrosDisponiveis() {
        List<Carro> carrosDisponiveis = new ArrayList<>();
        String sql = "SELECT * FROM carro WHERE disponibilidade = 0 AND removido = 0";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Carro carro = new Carro();
                carro.setId(rs.getInt("id"));
                carro.setModelo(rs.getString("modelo"));
                carro.setMarca(rs.getString("marca"));
                carro.setTipo(rs.getString("tipo"));
                carro.setPlaca(rs.getString("placa"));
                carro.setKmRodado(rs.getInt("km_rodado"));
                carro.setConsumo(rs.getDouble("consumo"));
                carro.setCapacidadeTanque(rs.getInt("capacidade"));
                carro.setNivelCombustivel(rs.getInt("nivel_combustivel"));
                carro.setImage(rs.getString("image"));

                carrosDisponiveis.add(carro);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar veículos: " + e.getMessage());
        }
        return carrosDisponiveis;
    }

}

