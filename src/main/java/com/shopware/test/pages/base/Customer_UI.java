package com.shopware.test.pages.base;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.shopware.test.utils.DataUtils;

public class Customer_UI {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String salutation;
	private List<Address_UI> addresses;
	private String customerType;

	public Customer_UI() {
		super();
		this.firstName = DataUtils.randomString(10);
		this.lastName = DataUtils.randomString(10);
		this.email = DataUtils.randomEmail("test", "yopmail.com");
		this.password = "@bcd1234";
		this.salutation = "Mr";
		this.customerType = "Private customer";
		this.addresses = new ArrayList<Address_UI>();
		addresses.add(new Address_UI(this));
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public List<Address_UI> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address_UI> addresses) {
		this.addresses = addresses;
	}

	public void addAddress(Address_UI address) {
		this.addresses.add(address);
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	
	public JSONObject converToJSON() {
		JSONObject jBilling = new JSONObject();
		jBilling.put("firstname",addresses.get(0).getFirstName());
		jBilling.put("lastname", addresses.get(0).getLastName());
		jBilling.put("salutation", addresses.get(0).getSaluation().toLowerCase());
		jBilling.put("street", addresses.get(0).getAddressLine());
		jBilling.put("city", addresses.get(0).getCity());
		jBilling.put("zipcode", addresses.get(0).getZipCode());
		jBilling.put("country", 11);
		
		JSONObject jCustomer = new JSONObject();
		jCustomer.put("salutation",this.salutation.toLowerCase());
		jCustomer.put("firstname", this.firstName);
		jCustomer.put("lastname", this.lastName);
		jCustomer.put("email", this.email);
		jCustomer.put("password", this.password);
		jCustomer.put("billing", jBilling);
		return jCustomer;
	}
}
