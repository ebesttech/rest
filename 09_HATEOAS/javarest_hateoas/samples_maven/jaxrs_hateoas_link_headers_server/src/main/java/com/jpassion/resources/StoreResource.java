package com.jpassion.resources;

import javax.ws.rs.HEAD;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/shop")
public class StoreResource {
	
	@HEAD
	public Response head(@Context UriInfo uriInfo) {
		UriBuilder absolute = uriInfo.getBaseUriBuilder();
		URI customerUrl = absolute.clone().path(CustomerResource.class).build();
		URI orderUrl = absolute.clone().path(OrderResource.class).build();

		Response.ResponseBuilder responseBuilder = Response.ok();
		Link customers = Link.fromUri(customerUrl).rel("customers").type("application/xml").build();
		Link orders = Link.fromUri(orderUrl).rel("orders").type("application/xml").build();
		// Add links to the message as headers
		responseBuilder.links(customers, orders);
		return responseBuilder.build();
	}
}
