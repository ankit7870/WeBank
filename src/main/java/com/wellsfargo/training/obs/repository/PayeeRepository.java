package com.wellsfargo.training.obs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.training.obs.model.Payee;

public interface PayeeRepository extends JpaRepository<Payee, Long> {
//  public Optional<Payee> findByaccountnumber(Long accountnumber);
}
