package com.jpassion.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomMethodNotAllowedMapper implements
        ExceptionMapper<CustomMethodNotAllowedException> {

   // You have to implement toResponse(..) method
   public Response 
   toResponse(CustomMethodNotAllowedException ex) {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).
            entity(ex.getMessage()).
            type("text/plain").
            build();
    }
}
