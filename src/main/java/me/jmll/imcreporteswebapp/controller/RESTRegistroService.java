package me.jmll.imcreporteswebapp.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.jmll.imcreporteswebapp.model.Registro;
import me.jmll.imcreporteswebapp.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Path("/registros")
public class RESTRegistroService {

    // 📝 Guardar nuevo registro
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarRegistro(Registro registro) {
        try (Connection conn = Conexion.getConnection()) {
            String sql = "INSERT INTO registros (usuario_id, peso, altura, imc, edad, fecha_registro) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, registro.getUsuarioId());
            stmt.setDouble(2, registro.getPeso());
            stmt.setDouble(3, registro.getAltura());
            stmt.setDouble(4, registro.getImc());
            stmt.setInt(5, registro.getEdad());
            stmt.setString(6, registro.getFechaRegistro());

            stmt.executeUpdate();
            return Response.status(Response.Status.CREATED)
                           .entity("{\"mensaje\":\"Registro guardado correctamente\"}")
                           .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{\"error\":\"" + e.getMessage() + "\"}")
                           .build();
        }
    }

    // 📊 Obtener registros por usuario
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerRegistrosPorUsuario(@PathParam("id") int idUsuario) {
        List<Registro> registros = new ArrayList<>();

        try (Connection conn = Conexion.getConnection()) {
            String sql = "SELECT * FROM registros WHERE usuario_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Registro r = new Registro();
                r.setId(rs.getInt("id"));
                r.setUsuarioId(rs.getInt("usuario_id"));
                r.setPeso(rs.getDouble("peso"));
                r.setAltura(rs.getDouble("altura"));
                r.setImc(rs.getDouble("imc"));
                r.setEdad(rs.getInt("edad"));
                r.setFechaRegistro(rs.getString("fecha_registro"));
                registros.add(r);
            }

            return Response.ok(registros).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity("{\"error\":\"" + e.getMessage() + "\"}")
            .build();
    }
}

}

