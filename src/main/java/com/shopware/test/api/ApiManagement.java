package com.shopware.test.api;



import org.json.JSONObject;

import com.shopware.test.utils.APIClient;
import com.shopware.test.utils.QALogger;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class ApiManagement implements APIManagementConstants {
	private static APIClient apiClient;
	
	public JSONObject getCustomerList(String url) {
		apiClient = new APIClient();
		String link = url + PATH_API + PATH_CUSTOMER;
		String result = apiClient.get(link, new HTTPBasicAuthFilter(AUTH_USER,AUTH_PWD));
		QALogger.info("[Get customer] Result: "+result);
		return new JSONObject(result);
	}
	
	public void deleteCustomer(String url, String customerId) {
		apiClient = new APIClient();
		String link = url + PATH_API + PATH_CUSTOMER;
		String result = apiClient.delete(link, customerId, new HTTPBasicAuthFilter(AUTH_USER,AUTH_PWD));
		QALogger.info("[Delete customer] Body: "+result);
		QALogger.info("[Delete customer] Status code: "+apiClient.getStatusCode());
	}
	
	public JSONObject postCustomer(String url, String body) {
		apiClient = new APIClient();
		String path = url + PATH_API + PATH_CUSTOMER;
		QALogger.info("Body: "+body);
		String result = apiClient.post(path, body, new HTTPBasicAuthFilter(AUTH_USER,AUTH_PWD));
		QALogger.info("[Post customer] result: "+result);
		QALogger.info("[Post customer] status code: "+getStatusCode());
		return new JSONObject(result);
	}
	
	public String getStatusCode() {
		return apiClient.getStatusCode();
	}

}
