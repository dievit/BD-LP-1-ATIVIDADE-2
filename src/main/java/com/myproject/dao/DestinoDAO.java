package com.myproject.dao;

import com.myproject.model.Destino;
import com.myproject.util.ConexaoDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DestinoDAO {

    public static Destino buscarViagemPorId(int destinoId) {
        String sql = "SELECT * FROM destino WHERE id = ?";
        Destino destino = null;

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, destinoId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                destino = new Destino(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getDouble("distancia")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao buscar destino: " + e.getMessage());
        }
        return destino;
    }

    public static List<Destino> listarDestinos() {
        List<Destino> destinos = new ArrayList<>();
        String sql = "SELECT * FROM destino";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Destino destino = new Destino(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getDouble("distancia")
                );
                destinos.add(destino);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao listar destinos: " + e.getMessage());
        }

        return destinos;
    }

    public static Destino buscarDestinoPorId(int idDestino) {
        String sql = "SELECT * FROM destino WHERE id = ?";
        Destino destino = null;

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idDestino);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                destino = new Destino(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getDouble("distancia")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao buscar destino por ID: " + e.getMessage());
        }
        return destino;
    }
}
