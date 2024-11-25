package com.rifqy.project.ecommerce.e_commerce.cartitem;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rifqy.project.ecommerce.e_commerce.cartitem.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
