package com.accounts.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.accounts.accounts.constants.AccountsConstants;
import com.accounts.accounts.dto.AccountsDto;
import com.accounts.accounts.dto.CustomerDto;
import com.accounts.accounts.entity.Accounts;
import com.accounts.accounts.entity.Customer;
import com.accounts.accounts.exception.CustomerAlreadyExistException;
import com.accounts.accounts.exception.ResourceNotFoundException;
import com.accounts.accounts.mapper.AccountsMapper;
import com.accounts.accounts.mapper.CustomerMapper;
import com.accounts.accounts.repo.AccountsRepo;
import com.accounts.accounts.repo.CustomerRepo;
import com.accounts.accounts.service.IAccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountsServiceImple implements IAccountService{
    private AccountsRepo accountsRepo;
    private CustomerRepo customerRepo;
    @Override
    public void createAccount(CustomerDto customerDto) {
      
        Customer customer=CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer=customerRepo.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistException(
              "Customer already registered with given mobile number"
              +customerDto.getMobileNumber()  
            );
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("null");

        Customer savedCustomer=customerRepo.save(customer);
        System.out.println(savedCustomer);
        accountsRepo.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
       Customer customer=customerRepo.findByMobileNumber(mobileNumber)
       .orElseThrow(
        ()->new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
       );
       Accounts accounts=accountsRepo.findByCustomerId(customer.getCustomerId())
       .orElseThrow(
        ()->new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
       );
       CustomerDto customerDto=CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
       customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

       return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated=false;
        AccountsDto accountsDto=customerDto.getAccountsDto();
        if(accountsDto!=null){
            Accounts accounts=accountsRepo.findById(accountsDto.getAccountNumber())
            .orElseThrow(()->new ResourceNotFoundException("Account", "AccountNumber",
             accountsDto.getAccountNumber().toString()));
             AccountsMapper.mapToAccounts(accounts, accountsDto);
             accounts=accountsRepo.save(accounts);
             Long customerId=accounts.getCustomerId();
             Customer customer=customerRepo.findById(customerId)
             .orElseThrow(()->
            new ResourceNotFoundException("Customer", "CustomerId", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepo.save(customer);
            isUpdated=true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        // System.out.println(mobileNumber);
        Customer customer=customerRepo.findByMobileNumber(mobileNumber)
        // .orElse(null);
        .orElseThrow(
            ()-> new ResourceNotFoundException("Customer", "mobile number", mobileNumber)
        );
        System.out.println(accountsRepo.findByCustomerId(customer.getCustomerId()));
        accountsRepo.deleteByCustomerId(customer.getCustomerId());
        customerRepo.deleteById(customer.getCustomerId());

        return true;
    }
    
    private Accounts createNewAccount(Customer customer){
        Accounts newAccount=new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber= 1000000000L+new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.Savings);
        newAccount.setBranchAddress(AccountsConstants.Address);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("null");
        return newAccount;
    }
}
