package com.rifqy.project.ecommerce.e_commerce.applicationuser.model.dto;

import java.math.BigDecimal;

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
public class RegisterationResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;
    private BigDecimal balance;
    private RoleName roleName;
}