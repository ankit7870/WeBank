package com.wellsfargo.training.obs.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.obs.model.Account;
import com.wellsfargo.training.obs.repository.AccountRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AccountService {
	
	@Autowired
	private AccountRepository arepo;
	
	public Account registerAccount(Account a) {
		return arepo.save(a);
	}
	
	public Optional<Account> loginAccount (Long accountno) {
		return arepo.findByAccountno(accountno);
	}

	public Account getAccountsById(Long toAcc) {
		// TODO Auto-generated method stub
		return arepo.getById(toAcc);
	}

	public void saveAccounts(Account a) {
		// TODO Auto-generated method stub
		arepo.save(a);
		
	}

	

}
