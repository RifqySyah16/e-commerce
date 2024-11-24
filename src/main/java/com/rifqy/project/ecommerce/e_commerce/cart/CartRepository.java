package com.rifqy.project.ecommerce.e_commerce.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rifqy.project.ecommerce.e_commerce.cart.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
