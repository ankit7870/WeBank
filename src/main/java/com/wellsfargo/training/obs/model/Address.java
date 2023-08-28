package com.wellsfargo.training.obs.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Lombok is an open-source library (basically a standalone jar) capable of doing magic
 *  in automating the boilerplate code generation for any java class. 
 *  So if Lombok is in the classpath, it can quickly get rid of all the getters & setters 
 *  methods, class constructors, hashcode and equals methods and many more by adding a 
 *  couple of annotations to the class.
 */

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Address {
	
	@Id   // Primary key /unique
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;
	
	
	
	private String line1;
	private String line2;
	private String landmark;
	private String state;
	private @NonNull String city;
	private  int pincode;
	
	@OneToOne
	@JoinColumn(name="cumtomerid") 
	private Customer customer;


}
