package com.mycompany.proyectosjsp.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Recurso REST para Java EE 8
 */
@Path("javaee8")
public class JavaEE8Resource {
    
    @GET
    public Response ping(){
        return Response
                .ok("ping Java EE")
                .build();
    }
}
