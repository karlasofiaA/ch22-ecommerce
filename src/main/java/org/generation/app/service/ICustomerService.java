package org.generation.app.service;

import java.util.List;

import org.generation.app.dto.CustomerDto;
import org.generation.app.model.Customer;

public interface ICustomerService {
	
	public List<Customer> getAllCustomer();

	public List<CustomerDto> getAllCustomerDto();

	public List<Customer> getAllActiveCustomers();

	public Customer getCustomerById(long idCustomer);

	//Especificar el método DTO
	public CustomerDto getCustomerDtoById(long idCustomer);

	public boolean existCustomerByEmail(String email);
	
	public Customer setCustomer(Customer customer);
	
	public Customer updateCustomer(Customer customer);
	
	public String deleteCustomerById(long idCustomer);
}
