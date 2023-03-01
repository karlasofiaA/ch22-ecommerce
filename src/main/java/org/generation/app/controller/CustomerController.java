package org.generation.app.controller;

import java.util.List;

import org.generation.app.model.Customer;
import org.generation.app.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("api/customers")
public class CustomerController {

	@Autowired
	ICustomerService customerService;
	
	@GetMapping //localhost:8080/api/customers
	public List<Customer> getAllCustomer(){
		return customerService.getAllCustomer();
	}
	
	@GetMapping("active") //localhost:8080/api/customers/active
	public List<Customer> getAllActiveCustomers(){
		return customerService.getAllActiveCustomers();
	}
	
	@GetMapping("{id}") //localhost:8080/api/customers/2
	public ResponseEntity<?> getCustomerById(@PathVariable("id") long idCustomer) {
		//Manejo de excepciones
		try {
			return new ResponseEntity<Customer>(
					customerService.getCustomerById(idCustomer), //Objeto con el que respondes
					HttpStatus.OK); //Estatus de la respuesta OK
		} catch (IllegalStateException e) {
			return new ResponseEntity<String>(
					e.getMessage(),
					HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping //localhost:8080/api/customers
	public ResponseEntity<?> setNewCustomer (@RequestBody Customer customer) {
		try {
			return new ResponseEntity<Customer>(
					customerService.setCustomer(customer), //Objeto con el que respondes
					HttpStatus.CREATED); //Estatus de la respuesta OK
			
		} catch (IllegalStateException e) {
			return new ResponseEntity<String>(
					e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer){
		try {
			return new ResponseEntity<Customer>(
					customerService.updateCustomer(customer), //Objeto con el que respondes
					HttpStatus.CREATED); //Estatus de la respuesta OK
			
		} catch (IllegalStateException e) {
			return new ResponseEntity<String>(
					e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("{id}") //localhost:8080/api/customers/2
	public ResponseEntity<?> deleteCustomerById(@PathVariable("id") long idCustomer) {
		try {
			return new ResponseEntity<String>(
					customerService.deleteCustomerById(idCustomer), 
					HttpStatus.OK);													
		} catch (IllegalStateException e) {
			return new ResponseEntity<String>(e.getMessage() , HttpStatus.NOT_FOUND );
		}
	}

	
}
