package com.rifqy.project.ecommerce.e_commerce.cart;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rifqy.project.ecommerce.e_commerce.cart.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByApplicationUserId(Long userId);

}
