package com.wellsfargo.training.obs.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Transaction")
public class Transaction {
	@Id   // Primary key /unique
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transid;
	@Column(name="fromacc")
	private Long fromacc;
	private Long amount;
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	private Long toacc;
	
	private String type;
	private String date;
	public Transaction(Long transid, Long fromacc, Long amount, Long toacc, String type, String date) {
		this.transid = transid;
		this.fromacc = fromacc;
		this.amount = amount;
		this.toacc = toacc;
		this.type = type;
		this.date = date;
	}
	public Long getTransid() {
		return transid;
	}
	public void setTransid(Long transid) {
		this.transid = transid;
	}
	public Long getFromacc() {
		return fromacc;
	}
	public void setFromacc(Long fromacc) {
		this.fromacc = fromacc;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getToacc() {
		return toacc;
	}
	public void setToacc(Long toacc) {
		this.toacc = toacc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
