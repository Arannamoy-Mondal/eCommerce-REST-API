package com.loans.loans.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loans.loans.constants.LoansConstants;
import com.loans.loans.dto.LoansDto;
import com.loans.loans.entity.Loans;
import com.loans.loans.mapper.LoansMapper;
import com.loans.loans.repo.LoansRepo;
import com.loans.loans.service.ILoansService;
@Service
public class LoansServiceImpl implements ILoansService{
    @Autowired
    private LoansRepo loansRepo;
    @Override
    public void createLoan(String mobileNumber) throws Exception {
       Loans loans=loansRepo.findByMobileNumber(mobileNumber).orElse(null);
       if(loans==null){
        loansRepo.save(createLoans(mobileNumber));
       }
       else{
        throw new Exception("Loan already registered with given mobileNumber "+mobileNumber);
       }
       
    }

    public Loans createLoans(String mobileNumber){
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }


    @Override
    public LoansDto fetchLoan(String mobileNumber) throws Exception {
        Loans loans=loansRepo.findByMobileNumber(mobileNumber).orElse(null);
       if(loans==null){
        throw new Exception("No loan found");
       }
       LoansDto loansDto=LoansMapper.mapToLoansDto(loans, new LoansDto());
       return loansDto;
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateLoan'");
    }

    @Override
    public boolean deleteLoan(String mobileNumber) throws Exception {
        loansRepo.deleteByMobileNumber(mobileNumber);
        Loans loans=loansRepo.findByMobileNumber(mobileNumber).orElse(null);
        if(loans!=null){
            throw new Exception("Something went wrong."+loans);
        }
        return true;
    }

}
