package com.shopware.test.api;



import org.json.JSONObject;

import com.shopware.test.utils.APIClient;
import com.shopware.test.utils.CommonUtils;
import com.shopware.test.utils.QALogger;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class ApiManagement implements APIManagementConstants {
	private static APIClient apiClient;
	private String url;
	
	public JSONObject getCustomerList() {
		apiClient = new APIClient();
		url = CommonUtils.getValueProperties("url.base");
		String path = url + PATH_API + PATH_CUSTOMER;
		String result = apiClient.get(path, new HTTPBasicAuthFilter(AUTH_USER,AUTH_PWD));
		QALogger.info("[Get customer] Result: "+result);
		return new JSONObject(result);
	}
	
	public void deleteCustomer(String customerId) {
		apiClient = new APIClient();
		url = CommonUtils.getValueProperties("url.base");
		String path = url + PATH_API + PATH_CUSTOMER;
		String result = apiClient.delete(path, customerId, new HTTPBasicAuthFilter(AUTH_USER,AUTH_PWD));
		QALogger.info("[Delete customer] Body: "+result);
		QALogger.info("[Delete customer] Status code: "+apiClient.getStatusCode());
	}
	
	public JSONObject postCustomer(String body) {
		apiClient = new APIClient();
		url = CommonUtils.getValueProperties("url.base");
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
