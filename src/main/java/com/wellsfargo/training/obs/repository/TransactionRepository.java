package com.wellsfargo.training.obs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.training.obs.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	
		
}
