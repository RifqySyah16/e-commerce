package com.rifqy.project.ecommerce.e_commerce.applicationuser.model;

import java.math.BigDecimal;

import com.rifqy.project.ecommerce.e_commerce.applicationuser.model.dto.RegisterationResponseDTO;
import com.rifqy.project.ecommerce.e_commerce.applicationuser.model.dto.RoleName;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder.Default;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String username;

    private String password;

    @Default
    private BigDecimal balance = BigDecimal.ZERO;

    private RoleName roleName;

    public RegisterationResponseDTO convertToResponse() {
        return RegisterationResponseDTO.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .username(this.username)
                .password(password)
                .balance(this.balance)
                .roleName(this.roleName)
                .build();
    }
}