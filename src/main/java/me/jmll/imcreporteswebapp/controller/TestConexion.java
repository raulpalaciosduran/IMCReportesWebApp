package me.jmll.imcreporteswebapp.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConexion {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/imc_reportes?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = ""; // o tu contraseña si la configuraste

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("✅ Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar:");
            e.printStackTrace();
        }
    }
}

