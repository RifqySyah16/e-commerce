package com.rifqy.project.ecommerce.e_commerce.cartitem.model;

import java.math.BigDecimal;

import com.rifqy.project.ecommerce.e_commerce.cart.model.Cart;
import com.rifqy.project.ecommerce.e_commerce.cart.model.dto.CartResponseDTO;
import com.rifqy.project.ecommerce.e_commerce.cartitem.model.dto.CartItemResponseDTO;
import com.rifqy.project.ecommerce.e_commerce.item.model.Item;
import com.rifqy.project.ecommerce.e_commerce.item.model.dto.ItemResponseDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    public CartItemResponseDTO convertToResponse() {
        CartResponseDTO cartResponseDTO = this.cart.convertToResponse();
        ItemResponseDTO itemResponseDTO = this.item.convertToResponse();

        return CartItemResponseDTO.builder()
                .id(this.id)
                .quantity(this.quantity)
                .totalPrice(this.totalPrice)
                .cartResponseDTO(cartResponseDTO)
                .itemResponseDTO(itemResponseDTO)
                .build();
    }
}
