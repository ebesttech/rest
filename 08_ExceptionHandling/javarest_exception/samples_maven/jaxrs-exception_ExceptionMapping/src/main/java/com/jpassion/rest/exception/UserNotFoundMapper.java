package com.jpassion.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserNotFoundMapper implements
        ExceptionMapper<UserNotFoundException> {

   // You have to implement toResponse(..) method
   public Response 
   toResponse(UserNotFoundException ex) {
        return Response.status(Response.Status.NOT_FOUND).
            entity(ex.getMessage()).
            type("text/plain").
            build();
    }
}
