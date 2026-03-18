package com.accounts.accounts.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accounts.accounts.entity.Accounts;



public interface AccountsRepo extends JpaRepository<Accounts,Long>{
    Optional<Accounts> findByCustomerId(Long customerId);
}
