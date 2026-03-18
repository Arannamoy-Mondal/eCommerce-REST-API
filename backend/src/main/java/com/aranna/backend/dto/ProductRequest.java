package com.aranna.backend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String productName;
    private byte[] image;
    private String description;
    private Integer quantity;
    private double price;
    private double discount;
    private double specialPrice;
    private List<String> categories;
}
