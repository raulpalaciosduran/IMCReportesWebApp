package me.jmll.imcreporteswebapp.dao;

import me.jmll.imcreporteswebapp.model.RegistroIMC;
import me.jmll.imcreporteswebapp.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistroIMCDAO {

    public List<RegistroIMC> obtenerPorUsuario(int idUsuario) {
    List<RegistroIMC> lista = new ArrayList<>();
    String sql = "SELECT r.id_registro, r.id_usuario, r.peso, r.altura, r.imc, r.fecha_registro, u.edad " +
             "FROM registros_imc r " +
             "JOIN usuarios u ON r.id_usuario = u.id_usuario " +
             "WHERE r.id_usuario = ? " +
             "ORDER BY r.fecha_registro ASC";


    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idUsuario);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            RegistroIMC r = new RegistroIMC();
            r.setId(rs.getInt("id_registro"));
            r.setUsuarioId(rs.getInt("id_usuario"));
            r.setPeso(rs.getDouble("peso"));
            r.setAltura(rs.getDouble("altura"));
            r.setImc(rs.getDouble("imc"));
            r.setFechaRegistro(rs.getDate("fecha_registro").toString());
            r.setEdad(rs.getInt("edad"));
            lista.add(r);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return lista;
}
}    

