package com.rifqy.project.ecommerce.e_commerce.cart;

import com.rifqy.project.ecommerce.e_commerce.cart.model.Cart;
import com.rifqy.project.ecommerce.e_commerce.cartitem.model.CartItem;

public interface CartService {

    public Cart add(CartItem newCartItem);
    public void remove(Long id);
}
