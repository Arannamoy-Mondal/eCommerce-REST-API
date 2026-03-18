package com.aranna.backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.aranna.backend.dto.ProductRequest;
import com.aranna.backend.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PostMapping;




@RestController
@RequestMapping("api/public/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @PostMapping(value = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?>  postMethodName(
    @RequestPart("entity") ProductRequest entity,
    @RequestPart("image") MultipartFile image) {
        try {
            entity.setImage(image.getBytes());
            
            return ResponseEntity.status(HttpStatus.OK).body(productService.create(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        }
    }
    

    @GetMapping("")
    public ResponseEntity<?> getAllProduct(@RequestParam(required = false) String param) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.readAll());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getSpecificProduct(@RequestParam(required = false) String param,@PathVariable("productId") Integer productId) {
        try {

            return ResponseEntity.status(HttpStatus.OK).body(productService.readSpecific(productId));
        }
        catch (ResponseStatusException e){
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    
}
