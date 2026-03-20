package com.accounts.accounts.service.feignService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accounts.accounts.dto.CardsDto;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient("cards")
public interface CardsFeignClient {
    @PostMapping("/api/create")
    public ResponseEntity<?> create(@RequestParam String mobileNumber);

    @GetMapping("/api/fetch")
    public ResponseEntity<?> fetch(
            @RequestParam String mobileNumber);

    @PutMapping("/api/update")
    public ResponseEntity<?> update(
            @RequestBody CardsDto cardsDto);

    @DeleteMapping("/api/delete")
    public ResponseEntity<?> delete(@RequestParam String mobileNumber);
}
