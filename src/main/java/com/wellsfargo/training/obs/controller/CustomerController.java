package com.wellsfargo.training.obs.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.wellsfargo.training.obs.service.CustomerService;

import com.wellsfargo.training.obs.exception.ResourceNotFoundException;
import com.wellsfargo.training.obs.model.Address;
import com.wellsfargo.training.obs.model.Customer;
import com.wellsfargo.training.obs.model.Occupation;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value="/api")
public class CustomerController {
	@Autowired
	private CustomerService customerservice;
	
	@PostMapping("/openaccount")
	public ResponseEntity<String> createCustomer(@Validated @RequestBody Customer customer) {
		
//		com.wellsfargo.training.obs.model.Address address = customer.getAddress();
		Address address = customer.getAddress();
		Occupation occupation = customer.getOccupation();
		
		
		// Establish the bi-directional relationship
        address.setCustomer(customer);
        customer.setAddress(address);
        
        occupation.setCustomer(customer);
        customer.setOccupation(occupation);
				
		Customer registeredUser = customerservice.registerUser(customer);
	    if (registeredUser!= null) {
	        return ResponseEntity.ok("Registration successful");
	    } else {
	        return ResponseEntity.badRequest().body("Registration failed");
	    }
		
	}
	

}
