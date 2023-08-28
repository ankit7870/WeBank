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
import com.wellsfargo.training.obs.model.Admin;
import com.wellsfargo.training.obs.service.AdminService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adservice;
	
	@PostMapping("/register")
	public ResponseEntity<String> createAdmin (@Validated @RequestBody Admin admin) {
		Admin registeredAdmin = adservice.registerAdmin(admin);
		if(registeredAdmin!=null) {
			return ResponseEntity.ok("Admin added");
		} else {
			return ResponseEntity.badRequest().body("Error occurred while adding admin");
		}
	}
	
	@PostMapping("/login")
	public Boolean loginAdmin(@Validated @RequestBody Admin admin) throws ResourceNotFoundException {
		Boolean a = false;
		String adname = admin.getName();
		String pswd = admin.getPassword();
		
		Admin ad = adservice.loginAdmin(adname).orElseThrow(() -> new ResourceNotFoundException("Admin not found"));
		
		if(adname.equals(ad.getName()) && pswd.equals(ad.getPassword())) {
			a = true;
		}
		
		return a;
	}

}
