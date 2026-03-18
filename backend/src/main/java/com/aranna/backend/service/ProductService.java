package com.aranna.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.aranna.backend.dto.ProductRequest;
import com.aranna.backend.model.Category;
import com.aranna.backend.model.Product;
import com.aranna.backend.repo.CategoryRepo;
import com.aranna.backend.repo.ProductRepo;

import jakarta.annotation.Nullable;

@Service
public class ProductService {
 
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    public @Nullable Object create(ProductRequest productRequest){
        Product product=Product.builder()
        .productName(productRequest.getProductName())
        .image(productRequest.getImage())
        .description(productRequest.getDescription())
        .quantity(productRequest.getQuantity())
        .price(productRequest.getPrice())
        .discount(productRequest.getDiscount())
        .specialPrice(productRequest.getSpecialPrice())
        .build();

        List<Category> categories=new ArrayList<>();
        for(String cat:productRequest.getCategories()){
            categories.add(categoryRepo.findByCategoryName(cat.toLowerCase()));
        }
        product.setCategories(categories);
        return productRepo.save(product);
        
    }

    public @Nullable Object readAll(){
        return productRepo.findAll();
        
    }

    public @Nullable Object readSpecific(Integer productId){
  
        var res=productRepo.findById(productId);
        if(res!=null){
            return res;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product Id not found");
    }

    public @Nullable Object update(Integer productId,Product entity)throws Exception{
        var product=productRepo.findById(productId).orElse(null);
        if(product!=null){
            if(entity.getProductName()!=null){
                product.setProductName(entity.getProductName());
            }
            if(entity.getDescription()!=null){
                product.setDescription(entity.getDescription());
            }
            if(entity.getQuantity()!=0){
                product.setDescription(entity.getDescription());
            }
            if(entity.getPrice()!=0){
                product.setPrice(entity.getPrice());
            }
            if(entity.getSpecialPrice()!=0){
                product.setSpecialPrice(entity.getSpecialPrice());
            }
            productRepo.save(product);
            return product;

        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product Id not Found");
    }



    public @Nullable Object delete(Integer productId){
        productRepo.deleteById(productId);
        var res=productRepo.findById(productId);
        if(res==null){
            return true;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product Id not found");
    }
}
