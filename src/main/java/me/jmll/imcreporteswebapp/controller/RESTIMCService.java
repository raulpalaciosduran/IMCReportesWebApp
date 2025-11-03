package me.jmll.imcreporteswebapp.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import me.jmll.imcreporteswebapp.model.RegistroIMC;
import me.jmll.imcreporteswebapp.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;


@Path("/imc")
public class RESTIMCService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testServicio() {
        return "Servicio IMC activo";
    }

    @POST
    @Path("/guardar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response guardarRegistroIMC(RegistroIMC registro) {
        try (Connection conn = Conexion.getConnection()) {
            double imc = registro.getPeso() / Math.pow(registro.getAltura(), 2);

            String sql = "INSERT INTO registros_imc (id_usuario, peso, imc, fecha_registro) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, registro.getUsuarioId());
            stmt.setDouble(2, registro.getPeso());
            stmt.setDouble(3, imc);
            stmt.setDate(4, Date.valueOf(registro.getFecha())); // fecha es LocalDate

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                return Response.ok("Registro guardado exitosamente").build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                               .entity("No se pudo guardar el registro").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error al conectar con la base de datos").build();
        }
    }
    @GET
    @Path("/registros")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerRegistrosIMC() {
        List<RegistroIMC> registros = new ArrayList<>();

        try (Connection conn = Conexion.getConnection()) {
            String sql = "SELECT id, usuario_id, peso, altura, fecha FROM registros_imc";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                RegistroIMC registro = new RegistroIMC();
                registro.setId(rs.getInt("id"));
                registro.setUsuarioId(rs.getInt("usuario_id"));
                registro.setPeso(rs.getDouble("peso"));
                registro.setAltura(rs.getDouble("altura"));
                registro.setFecha(rs.getDate("fecha").toLocalDate()); // fecha es LocalDate


                registros.add(registro);
            }

            return Response.ok(registros).build();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error al consultar registros").build();
        }
    }

}
