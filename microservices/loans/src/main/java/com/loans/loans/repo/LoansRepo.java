package com.loans.loans.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.loans.loans.entity.Loans;

import jakarta.transaction.Transactional;

@Repository
public interface LoansRepo extends JpaRepository<Loans,Long> {

    Optional<Loans> findByMobileNumber(String mobileNumber);

    Optional<Loans> findByLoanNumber(String loanNumber);

    @Transactional
    @Modifying
    void deleteByMobileNumber(String mobileNumber);
} 
