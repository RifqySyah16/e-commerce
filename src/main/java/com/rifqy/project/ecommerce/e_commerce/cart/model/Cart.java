package com.rifqy.project.ecommerce.e_commerce.cart.model;

import java.util.List;

import com.rifqy.project.ecommerce.e_commerce.applicationuser.model.ApplicationUser;
import com.rifqy.project.ecommerce.e_commerce.cart.model.dto.CartResponseDTO;
import com.rifqy.project.ecommerce.e_commerce.cartitem.model.CartItem;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private ApplicationUser applicationUser;

    @OneToMany
    private List<CartItem> cartItems;

    public CartResponseDTO convertToResponse() {
        return CartResponseDTO.builder()
                .id(this.id)
                .build();
    }
}