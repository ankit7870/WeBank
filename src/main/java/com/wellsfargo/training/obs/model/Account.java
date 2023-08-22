package com.wellsfargo.training.obs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToOne;
import lombok.ToString;

@ToString
@Entity
public class Account {
	@Id   // Primary key /unique
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Accountid;
	
	
	private Long accountno;
	private String password;
	private String transactionpassword;
	private Long balance;
	private String type;
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(Long accountid, Long accountno, String password, String transactionpassword, Long balance,
			String type) {
		Accountid = accountid;
		this.accountno = accountno;
		this.password = password;
		this.transactionpassword = transactionpassword;
		this.balance = balance;
		this.type = type;
	}
	public Long getAccountid() {
		return Accountid;
	}
	public void setAccountid(Long accountid) {
		Accountid = accountid;
	}
	public Long getAccountno() {
		return accountno;
	}
	public void setAccountno(Long accountno) {
		this.accountno = accountno;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTransactionpassword() {
		return transactionpassword;
	}
	public void setTransactionpassword(String transactionpassword) {
		this.transactionpassword = transactionpassword;
	}
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}
