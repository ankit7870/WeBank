package com.wellsfargo.training.obs.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.obs.model.Customer;

import com.wellsfargo.training.obs.repository.CustomerRepository;


@Service
public class CustomerService {

	@Autowired
	private CustomerRepository urepo;
	
	public Customer registerUser(Customer u) {
		return urepo.save(u);
	}
	
//	public Optional<Customer> loginUser(String email) {
//
//		return urepo.findByEmail(email); 
//	}
	
	public Optional<Customer> registerAccount(Long cid) {
		return urepo.findById(cid);
	}
	
}
