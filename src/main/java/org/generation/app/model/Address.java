package org.generation.app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data // Generar setter y getter a partir de esta notación
@Table(name = "address")

public class Address {
	public static final int FIELDS_MAX_LENGTH = 160;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_adress")
	private long idAddress;
	
	@Column( name = "address", nullable = false, length = FIELDS_MAX_LENGTH)
	private String address; 
	
	@Column( name = "zip_code", length = FIELDS_MAX_LENGTH)
	private String zipCode; 
	
	@Column ( name =  "city", nullable = false, updatable = false,  unique = true, length = FIELDS_MAX_LENGTH)
	private String city;

	@ManyToOne
	@JoinColumn(name="fk_id_customer")
	private Customer fkIdCustomer; //Definimos de acuerdo a la entidad
}
