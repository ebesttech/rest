package com.jpassion.client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class Main {

	public static void main(String[] args) throws Exception {

		final String RESOURCE_URL = "http://localhost:8080/jaxrs_hateoas_atom_links_server/resources/customers";

		Client client = ClientBuilder.newClient();
		
		// Use XML
		URI uri = new URI(RESOURCE_URL);
		while (uri != null) {
			WebTarget target = client.target(uri);
			String output = target.request().get(String.class);
			System.out.println("----> Use XML:  Getting data from " + uri.toString());
			System.out.println(new XmlFormatter().format(output));

			Customers customers = target.request().get(Customers.class);
			uri = customers.getNext();
		}
		
		// Use JSON
		uri = new URI(RESOURCE_URL);
		while (uri != null) {
			WebTarget target = client.target(uri);
			String output = target.request().get(String.class);
			System.out.println("----> Use JSON: Getting data from " + uri.toString());

			Customers customers = target.request().accept("application/json")
					.get(Customers.class);
			uri = customers.getNext();
		}
		

		client.close();

	}
}
