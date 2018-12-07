package com.shopware.test.utils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class APIClient {
	private ClientResponse response;
	
	public String get(String url,HTTPBasicAuthFilter auth) {
		Client client = Client.create();
		client.addFilter(auth);
		WebResource webResource = client.resource(url);
		response = webResource.accept("application/json").get(ClientResponse.class);
		return response.getEntity(String.class);
	}
	
	public String delete(String url, String id, HTTPBasicAuthFilter auth) {
		Client client = Client.create();
		client.addFilter(auth);
		String path = url+id;
		QALogger.info("Path delete: "+path);
		WebResource webResource = client.resource(path);
		response = webResource.delete(ClientResponse.class);
		return response.getEntity(String.class);
	}
	
	public String post(String url, String body, HTTPBasicAuthFilter auth) {
		Client client = Client.create();
		client.addFilter(auth);
		QALogger.info("Path delete: "+url);
		WebResource webResource = client.resource(url);
		response = webResource.post(ClientResponse.class,body);
		return response.getEntity(String.class);
	}
	
	public String getStatusCode() {
		return String.valueOf(response.getStatus());
	}
	
	
	

}
