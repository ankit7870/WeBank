package com.wellsfargo.training.obs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.obs.model.Transaction;
import com.wellsfargo.training.obs.repository.TransactionRepository;

@Service
public class TransactionService {
	@Autowired
	TransactionRepository trepo;
	public Transaction saveTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		return trepo.save(transaction);
	}
	public List<Transaction> getAllTransaction() {
		// TODO Auto-generated method stub
		return trepo.findAll();
	}

}
