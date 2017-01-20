package com.jpassion.resources;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/resources")
public class CustomerApplication extends Application {
	
	private Set<Object> singletons = new HashSet<Object>();

	public CustomerApplication() {
		singletons.add(new CustomerResource());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
