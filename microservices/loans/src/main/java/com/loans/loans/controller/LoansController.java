package com.loans.loans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loans.loans.service.ILoansService;


@RestController
@RequestMapping("/api")
public class LoansController {


    @Autowired
    private ILoansService iLoansService;


    @PostMapping("/create")
    public ResponseEntity<?> createLoan(@RequestParam String mobileNumber){
            try {
                iLoansService.createLoan(mobileNumber);
                return ResponseEntity.status(HttpStatus.OK).body("Loan created successfully.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }

    @GetMapping("/fetch")
    public ResponseEntity<?> fetch(@RequestParam String mobileNumber){
            try {
                return ResponseEntity.status(HttpStatus.OK).body(iLoansService.fetchLoan(mobileNumber));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam String mobileNumber){
            try {
                return ResponseEntity.status(HttpStatus.OK).body(iLoansService.deleteLoan(mobileNumber));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }
}
