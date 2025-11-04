package me.jmll.imcreporteswebapp;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.Set;
import java.util.HashSet;
import me.jmll.imcreporteswebapp.controller.RESTUsuarioService;
import me.jmll.imcreporteswebapp.controller.RESTIMCService;

@ApplicationPath("api")
public class JakartaRestConfiguration extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(RESTUsuarioService.class);
        resources.add(RESTIMCService.class);
        return resources;
    }
}

