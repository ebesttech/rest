package com.jpassion.client;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;

import org.junit.Assert;

public class Main {

	public static void main(String[] args) throws Exception {

		final String RESOURCE_URL = "http://localhost:8080/jaxrs_hateoas_link_headers_server/resources/shop";

		Client client = ClientBuilder.newClient();
		
		System.out.println("----> Get customers and orders links from Shop resource ");
		Response response = client.target(RESOURCE_URL).request().head();
		
		// Get the links from "customers" relation
		Link customersLink = response.getLink("customers");
		System.out.println(customersLink);
		// Get the links from "orders" relation
		Link ordersLink = response.getLink("orders");
		System.out.println(ordersLink);

		System.out.println("----> Create a customer through this URL: " + customersLink.getUri().toString());

		Customer customer = new Customer();
		customer.setFirstName("Tom");
		customer.setLastName("Jons");
		customer.setStreet("10 Somewhere Street");
		customer.setCity("Westford");
		customer.setState("MA");
		customer.setZip("01711");
		customer.setCountry("USA");

		response = client.target(customersLink).request().post(Entity.xml(customer));
		Assert.assertEquals(201, response.getStatus());
		URI createdCustomerUrl = response.getLocation();
		
		System.out.println("----> Update a customer through this URL: " + createdCustomerUrl);
		customer.setZip("99999");
		customer.setCity("City1");
		client.target(createdCustomerUrl).request().put(Entity.xml(customer));

		// Create a new Order with a couple of LineItems
		Order order = new Order();
		order.setTotal("$199.99");
		order.setCustomer(customer);
		order.setDate(new Date().toString());
		LineItem item1 = new LineItem();
		item1.setCost("$199.99");
		item1.setProduct("iPhone");
		LineItem item2 = new LineItem();
		item2.setCost("$299.99");
		item2.setProduct("SamSung Galaxy");
		order.setLineItems(new ArrayList<LineItem>());
		order.getLineItems().add(item1);
		order.getLineItems().add(item2);

		System.out.println("----> Create an order through this URL: " + ordersLink.getUri().toString());
		response = client.target(ordersLink).request().post(Entity.xml(order));
		Assert.assertEquals(201, response.getStatus());
		URI createdOrderUrl = response.getLocation();

		// Get list of orders
		System.out.println("----> New list of orders");
		response = client.target(ordersLink).request().get();
		String orderList = response.readEntity(String.class);
		System.out.println(new XmlFormatter().format(orderList));
		Link purge = response.getLink("purge");

		// Cancel an order
		response = client.target(createdOrderUrl).request().head();
		Link cancel = response.getLink("cancel");
		response.close();
		if (cancel != null) {
			System.out.println("----> Canceling the order at URL: " + cancel.getUri().toString());
			response = client.target(cancel).request().post(null);
			Assert.assertEquals(204, response.getStatus());
		}

		System.out.println("----> New list of orders after cancel: ");
		orderList = client.target(ordersLink).request().get(String.class);
		System.out.println(new XmlFormatter().format(orderList));

		System.out.println("----> Purge cancelled orders at URL: " + purge.getUri().toString());
		response = client.target(purge).request().post(null);
		Assert.assertEquals(204, response.getStatus());
		

		System.out.println("----> New list of orders after purge: ");
		orderList = client.target(ordersLink).request().get(String.class);
		System.out.println(new XmlFormatter().format(orderList));
		
		client.close();

	}
}
