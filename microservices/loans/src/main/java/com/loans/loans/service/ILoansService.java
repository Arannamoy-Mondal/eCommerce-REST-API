package com.loans.loans.service;

import org.springframework.stereotype.Service;

import com.loans.loans.dto.LoansDto;
@Service
public interface ILoansService {
    void createLoan(String mobileNumber) throws Exception;

    LoansDto fetchLoan(String mobileNumber) throws Exception;

    boolean updateLoan(LoansDto loansDto) throws Exception;

    boolean deleteLoan(String mobileNumber) throws Exception;
}
