package com.rifqy.project.ecommerce.e_commerce.cartitem.model.dto;

import java.math.BigDecimal;

import com.rifqy.project.ecommerce.e_commerce.cart.model.Cart;
import com.rifqy.project.ecommerce.e_commerce.cart.model.dto.CartRequestDTO;
import com.rifqy.project.ecommerce.e_commerce.cartitem.model.CartItem;
import com.rifqy.project.ecommerce.e_commerce.item.model.Item;
import com.rifqy.project.ecommerce.e_commerce.item.model.dto.ItemRequestDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemRequestDTO {
    private Long id;

    @NotNull(message = "Quantity is required")
    private Integer quantity;

    @NotNull(message = "Total price is required")
    private BigDecimal totalPrice;

    @Valid
    private CartRequestDTO cartRequestDTO;

    @Valid
    private ItemRequestDTO itemRequestDTO;

    public CartItem convertToEntity() {
        Cart cart = this.cartRequestDTO.convertToEntity();
        Item item = this.itemRequestDTO.convertToEntity();

        return CartItem.builder()
                .id(this.id)
                .quantity(this.quantity)
                .totalPrice(this.totalPrice)
                .cart(cart)
                .item(item)
                .build();
    }
}
