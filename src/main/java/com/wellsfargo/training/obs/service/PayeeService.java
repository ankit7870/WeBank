package com.wellsfargo.training.obs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.obs.model.Payee;
import com.wellsfargo.training.obs.repository.PayeeRepository;

@Service
public class PayeeService {

	@Autowired
	private PayeeRepository prepo;
	
	public  Payee savePayee(Payee p) {
		return prepo.save(p);
		
	}
	
	public List<Payee> listAll(){
		return prepo.findAll();
	}
	
}
