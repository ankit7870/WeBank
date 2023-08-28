package com.wellsfargo.training.obs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

@CrossOrigin(origins="http://localhost:3000")
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
		
		Customer c = cservice.registerAccount(accountno).orElseThrow(() -> new ResourceNotFoundException("Customer Account not found"));
		
		if(accountno==c.getCustomerid()) {
			f=true;
		}
		
		if(f) {
			Account registeredAccount = aservice.registerAccount(account);
			if(registeredAccount!=null) {
				return ResponseEntity.ok("Netbanking Registration successful!");
				} else {
					return ResponseEntity.badRequest().body("Error occured during netbanking registration");
		}
		}else {
			return ResponseEntity.badRequest().body("Error occured during netbanking registration");
		}
			
		
	}
		
	
	@PostMapping("/login")
	public Boolean loginAccount(@Validated @RequestBody Account account) throws ResourceNotFoundException {
		Boolean a = false;
		Long aid = account.getAccountno();
		String password = account.getPassword();
		
		Account ac = aservice.loginAccount(aid).orElseThrow(() -> new ResourceNotFoundException("Account not found"));
		
		if(aid.equals(ac.getAccountno()) && password.equals(ac.getPassword())) {
			a = true;
		}
		return a;
	}
	
	
	@GetMapping("/accounts")
	public List<Account> getAllAccounts(){
		try {
			return aservice.listAll();
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return aservice.listAll();
	}
	
	@GetMapping("/accounts/{accountno}")
	public ResponseEntity<Account> getAccountByAccountNo(@PathVariable(value="accountno") Long aNo) throws ResourceNotFoundException {
		Account a = aservice.getAccountByNo(aNo).orElseThrow(()->new ResourceNotFoundException("Account not found having Account No : "+aNo));
		
		return ResponseEntity.ok().body(a);
	}
	
	@PutMapping("/accounts/{accountno}")
	public ResponseEntity<Account> updateAccount(@PathVariable(value="accountno") Long aNo, @Validated @RequestBody Account a) throws ResourceNotFoundException {
		Account account = aservice.getAccountByNo(aNo).orElseThrow(()-> new ResourceNotFoundException("Account not found having Account No : "+aNo));
		
		account.setBalance(a.getBalance());
		account.setType(a.getType());
		
		final Account updateAccount = aservice.registerAccount(account);
		
		return ResponseEntity.ok().body(updateAccount);
	}
	
	@DeleteMapping("/accounts/{accountno}")
	public Map<String, Boolean> deleteAccount(@PathVariable(value="acocuntno") Long aNo) throws ResourceNotFoundException {
		
		aservice.getAccountByNo(aNo).orElseThrow(() -> new ResourceNotFoundException("Account not found having Account No : "+aNo));
		aservice.deleteAccount(aNo);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		
		return response; 
	}
}
