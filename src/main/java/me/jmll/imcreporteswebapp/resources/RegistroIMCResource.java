package me.jmll.imcreporteswebapp.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import me.jmll.imcreporteswebapp.dao.RegistroIMCDAO;
import me.jmll.imcreporteswebapp.model.RegistroIMC;

import java.util.List;

@Path("/registros")
public class RegistroIMCResource {

    @GET
    @Path("/usuario/{id_usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RegistroIMC> getPorUsuario(@PathParam("id_usuario") int idUsuario) {
        RegistroIMCDAO dao = new RegistroIMCDAO();
        return dao.obtenerPorUsuario(idUsuario);
    }
}


