package org.generation.app.repository;

import java.util.List;

import org.generation.app.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerRepository extends CrudRepository<Customer, Long> {

	//Regresar todos los clientes que son activos
	List<Customer> findAllByActive(boolean stateOfActive);
}
