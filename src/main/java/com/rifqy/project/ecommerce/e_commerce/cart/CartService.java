package com.rifqy.project.ecommerce.e_commerce.cart;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.rifqy.project.ecommerce.e_commerce.cart.model.Cart;
import com.rifqy.project.ecommerce.e_commerce.cartitem.CartItemService;
import com.rifqy.project.ecommerce.e_commerce.cartitem.model.CartItem;
import com.rifqy.project.ecommerce.e_commerce.item.ItemService;
import com.rifqy.project.ecommerce.e_commerce.item.model.Item;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ItemService itemService;
    private final CartRepository cartRepository;
    private final CartItemService cartItemService;

    public Cart addItem(Integer quantity, Item item, Cart cart, CartItem cartItem) {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity must be greater than zero");
        }

        Item existingItem = this.itemService.getOne(item.getId());
        this.itemService.isStockAvailable(existingItem, quantity);

        Cart existingCart = this.create(cart);

        CartItem existingCartItem = this.cartItemService.getOne(cartItem.getId());
        existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
        existingCartItem.setTotalPrice(BigDecimal.valueOf(existingCartItem.getQuantity()).multiply(item.getPrice()));
        CartItem savedCartItem = this.cartItemService.create(existingItem, existingCart);

        return this.itemService.reduceStock(savedCartItem.getItem(), quantity);
    }

    private Cart create(Cart newCart) {
        return this.cartRepository.findByApplicationUserId(newCart.getApplicationUser().getId())
                .orElseGet(() -> this.cartRepository.save(newCart));
    }

    public void remove(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }
}
