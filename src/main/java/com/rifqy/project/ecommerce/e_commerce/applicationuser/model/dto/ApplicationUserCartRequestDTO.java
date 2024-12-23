package com.rifqy.project.ecommerce.e_commerce.applicationuser.model.dto;

import com.rifqy.project.ecommerce.e_commerce.applicationuser.model.ApplicationUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUserCartRequestDTO {
    private Long id;

    public ApplicationUser convertToEntity() {
        return ApplicationUser.builder()
                .id(this.id)
                .build();
    }
}