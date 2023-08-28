package com.wellsfargo.training.obs.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.obs.model.Admin;
import com.wellsfargo.training.obs.repository.AdminRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminService {
	
	@Autowired
	private AdminRepository adrepo;
	
	public Admin registerAdmin(Admin adm) {
		return adrepo.save(adm);
	}
	
	public Optional<Admin> loginAdmin (String name){
		return adrepo.findByName(name);
	}

}
