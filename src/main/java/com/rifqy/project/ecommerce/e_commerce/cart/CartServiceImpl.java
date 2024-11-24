package com.rifqy.project.ecommerce.e_commerce.cart;

import org.springframework.stereotype.Service;

import com.rifqy.project.ecommerce.e_commerce.cart.model.Cart;
import com.rifqy.project.ecommerce.e_commerce.cartitem.CartItemService;
import com.rifqy.project.ecommerce.e_commerce.cartitem.model.CartItem;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{
    private final CartRepository cartRepository;
    private final CartItemService cartItemService;

    @Override
    public Cart add(CartItem newCartItem) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public void remove(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }
}
