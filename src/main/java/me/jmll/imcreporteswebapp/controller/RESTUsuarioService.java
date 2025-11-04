package me.jmll.imcreporteswebapp.controller;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import me.jmll.imcreporteswebapp.model.Usuario;
import me.jmll.imcreporteswebapp.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import jakarta.ws.rs.GET;
import java.util.List;
import java.util.ArrayList;


@Path("/usuario")
public class RESTUsuarioService {

    // 🔐 Método login usuario
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUsuario(Usuario usuario) {
        try (Connection conn = Conexion.getConnection()) {
            String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contraseña = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getContraseña());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuarioLogueado = new Usuario();
                usuarioLogueado.setIdUsuario(rs.getInt("id_usuario"));
                usuarioLogueado.setNombreCompleto(rs.getString("nombre_completo"));
                usuarioLogueado.setEdad(rs.getInt("edad"));
                usuarioLogueado.setSexo(rs.getString("sexo"));
                usuarioLogueado.setEstatura(rs.getDouble("estatura"));
                usuarioLogueado.setNombreUsuario(rs.getString("nombre_usuario"));
                // No se devuelve la contraseña por seguridad

                return Response.ok(usuarioLogueado).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED)
                               .entity("{\"error\":\"Credenciales inválidas\"}").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{\"error\":\"Error al conectar con la base de datos\"}").build();
        }
    } 
    
    // 🧾 Método para registrar usuario en formulario
    @POST
    @Path("/registrar")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarUsuario(@FormParam("nombre_completo") String nombreCompleto,
                                     @FormParam("nombre_usuario") String nombreUsuario,
                                     @FormParam("contraseña") String contraseña,
                                     @FormParam("edad") int edad,
                                     @FormParam("sexo") String sexo,
                                     @FormParam("estatura") double estatura) {
      try (Connection conn = Conexion.getConnection()) {
        String sql = "INSERT INTO usuarios (nombre_completo, nombre_usuario, contraseña, edad, sexo, estatura) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nombreCompleto);
        stmt.setString(2, nombreUsuario);
        stmt.setString(3, contraseña); // Puedes aplicar hash si quieres
        stmt.setInt(4, edad);
        stmt.setString(5, sexo);
        stmt.setDouble(6, estatura);

        int filas = stmt.executeUpdate();
        if (filas > 0) {
          return Response.ok("{\"mensaje\":\"Usuario registrado correctamente\"}").build();
        } else {
          return Response.status(Response.Status.BAD_REQUEST)
                         .entity("{\"error\":\"No se pudo registrar el usuario\"}")
                         .build();
        }
      } catch (SQLException | ClassNotFoundException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                       .entity("{\"error\":\"" + e.getMessage() + "\"}")
                       .build();
      }
    }
   
    //Método para registra JSON
    @POST
    @Path("/registrar/json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarUsuarioJson(Usuario usuario) {
        if (usuario == null || usuario.getNombreUsuario() == null || usuario.getContraseña() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("{\"error\":\"Datos incompletos\"}")
                           .build();
        }

        try (Connection conn = Conexion.getConnection()) {
            String sql = "INSERT INTO usuarios (nombre_completo, nombre_usuario, contraseña, edad, sexo, estatura, peso) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getNombreCompleto());
            stmt.setString(2, usuario.getNombreUsuario());
            stmt.setString(3, usuario.getContraseña());
            stmt.setInt(4, usuario.getEdad());
            stmt.setString(5, usuario.getSexo());
            stmt.setDouble(6, usuario.getEstatura());
            stmt.setDouble(7, usuario.getPeso());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                return Response.status(Response.Status.CREATED)
                               .entity("{\"mensaje\":\"Usuario registrado correctamente\"}")
                               .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("{\"error\":\"No se pudo registrar el usuario\"}")
                               .build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{\"error\":\"" + e.getMessage() + "\"}")
                           .build();
        }
}
 //Método para pintar tabla
    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = Conexion.getConnection()) {
            String sql = "SELECT * FROM usuarios";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombreCompleto(rs.getString("nombre_completo"));
                u.setNombreUsuario(rs.getString("nombre_usuario"));
                u.setContraseña(rs.getString("contraseña")); // Puedes omitir si no quieres mostrarla
                u.setEdad(rs.getInt("edad"));
                u.setSexo(rs.getString("sexo"));
                u.setEstatura(rs.getDouble("estatura"));
                u.setPeso(rs.getDouble("peso"));
                usuarios.add(u);
            }

            return Response.ok(usuarios).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{\"error\":\"" + e.getMessage() + "\"}")
                           .build();
        }
    }
}

