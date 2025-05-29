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

    private Connection conn;

    public CarroDAO(Connection connection) {
        this.conn = connection;
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
    //inicio metodos CRUD
    public void cadastrarCarro(Carro carro) {
        if (CarroDAO.placaExiste(carro.getPlaca())) {
            System.out.println("Placa já cadastrada");
            return;
        }

        String sql = "INSERT INTO carro (modelo, marca, placa, kmRodado, consumo, capacidadeTanque, nivelCombustivel, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, carro.getModelo());
            stmt.setString(2, carro.getMarca());
            stmt.setString(3, carro.getPlaca());
            stmt.setInt(4, carro.getKmRodado());
            stmt.setInt(5, carro.getConsumo());
            stmt.setInt(6, carro.getCapacidadeTanque());
            stmt.setInt(7, carro.getNivelCombustivel());
            stmt.setString(8, carro.getImage());
            stmt.executeUpdate();
            System.out.println("Veículo cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar veículo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void removerCarro(Carro carro) {
        String sql = "UPDATE carro SET removido = 1 WHERE placa = ?";

        if (!placaExiste(carro.getPlaca())) {
            System.out.println("Placa não encontrada.");
            return;
        }
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, carro.getPlaca());
            stmt.executeUpdate();

            System.out.println("Veículo removido com sucesso!");
            int affectedRows = stmt.executeUpdate();


            if (affectedRows > 0) {
                System.out.println("Veículo removido com sucesso!");
            } else {
                System.out.println("Nenhum veículo encontrado com a placa: " + carro.getPlaca());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover veículo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Carro buscarCarro(String placa) {
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
                carro.setKmRodado(rs.getInt("kmRodado"));
                carro.setConsumo(rs.getInt("consumo"));
                carro.setCapacidadeTanque(rs.getInt("capacidadeTanque"));
                carro.setNivelCombustivel(rs.getInt("nivelCombustivel"));
                carro.setImage(rs.getString("image"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar veículo: " + e.getMessage());
        }
        return carro;
    }

    public void atualizarCarro(Carro carro) {
        String sql = "UPDATE carro SET modelo = ?, marca = ?, kmRodado = ?, consumo = ?, capacidadeTanque = ?, nivelCombustivel = ?, image = ? WHERE placa = ?";

        if (!placaExiste(carro.getPlaca())) {
            System.out.println("Placa não encontrada.");
            return;
        }
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, carro.getModelo());
            stmt.setString(2, carro.getMarca());
            stmt.setInt(3, carro.getKmRodado());
            stmt.setInt(4, carro.getConsumo());
            stmt.setInt(5, carro.getCapacidadeTanque());
            stmt.setInt(6, carro.getNivelCombustivel());
            stmt.setString(7, carro.getImage());
            stmt.setString(8, carro.getPlaca());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Veículo atualizado com sucesso!");
            } else {
                System.out.println("Nenhum veículo encontrado com a placa: " + carro.getPlaca());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar veículo: " + e.getMessage());
        }
    }
    //fim dos metodos CRUD

    //inicio metodos para listagem de carros
    public List<Carro> listarCarrosDisponiveis() {
        List<Carro> carrosDisponiveis = new ArrayList<>();
        String sql = "SELECT * FROM carro WHERE disponivel = 0";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Carro carro = new Carro();
                carro.setModelo(rs.getString("modelo"));
                carro.setMarca(rs.getString("marca"));
                carro.setPlaca(rs.getString("placa"));
                carro.setKmRodado(rs.getInt("kmRodado"));
                carro.setConsumo(rs.getInt("consumo"));
                carro.setCapacidadeTanque(rs.getInt("capacidadeTanque"));
                carro.setNivelCombustivel(rs.getInt("nivelCombustivel"));
                carro.setImage(rs.getString("image"));

                carrosDisponiveis.add(carro);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar veículos: " + e.getMessage());
        }
        return carrosDisponiveis;
    }

    public List<Carro> listarCarrosIndisponiveis() {
        List<Carro> carrosIndisponiveis = new ArrayList<>();
        String sql = "SELECT * FROM carro WHERE disponivel = 1";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Carro carro = new Carro();
                carro.setModelo(rs.getString("modelo"));
                carro.setMarca(rs.getString("marca"));
                carro.setPlaca(rs.getString("placa"));
                carro.setKmRodado(rs.getInt("kmRodado"));
                carro.setConsumo(rs.getInt("consumo"));
                carro.setCapacidadeTanque(rs.getInt("capacidadeTanque"));
                carro.setNivelCombustivel(rs.getInt("nivelCombustivel"));
                carro.setImage(rs.getString("image"));

                carrosIndisponiveis.add(carro);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar veículos indisponíveis: " + e.getMessage());
        }
        return carrosIndisponiveis;
    }

    public List<Carro> listarRemovidos() {
        List<Carro> carrosRemovidos = new ArrayList<>();
        String sql = "SELECT * FROM carro WHERE removido = 1";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Carro carro = new Carro();
                carro.setModelo(rs.getString("modelo"));
                carro.setMarca(rs.getString("marca"));
                carro.setPlaca(rs.getString("placa"));
                carro.setKmRodado(rs.getInt("kmRodado"));
                carro.setConsumo(rs.getInt("consumo"));
                carro.setCapacidadeTanque(rs.getInt("capacidadeTanque"));
                carro.setNivelCombustivel(rs.getInt("nivelCombustivel"));
                carro.setImage(rs.getString("image"));

                carrosRemovidos.add(carro);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar veículos removidos: " + e.getMessage());
        }
        return carrosRemovidos;
    }
    //fim dos metodos para listagem de carros
}

