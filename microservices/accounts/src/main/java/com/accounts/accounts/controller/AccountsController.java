package com.accounts.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accounts.accounts.constants.AccountsConstants;
import com.accounts.accounts.dto.CustomerDto;
import com.accounts.accounts.service.IAccountService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AccountsController {
    // @Autowired
    private IAccountService iAccountService;

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody CustomerDto customerDto) {
        // try {
        // System.out.println(customerDto);
        iAccountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.OK).body(AccountsConstants.message_201);
        // } catch (Exception e) {
        // System.out.println(e.getMessage());
        // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        // }
    }

    @GetMapping("/fetch")
    public ResponseEntity<?> fetchAccountDetails(@RequestParam String mobileNumber) {
        try {

            return ResponseEntity.status(HttpStatus.OK).body(iAccountService.fetchAccount(mobileNumber));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getHello() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body("Hello");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("null");
        }
    }
}
