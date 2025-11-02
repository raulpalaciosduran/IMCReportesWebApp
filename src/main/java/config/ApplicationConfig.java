package me.jmll.imcreporteswebapp.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("api")
public class ApplicationConfig extends Application {
    // Aquí se registran los servicios REST automáticamente
}
