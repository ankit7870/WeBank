package com.wellsfargo.training.obs.controller;

//package com.example.wfbank.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wellsfargo.training.obs.service.AccountService;
import com.wellsfargo.training.obs.service.CustomerService;
import com.wellsfargo.training.obs.service.TransactionService; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.training.obs.model.Account;
import com.wellsfargo.training.obs.model.Customer;
import com.wellsfargo.training.obs.model.Transaction;


@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
	@Autowired private TransactionService TransactionService;
	@Autowired private AccountService accountsService;
	@Autowired private CustomerService userService;
//	@Autowired private BCryptPasswordEncoder passwordEncoder;
	private ObjectMapper objectMapper;
	private Logger LOGGER = LoggerFactory.getLogger(getClass())	;
	public TransactionController(TransactionService TransactionService, CustomerService userService, 
			AccountService accountService) {
		super();
		this.TransactionService = TransactionService;
		this.userService = userService;
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
		Long transId;
		try {
			Long fromAcc = jsonNode.get("fromacc").asLong();
			Long toAcc = jsonNode.get("toacc").asLong();
			
			
			Account toAccount = accountsService.getAccountsById(toAcc);
			if(toAccount==null )
				throw new Exception(toAcc + " does not exists");
			Account fromAccount = accountsService.getAccountsById(fromAcc);
			Long amount = jsonNode.get("amount").asLong();
			Long balance = fromAccount.getBalance();
			String type = jsonNode.get("type").toString();
			String date = jsonNode.get("date").toString();
			
//			if(fromAcc != user.getAccount().getAccNumber()) {
//				throw new Exception("Transaction can be done by your account only");
//			}
//			LOGGER.info(user.getPin() + ":"+user.getUserId());
//			if(!(passwordEncoder.matches(jsonNode.get("pin").asText(), user.getPin()))) {
//				throw new Exception("Invalid Pin");
//			}
			
//			if(accountsService.existsById(toAcc)==false) {
//				throw new Exception("Transaction done to an invalid account");
//			}
//			
//			if(type.equals("RTGS")) {
//				if((amount.compareTo(BigDecimal.valueOf(200000)) == -1)&& (amount.compareTo(BigDecimal.valueOf(1000000)) == 1)) {
//					throw new Exception("Transaction amount should be between 2 Lakhs and 10 Lakhs");
//				}
//			}
//			else if(type.equals("IMPS")) {
//				if(amount.compareTo(BigDecimal.valueOf(500000)) == 1) {
//					throw new Exception("Transaction amount should be less than 5 Lakhs");
//				}
//			}
//			else {
//				if(amount.compareTo(BigDecimal.valueOf(1000000)) == 1) {
//					throw new Exception("Transaction amount should be less than 10 Lakhs");
//				}
//			}
//			if(amount.compareTo(balance) == 1) {
//				throw new Exception("Insufficent balance");
//			}
//			
			Long toBalance = toAccount.getBalance();
			balance = balance-amount;
			toBalance = toBalance+amount;
			fromAccount.setBalance(toBalance);
			toAccount.setBalance(balance);
			
			accountsService.saveAccounts(fromAccount);
			accountsService.saveAccounts(toAccount);
			

			
			
			Transaction transaction = objectMapper.treeToValue(jsonNode, Transaction.class);
			transaction.setFromacc(fromAcc);
			transaction.setToacc(toAcc);
			transaction.setAmount(amount);
			transaction.setDate(date);
			transaction.setType(type);
			//transaction.setTransid(transId);
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
	
//	@GetMapping
//	public List<Transaction> getAllTransaction(){
//		return TransactionService.getAllTransaction();
//	}
	
//	@GetMapping("accNumber")
//	public ResponseEntity<List<Transaction>> getUsersTransaction() {
//		long accNumber = userService.getCurrentUser().getAccount().getAccNumber();
//		List<Transaction> transaction = TransactionService.findByUserId(accNumber);
//		if(transaction.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>(transaction, HttpStatus.OK);
//	}
}

