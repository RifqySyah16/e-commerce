package com.rifqy.project.ecommerce.e_commerce.cart.model.dto;

import com.rifqy.project.ecommerce.e_commerce.cart.model.Cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartRequestDTO {
    private Long id;

    public Cart convertToEntity() {
        return Cart.builder()
                .id(this.id)
                .build();
    }
}