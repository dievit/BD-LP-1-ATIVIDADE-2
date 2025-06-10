package com.myproject.dao;

import com.myproject.model.Destino;
import com.myproject.util.ConexaoDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DestinoDAO {

    public static Object buscarViagemPorId(int id) {
        String sql = "SELECT * FROM destino WHERE id = ?";
        Destino destino = null;

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                destino = new Destino(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getDouble("distanciakm")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao buscar destino: " + e.getMessage());
        }

        return destino;


    }
}
