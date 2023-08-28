package com.wellsfargo.training.obs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.training.obs.exception.ResourceNotFoundException;
import com.wellsfargo.training.obs.model.Account;
import com.wellsfargo.training.obs.model.Customer;
import com.wellsfargo.training.obs.model.Payee;
import com.wellsfargo.training.obs.service.CustomerService;
import com.wellsfargo.training.obs.service.PayeeService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value="/api")
public class PayeeController {

	@Autowired
	private PayeeService pservice;

	@Autowired
	private CustomerService cservice;
	
	@PostMapping("/payee")
	public ResponseEntity<String> completePayee(@Validated @RequestBody Payee payee) {
		
//		String payeeAccountNo = payee.getPayeeAccountNo();
//		Boolean f = false;
//		
//		Customer c = cservice.registerAccount(payeeAccountNo).orElseThrow(() -> new ResourceNotFoundException("Customer Account not found"));
//		
//		if(payeeAccountNo==c.getPayeeAccountNo()) {
//			f=true;
//		}
//		
//		if(f) {
//			Payee registerPayee = pservice.savePayee(payee);
//			if(registerPayee!=null) {
//				return ResponseEntity.ok("payee add done");
//				} else {
//					return ResponseEntity.badRequest().body("not done");
//		}
//		}else {
//			return ResponseEntity.badRequest().body("not done");
//		}

		Payee registerPayee = pservice.savePayee(payee);
		if(registerPayee!=null) {
			return ResponseEntity.ok("payee add done");
			} else {
				return ResponseEntity.badRequest().body("not done");
			}
	}

}