package com.wellsfargo.training.obs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wellsfargo.training.obs.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
//	public Optional<Customer> findByEmail(String email);
	//public Customer getCurrentUser(Long cid);
//	public Optional<Customer> findByCustomerid(String customerid);
}
