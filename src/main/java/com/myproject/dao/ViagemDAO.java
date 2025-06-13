package com.myproject.dao;

import com.myproject.model.Viagem;
import com.myproject.util.ConexaoDB;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ViagemDAO {
    public boolean carroDisponivel(int idCarro, LocalDate dataViagem) {
        String sql = "SELECT COUNT(*) FROM viagem WHERE id_carro = ? AND data_viagem = ? AND em_rota = 1";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCarro);
            stmt.setDate(2, java.sql.Date.valueOf(dataViagem));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) == 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean motoristaDisponivel(int idMotorista, LocalDate dataViagem) {
        String sql = "SELECT COUNT(*) FROM viagem WHERE id_motorista = ? AND data_viagem = ? AND em_rota = 1";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMotorista);
            stmt.setDate(2, java.sql.Date.valueOf(dataViagem));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) == 0;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }


    public boolean cadastrarViagem(Viagem viagem) {
        if (viagem.getMotorista() == null || viagem.getCarro() == null || viagem.getDestino() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos obrigatórios");
            alert.setHeaderText(null);
            alert.setContentText("Preencha todos os campos: motorista, carro e destino.");
            alert.showAndWait();
            return false;
        }

        if (!carroDisponivel(viagem.getCarro().getId(), viagem.getDataViagem())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Carro Indisponível");
            alert.setHeaderText(null);
            alert.setContentText("Este carro já está cadastrado em uma viagem nesta data.");
            alert.showAndWait();
            return false;
        }

        if (!motoristaDisponivel(viagem.getMotorista().getId(), viagem.getDataViagem())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Motorista Indisponível");
            alert.setHeaderText(null);
            alert.setContentText("Este motorista já está cadastrado em uma viagem nesta data.");
            alert.showAndWait();
            return false;
        }



        String sql = "INSERT INTO viagem (id_motorista, id_carro, id_destino, distancia_km, custo_estimado, em_rota, data_viagem) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, viagem.getMotorista().getId());
            stmt.setInt(2, viagem.getCarro().getId());
            stmt.setInt(3, viagem.getDestino().getId());
            stmt.setDouble(4, viagem.getDistanciaKm());
            stmt.setDouble(5, viagem.getCustoEstimado());
            stmt.setBoolean(6, viagem.isEmRota());
            stmt.setDate(7, java.sql.Date.valueOf(viagem.getDataViagem()));

            int affectedRows = stmt.executeUpdate();
            System.out.println("Viagem cadastrada com sucesso! Linhas afetadas: " + affectedRows);

            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idGerado = generatedKeys.getInt(1);
                    viagem.setId(idGerado);
                    System.out.println("Viagem cadastrada com ID: " + idGerado);
                }
            }

            return affectedRows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao cadastrar viagem: " + e.getMessage());
        }

        return false;
    }

    public void removerViagem(int id) {
        String sql = "DELETE FROM viagem WHERE id = ?";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Viagem removida com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao remover viagem: " + e.getMessage());
        }
    }

    public static boolean atualizarViagem(Viagem viagem) {
        String sql = "UPDATE viagem SET id_motorista = ?, id_carro = ?, id_destino = ?, distancia_km = ?, custo_estimado = ?, em_rota = ?, data_viagem = ? WHERE id = ?";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, viagem.getMotorista().getId());
            stmt.setInt(2, viagem.getCarro().getId());
            stmt.setInt(3, viagem.getDestino().getId());
            stmt.setDouble(4, viagem.getDistanciaKm());
            stmt.setDouble(5, viagem.getCustoEstimado());
            stmt.setBoolean(6, viagem.isEmRota());
            stmt.setDate(7, java.sql.Date.valueOf(viagem.getDataViagem()));
            stmt.setInt(8, viagem.getId());

            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar viagem: " + e.getMessage());
        }

        return false;
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
                        MotoristaDAO.buscarMotoristaPorId(rs.getInt("id_motorista")),
                        CarroDAO.buscarCarroPorId(rs.getInt("id_carro")),
                        DestinoDAO.buscarDestinoPorId(rs.getInt("id_destino")),
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

    public static List<Viagem> buscarViagemPorDestino(int id_destino) {
        List<Viagem> porDestino = new ArrayList<>();
        String sql = "SELECT * FROM viagem WHERE id_destino = ?";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_destino);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Viagem viagem = new Viagem(
                        rs.getInt("id"),
                        MotoristaDAO.buscarMotoristaPorId(rs.getInt("id_motorista")),
                        CarroDAO.buscarCarroPorId(rs.getInt("id_carro")),
                        DestinoDAO.buscarDestinoPorId(rs.getInt("id_destino")),
                        rs.getDouble("distancia_km"),
                        rs.getDouble("custo_estimado"),
                        rs.getBoolean("em_rota"),
                        rs.getDate("data_viagem").toLocalDate()
                );
                porDestino.add(viagem);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao buscar viagens por destino: " + e.getMessage());
        }

        return porDestino;
    }
}
