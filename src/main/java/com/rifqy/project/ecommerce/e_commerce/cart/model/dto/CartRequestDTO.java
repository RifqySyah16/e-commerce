package com.rifqy.project.ecommerce.e_commerce.cart.model.dto;

import com.rifqy.project.ecommerce.e_commerce.applicationuser.model.ApplicationUser;
import com.rifqy.project.ecommerce.e_commerce.applicationuser.model.dto.ApplicationUserCartRequestDTO;
import com.rifqy.project.ecommerce.e_commerce.cart.model.Cart;

import jakarta.validation.Valid;
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

    @Valid
    private ApplicationUserCartRequestDTO applicationUserCartRequestDTO;

    public Cart convertToEntity() {
        ApplicationUser applicationUser = this.applicationUserCartRequestDTO.convertToEntity();

        return Cart.builder()
                .id(this.id)
                .applicationUser(applicationUser)
                .build();
    }
}