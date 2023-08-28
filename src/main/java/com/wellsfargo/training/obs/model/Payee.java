package com.wellsfargo.training.obs.model;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToOne;

@Entity
public class Payee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long payeeID;
	private String payeeNickname;
	private String payeeAccountNo;
	private String payerAccountNo;
	
	public Payee() {
	}

	public Payee(Long payeeID, String payeeNickname, String payeeAccountNo, String payerAccountNo) {
		this.payeeID = payeeID;
		this.payeeNickname = payeeNickname;
		this.payeeAccountNo = payeeAccountNo;
		this.payerAccountNo = payerAccountNo;
	}

	public Long getPayeeID() {
		return payeeID;
	}

	public void setPayeeID(Long payeeID) {
		this.payeeID = payeeID;
	}

	public String getPayeeNickname() {
		return payeeNickname;
	}

	public void setPayeeNickname(String payeeNickname) {
		this.payeeNickname = payeeNickname;
	}

	public String getPayeeAccountNo() {
		return payeeAccountNo;
	}

	public void setPayeeAccountNo(String payeeAccountNo) {
		this.payeeAccountNo = payeeAccountNo;
	}

	public String getPayerAccountNo() {
		return payerAccountNo;
	}

	public void setPayerAccountNo(String payerAccountNo) {
		this.payerAccountNo = payerAccountNo;
	}
	
}