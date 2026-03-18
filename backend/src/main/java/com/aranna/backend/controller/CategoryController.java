package com.aranna.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aranna.backend.model.Category;
import com.aranna.backend.service.CategoryService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/public/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/all")
    public ResponseEntity<?> getAllCategory(@RequestParam(required = false) String param) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody Category entity) {   
        try {
            entity.setCategoryName(entity.getCategoryName().toLowerCase());
            var category=categoryService.save(entity);
            return ResponseEntity.status(HttpStatus.OK).body(category);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @PutMapping("/{categoryName}")
    public ResponseEntity<?> updateCategory(@PathVariable("categoryName") String categoryName, @RequestBody(required = false) Category entity) {
        
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(categoryName, entity));
    }



    @DeleteMapping("/{categoryName}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryName") String categoryName){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.deleteCategory(categoryName));
    }
}
