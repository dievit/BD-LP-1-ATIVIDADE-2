package com.myproject.dao;

import com.myproject.model.Viagem;
import com.myproject.util.ConexaoDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ViagemDAO {
    private static Connection conn;

    public ViagemDAO(Connection conn) {
        this.conn = conn;
    }

    public void cadastrarViagem(Viagem viagem) {
        String sql = "INSERT INTO viagem (motorista_id, carro_id, partida, destino, distancia_km, custo_estimado, em_rota) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, viagem.getMotorista().getId());
            stmt.setInt(2, viagem.getCarro().getId());
            stmt.setString(3, viagem.getPartida());
            stmt.setString(4, viagem.getDestino());
            stmt.setDouble(5, viagem.getDistanciaKm());
            stmt.setDouble(6, viagem.getCustoEstimado());
            stmt.setBoolean(7, viagem.isEmRota());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao cadastrar viagem: " + e.getMessage());
        }
    }

    public void removerViagem(int id) {
        String sql = "DELETE FROM viagem WHERE id = ?";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao remover viagem: " + e.getMessage());
        }
    }

    public static boolean atualizarViagem(Viagem viagem) {
        int motorista_id = viagem.getMotorista().getId();
        int carro_id = viagem.getCarro().getId();
        String partida = viagem.getPartida();
        String destino = viagem.getDestino();
        double distancia_km = viagem.getDistanciaKm();
        double custo_estimado = viagem.getCustoEstimado();
        boolean em_rota = viagem.isEmRota();
        LocalDate dataViagem = viagem.getDataViagem();

        String sql = "UPDATE viagem SET motorista_id = ?, carro_id = ?, partida = ?, destino = ?, distancia_km = ?, custo_estimado = ?, em_rota = ?, data_viagem = ? WHERE id = ?";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, motorista_id);
            stmt.setInt(2, carro_id);
            stmt.setString(3, partida);
            stmt.setString(4, destino);
            stmt.setDouble(5, distancia_km);
            stmt.setDouble(6, custo_estimado);
            stmt.setBoolean(7, em_rota);
            stmt.setDate(9, java.sql.Date.valueOf(dataViagem));
            stmt.setInt(8, viagem.getId());


            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar viagem: " + e.getMessage());
        }

        return true;
    }

    public static List<Viagem> listarViagens() {
        List<Viagem> viagens = new ArrayList<>();
        String sql = "SELECT * FROM viagem";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Viagem viagem = new Viagem(
                        rs.getInt("id"),
                        MotoristaDAO.buscarMotoristaPorId(rs.getInt("motorista_id")),
                        CarroDAO.buscarCarroPorId(rs.getInt("carro_id")),
                        rs.getString("partida"),
                        rs.getString("destino"),
                        rs.getDouble("distancia_km"),
                        rs.getDouble("custo_estimado"),
                        rs.getBoolean("em_rota"),
                        rs.getDate("data_viagem").toLocalDate()
                );

                viagens.add(viagem);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao listar viagens: " + e.getMessage());
        }

        return viagens;
    }

    public static Object buscarViagemPorDestino(String destino) {
        String sql = "SELECT * FROM viagem WHERE destino = ?";
        Viagem viagem = null;

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, destino);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                viagem = new Viagem(
                        rs.getInt("id"),
                        MotoristaDAO.buscarMotoristaPorId(rs.getInt("motorista_id")),
                        CarroDAO.buscarCarroPorId(rs.getInt("carro_id")),
                        rs.getString("partida"),
                        rs.getString("destino"),
                        rs.getDouble("distancia_km"),
                        rs.getDouble("custo_estimado"),
                        rs.getBoolean("em_rota"),
                        rs.getDate("data_viagem").toLocalDate()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao buscar viagem por ID: " + e.getMessage());
        }

        return viagem;
    }


}

