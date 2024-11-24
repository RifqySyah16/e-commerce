package com.rifqy.project.ecommerce.e_commerce.cartitem.model.dto;

import java.math.BigDecimal;

import com.rifqy.project.ecommerce.e_commerce.cart.model.dto.CartResponseDTO;
import com.rifqy.project.ecommerce.e_commerce.item.model.dto.ItemResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponseDTO {
    private Long id;
    private Integer quantity;
    private BigDecimal totalPrice;
    private CartResponseDTO cartResponseDTO;
    private ItemResponseDTO itemResponseDTO;
}
