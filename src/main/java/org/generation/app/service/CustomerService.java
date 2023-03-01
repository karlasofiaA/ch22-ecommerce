package org.generation.app.service;

import java.util.List;

import org.generation.app.model.Customer;
import org.generation.app.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService{

	@Autowired
	ICustomerRepository customerRepository;
	
	@Override
	public List<Customer> getAllCustomer() {
		List<Customer> allCustomers = (List<Customer>) customerRepository.findAll();
		return allCustomers;
	}

	@Override
	public List<Customer> getAllActiveCustomers() {
		List<Customer> allCustomers = customerRepository.findAllByActive(true);
		return allCustomers;
	}

	@Override
	public Customer getCustomerById(long idCustomer) { 
		return customerRepository.findById(idCustomer)
				.orElseThrow( () -> 
					new IllegalStateException("The user does not exist with id: " + idCustomer));
	}

	@Override
	public Customer setCustomer(Customer customer) {
		
		if ( existCustomerByEmail(customer.getEmail() ) )
			throw new IllegalStateException("User does exist with email: " + customer.getEmail());
		 else if ( customer.getEmail().length() > Customer.FIELDS_MAX_LENGTH )
	            throw new IllegalStateException("Email length is greater than: " + Customer.FIELDS_MAX_LENGTH);
		
		Customer newCustomer = customer;
		newCustomer.setIdCustomer(0);
		newCustomer.setActive(true);
		
		return customerRepository.save(newCustomer);
	}


	@Override
	public Customer updateCustomer(Customer newDataCustomer) {
		
		if ( !existCustomerByEmail(newDataCustomer.getEmail() ) )
			throw new IllegalStateException("The user does not exist with email: " + newDataCustomer.getEmail());
		 else if ( newDataCustomer.getEmail().length() > Customer.FIELDS_MAX_LENGTH )
	            throw new IllegalStateException("Email length is greater than: " + Customer.FIELDS_MAX_LENGTH);
		
		//Obtener los datos actuales del cliente
		Customer customer = getCustomerById(newDataCustomer.getIdCustomer());
		//Actualizar los datos permitidos
		customer.setFirstName(newDataCustomer.getFirstName());
		customer.setLastName(newDataCustomer.getLastName());
		customer.setAvatar(newDataCustomer.getAvatar());
		customer.setPassword(newDataCustomer.getPassword());
		
		return customerRepository.save(customer);
	}

	@Override
	public boolean existCustomerByEmail(String email) {
		
		return customerRepository.existsByEmail(email);
	}

	@Override
	public String deleteCustomerById(long idCustomer) {
		Customer customer = getCustomerById(idCustomer);
		customerRepository.delete(customer); //Elimina el registro
		
		customer.setActive(false);
		customerRepository.save(customer);
		
		return "The user was delete" + idCustomer;
	}
}
