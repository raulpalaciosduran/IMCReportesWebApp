package me.jmll.imcreporteswebapp.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/imc")
public class RESTIMCService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testServicio() {
        return "Servicio IMC activo";
    }
}
