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
		QALogger.info("Result: "+result);
		return new JSONObject(result);
	}
	
	public void deleteCustomer(String url, String customerId) {
		apiClient = new APIClient();
		String link = url + PATH_API + PATH_CUSTOMER;
		String result = apiClient.delete(link, customerId, new HTTPBasicAuthFilter(AUTH_USER,AUTH_PWD));
		QALogger.info("[Delete customer] Body: "+result);
		QALogger.info("[Delete customer] Status code: "+apiClient.getStatusCode());
	}
	
	public String getStatusCode() {
		return apiClient.getStatusCode();
	}

}
