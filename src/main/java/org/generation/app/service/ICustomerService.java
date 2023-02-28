package org.generation.app.service;

import java.util.List;

import org.generation.app.model.Customer;

public interface ICustomerService {
	
	public List<Customer> getAllCustomer();

	public List<Customer> getAllActiveCustomers();

	public Customer getCustomerById(long idCustomer);

}
