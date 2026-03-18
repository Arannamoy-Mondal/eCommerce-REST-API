package com.aranna.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aranna.backend.model.Product;


@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

}
