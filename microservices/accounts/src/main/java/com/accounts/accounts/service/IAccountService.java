package com.accounts.accounts.service;

import org.springframework.stereotype.Service;

import com.accounts.accounts.dto.CustomerDto;

@Service
public interface IAccountService {
    void createAccount(CustomerDto customerDto);
    CustomerDto fetchAccount(String mobileNumber);
    boolean updateAccount(CustomerDto customerDto);
    boolean deleteAccount(String mobileNumber);
    
}
