package com.wellsfargo.training.obs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.wellsfargo.training.obs.service.AccountService;
import com.wellsfargo.training.obs.service.CustomerService;
import com.wellsfargo.training.obs.service.TransactionService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.training.obs.exception.ResourceNotFoundException;
import com.wellsfargo.training.obs.model.Account;
import com.wellsfargo.training.obs.model.Customer;
import com.wellsfargo.training.obs.model.Transaction;


@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
	@Autowired private TransactionService TransactionService;
	@Autowired private AccountService accountsService;
	@Autowired private AccountController accountcont;
	
//	@Autowired private BCryptPasswordEncoder passwordEncoder;
	private ObjectMapper objectMapper;
//	private Logger LOGGER = LoggerFactory.getLogger(getClass())	;
	public TransactionController(AccountController a,TransactionService TransactionService, CustomerService userService, 
			AccountService accountService) {
		super();
		this.accountcont=a;
		this.TransactionService = TransactionService;
		this.accountsService = accountService;
//		this.passwordEncoder = passwordEncoder;
		this.objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	@PostMapping()
	public ResponseEntity<Map<String,String>> saveTransaction(@RequestBody JsonNode jsonNode) throws JsonMappingException, JsonProcessingException{
//		JsonNode jsonNode = objectMapper.readTree(requestBody);
		Customer user;
		Map<String,String>mp = new HashMap<>();
		try {
			Long fromAcc = jsonNode.get("fromacc").asLong();
			
		}
		catch (Exception e) {
			mp.put("message", e.getMessage());
			return new ResponseEntity<>(mp, HttpStatus.UNAUTHORIZED);
		}
		//Long transId;
		try {
			Long fromAcc = jsonNode.get("fromacc").asLong();
			Long toAcc = jsonNode.get("toacc").asLong();
			
			
			Account toAccount = accountsService.getAccountByNo(toAcc).orElseThrow(() -> new ResourceNotFoundException("Account not found"));;
			if(toAccount==null )
				throw new Exception(toAcc + " does not exists");
			Account fromAccount = accountsService.getAccountByNo(fromAcc).orElseThrow(() -> new ResourceNotFoundException("Account not found"));;
			Long amount = jsonNode.get("amount").asLong();
			Long balance = fromAccount.getBalance();
			String type = jsonNode.get("type").toString();
			String date = jsonNode.get("date").toString();
			
//			if(fromAcc != user.getAccount().getAccNumber()) {
//				throw new Exception("Transaction can be done by your account only");
//			}
//			LOGGER.info(user.getPin() + ":"+user.getUserId());
			if(!(jsonNode.get("transactionpassword").asText().equals( fromAccount.getTransactionpassword()))) {
				throw new Exception("Invalid Pin");
			}
			
//			if(accountsService.existsById(toAcc)==false) {
//				throw new Exception("Transaction done to an invalid account");
//			}
			
			if(type.equals("RTGS")) {
				if((amount.compareTo((long) 200000) == -1)&& (amount.compareTo((long)1000000) == 1)) {
					throw new Exception("Transaction amount should be between 2 Lakhs and 10 Lakhs");
				}
			}
			else if(type.equals("IMPS")) {
				if(amount.compareTo((long)500000) == 1) {
					throw new Exception("Transaction amount should be less than 5 Lakhs");
				}
			}
			else {
				if(amount.compareTo((long)1000000) == 1) {
					throw new Exception("Transaction amount should be less than 10 Lakhs");
				}
			}
			if(amount.compareTo(balance) == 1) {
				throw new Exception("Insufficent balance");
			}
			
			Long toBalance = toAccount.getBalance();
			balance = balance-amount;
			toBalance = toBalance+amount;
			fromAccount.setBalance(toBalance);
			toAccount.setBalance(balance);
			Account a=accountsService.getAccountByNo(toAcc).orElseThrow(()->new ResourceNotFoundException("Product not found for this Id : "));;
			a.setBalance(toBalance);
			Account b=accountsService.getAccountByNo(fromAcc).orElseThrow(()->new ResourceNotFoundException("Product not found for this Id : "));
			b.setBalance(balance);
			


			
			
			Transaction transaction = objectMapper.treeToValue(jsonNode, Transaction.class);
			transaction.setFromacc(fromAcc);
			transaction.setToacc(toAcc);
			transaction.setAmount(amount);
			transaction.setDate(date);
			transaction.setType(type);
			
			TransactionService.saveTransaction(transaction);
		}
		catch (Exception e) {
			mp.put("message", e.getMessage());
			return new ResponseEntity<>(mp,HttpStatus.BAD_REQUEST);
		}
			mp.put("message", "Transaction Succesful");
			//mp.put("transId", Long.toString(transId));
			return new ResponseEntity<>(mp, HttpStatus.CREATED);
	}
	
//	@GetMapping("transaction")
//	public List<Transaction> getAllTransaction(){
//		return TransactionService.getAllTransaction();
//	}

	@GetMapping("/{userid}")
	public List<Transaction> getCustTransaction(@PathVariable(value="userid") Long cId){
		//System.out.println(cId);
		List<Transaction> t= TransactionService.getAllTransaction();
		List<Transaction> trans=new ArrayList<Transaction>();
		for(Transaction x:t) {
			if(x.getFromacc().equals(cId) ) trans.add(x);
		}
		
		for(Transaction x:t) {
			if(x.getToacc().equals(cId)) trans.add(x);
		}
		return trans;
	}
}
