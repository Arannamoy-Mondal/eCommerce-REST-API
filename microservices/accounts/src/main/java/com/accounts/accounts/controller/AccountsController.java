package com.accounts.accounts.controller;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accounts.accounts.constants.AccountsConstants;
import com.accounts.accounts.dto.CustomerDto;
import com.accounts.accounts.dto.ErrorResponseDto;
import com.accounts.accounts.dto.ResponseDto;
import com.accounts.accounts.service.IAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Validated
@Tag(name = "CRUD API's for Accounts", description = "CRUD REST API's for Accounts")
public class AccountsController {
    // @Autowired
    private IAccountService iAccountService;

    @GetMapping("/java-version")
    public ResponseEntity<?> getJavaVersion(){
            try {
                // System.out.println(Runtime.version().toString());
                return ResponseEntity.status(HttpStatus.OK).body(System.getProperty("java.version"));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }

    @PostMapping("/create")
    @Operation(summary = "Create Account REST API", description = "REST API to create new Customer &  Account inside ")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    public ResponseEntity<?> createAccount(@Valid @RequestBody CustomerDto customerDto) {
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
    @Operation(summary = "Fetch Account Details REST API", description = "REST API to fetch Customer &  Account details based on a mobile number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    public ResponseEntity<?> fetchAccountDetails(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must be 10 number and contains numbers only") String mobileNumber) {
        try {

            return ResponseEntity.status(HttpStatus.OK).body(iAccountService.fetchAccount(mobileNumber));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/update")
    @Operation(summary = "Update Account Details REST API", description = "REST API to update Customer &  Account details based on a account number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    public ResponseEntity<?> methodName(@Valid @RequestBody CustomerDto customerDto) {

        boolean isUpdated = iAccountService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.status_200, AccountsConstants.message_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.status_417, AccountsConstants.message_417_update));
        }

    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete Account & Customer Details REST API", description = "REST API to delete Customer &  Account details based on a mobile number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    public ResponseEntity<?> methodName(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must be 10 number and contains numbers only") String mobileNumber) {
        System.out.println(mobileNumber);
        boolean isDeleted = iAccountService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.status_200, AccountsConstants.message_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.status_417, AccountsConstants.message_417_update));
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
