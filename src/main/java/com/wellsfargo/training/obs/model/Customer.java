package com.wellsfargo.training.obs.model;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Base64;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="customers")
public class Customer {
	
		@SequenceGenerator(name="customer_seq", initialValue = 2023000, allocationSize = 1)
		@Id   // Primary key /unique
		@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "customer_seq")
		private Long customerid;
		
		@Column(unique=true)
		private String email;
		
		private String title;
		
		private String fname;
		
		private String mname;

		private String lname;
		
		private String fathername;
		
		private String aadharno;
		
		
		
		@JsonFormat(pattern="yyyy-MM-dd")
		private Date dob;
		
		@Column(unique=true)
		private String mobileno;
		
		/* Model 1-1 Mapping b/w User & Address*/
		
		@OneToOne(mappedBy ="customer", cascade = CascadeType.ALL)
		private Address address;
		
		@OneToOne(mappedBy ="customer", cascade = CascadeType.ALL)
		private Occupation occupation;

		public Customer() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Customer(Long customerid, String email, String title, String fname, String mname, String lname,
				String fathername, String aadharno, Date dob, String mobileno, Address address, Occupation occupation) {
			this.customerid = customerid;
			this.email = email;
			this.title = title;
			this.fname = fname;
			this.mname = mname;
			this.lname = lname;
			this.fathername = fathername;
			this.aadharno = aadharno;
			this.dob = dob;
			this.mobileno = mobileno;
			this.address = address;
			this.occupation = occupation;
		}

		public Long getCustomerid() {
			return customerid;
		}

		public void setCustomerid(Long customerid) {
			this.customerid = customerid;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getFname() {
			return fname;
		}

		public void setFname(String fname) {
			this.fname = fname;
		}

		public String getMname() {
			return mname;
		}

		public void setMname(String mname) {
			this.mname = mname;
		}

		public String getLname() {
			return lname;
		}

		public void setLname(String lname) {
			this.lname = lname;
		}

		public String getFathername() {
			return fathername;
		}

		public void setFathername(String fathername) {
			this.fathername = fathername;
		}

		public String getAadharno() {
			return aadharno;
		}

		public void setAadharno(String aadharno) {
			this.aadharno = aadharno;
		}

		public Date getDob() {
			return dob;
		}

		public void setDob(Date dob) {
			this.dob = dob;
		}

		public String getMobileno() {
			return mobileno;
		}

		public void setMobileno(String mobileno) {
			this.mobileno = mobileno;
		}

		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		public Occupation getOccupation() {
			return occupation;
		}

		public void setOccupation(Occupation occupation) {
			this.occupation = occupation;
		}

		

}
