package org.generation.app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data //Generar setter y getter a partir de esta notación
@Table( name = "customer" ) 
public class Customer {
	public static final int FIELDS_MAX_LENGTH = 160;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column( name = "id_customer")
	private long idCustomer;
	
	@Column( name = "first_name", nullable = false, length = FIELDS_MAX_LENGTH)
	private String firstName; 
	
	@Column( name = "last_name", length = FIELDS_MAX_LENGTH)
	private String lastName; 
	
	@Column ( name =  "email", nullable = false, updatable = false,  unique = true, length = FIELDS_MAX_LENGTH)
	private String email;
	
	@Column ( name =  "password", length = FIELDS_MAX_LENGTH)
	private String password; 
	
	@Column ( name =  "avatar", length = FIELDS_MAX_LENGTH)
	private String avatar;
	
	@Column ( name =  "is_active")
	private boolean active;
	
	//Constructor por default
	
	
}
