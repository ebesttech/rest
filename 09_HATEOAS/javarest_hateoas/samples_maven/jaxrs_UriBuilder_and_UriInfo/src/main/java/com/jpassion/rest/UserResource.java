package com.jpassion.rest;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/users")
public class UserResource {

	//Inject the context
    @Context
    UriInfo uriInfo;
    
    private AtomicInteger idCounter = new AtomicInteger();
    
    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") int id) {
    	
    	//Ex1.3
    	System.out.println("uriInfo.getRequestUri() = " + uriInfo.getRequestUri());
    	System.out.println("uriInfo.getBaseUri() = " + uriInfo.getBaseUri());
    	System.out.println("uriInfo.getPath() = " + uriInfo.getPath());
    	
    	//Get client request URL from context object uriInfo for creating response link via UriBuilder 
        //UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getRequestUri()); 
    	//Ex1.3 - replace getRequestUri() with getBaseUri and getPath
    	UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getBaseUri() + uriInfo.getPath()) ; 
        
    	String output = uriBuilder.build(id).toString(); //include the id to response URL

        return Response.ok(output)
                .type(MediaType.TEXT_PLAIN)
                .lastModified(new Date())
                .header("CustomHeader", "CustomValue")
                .build();

    }

    @POST
    @Produces(MediaType.APPLICATION_XML)
    public Response createUserInXML(@FormParam("name") String name,
                                    @FormParam("age") int age) {
    	
    	User user = new User(name, age);
    	user.setId(idCounter.incrementAndGet());
      
        UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getRequestUri());
        uriBuilder.path("{index}");

        return Response.created(uriBuilder.build(user.getId())) //substitute user id as index on the response link
                .entity(user)
                .type(MediaType.APPLICATION_XML)
                .lastModified(new Date())
                .header("CustomHeader", "CustomValue")
                .build();

    }
    
    //Ex1.3
//    1. Add the following code to the "getUser(..)" method and observe the output in the console
//    System.out.println("uriInfo.getRequestUri() = " + uriInfo.getRequestUri());
//    System.out.println("uriInfo.getBaseUri() = " + uriInfo.getBaseUri());
//    System.out.println("uriInfo.getPath() = " + uriInfo.getPath());
//	  2. Replace uriInfo.getRequestUri() code using uriInfo.getBaseUri() and uriInfo.getPath()
    
    
    
    
}