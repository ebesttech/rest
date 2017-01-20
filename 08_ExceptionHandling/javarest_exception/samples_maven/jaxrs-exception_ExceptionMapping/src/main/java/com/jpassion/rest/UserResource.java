package com.jpassion.rest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jpassion.rest.exception.CustomMethodNotAllowedException;
import com.jpassion.rest.exception.UserNotFoundException;

@Path("/users")
public class UserResource {

	private Map<Integer, User> userDB = new ConcurrentHashMap<Integer, User>();
	private AtomicInteger idCounter = new AtomicInteger();

	// Init test data
	public UserResource() {
		userDB.put(idCounter.incrementAndGet(), new User("Sang Shin", 100));
		userDB.put(idCounter.incrementAndGet(), new User("Jo Masian", 200));
	}

	@GET
	@Path("{resourceID}.xml")
	public Response getCustomer(@PathParam("resourceID") int resourceID) {
		User user = userDB.get(resourceID);
		if (user == null) {
			throw new UserNotFoundException("Could not find user " + resourceID);
		}
		return Response.ok(user).type(MediaType.APPLICATION_XML).build();
	}
	
	//EX4.3 
	//1. Add a new dummy method that handles PUT method
	//2. Create CustomMethodNotAllowedException class
	//3. Create appropriate ExceptionMapper class
	//4. curl -X PUT http://localhost:8080/jaxrs_exception_ExceptionMapping/resources/users/10.xml
	@PUT
	@Path("{resourceID}.xml")
	public Response getCustomer1(@PathParam("resourceID") int resourceID) {		
		throw new CustomMethodNotAllowedException("Ex4.3 PUT Method Not allowed ");		
	}

}