package com.aranna.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aranna.backend.model.Cart;

public interface CartRepo extends JpaRepository<Cart,Integer> {

}
