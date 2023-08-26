package com.wellsfargo.training.obs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.training.obs.exception.ResourceNotFoundException;
import com.wellsfargo.training.obs.model.Account;
import com.wellsfargo.training.obs.model.Customer;
import com.wellsfargo.training.obs.service.AccountService;
import com.wellsfargo.training.obs.service.CustomerService;


@RestController
@RequestMapping("/api")
public class AccountController {
	
	@Autowired
	private AccountService aservice;
	
	@Autowired
	private CustomerService cservice;
	

	
	@PostMapping("/register")
	public ResponseEntity<String> createAccount (@Validated @RequestBody Account account) throws ResourceNotFoundException {
		
		Long accountno = account.getAccountno();
		Boolean f = false;
		
		Customer c = cservice.registerAccount(accountno).orElseThrow(() -> new ResourceNotFoundException("cust not in db"));
		
		if(accountno==c.getCustomerid()) {
			f=true;
		}
		
		if(f) {
			Account registeredAccount = aservice.registerAccount(account);
			if(registeredAccount!=null) {
				return ResponseEntity.ok("okay done");
				} else {
					return ResponseEntity.badRequest().body("not done");
		}
		}else {
			return ResponseEntity.badRequest().body("not done");
		}
			
		
	}
		
	
	@PostMapping("/login")
	public Boolean loginAccount(@Validated @RequestBody Account account) throws ResourceNotFoundException {
		Boolean a = false;
		Long aid = account.getAccountno();
		String password = account.getPassword();
		
		Account ac = aservice.loginAccount(aid).orElseThrow(() -> new ResourceNotFoundException("Account not found"));
		
		if(aid==ac.getAccountno() && password.equals(ac.getPassword())) {
			a = true;
		}
		return a;
	}
}
