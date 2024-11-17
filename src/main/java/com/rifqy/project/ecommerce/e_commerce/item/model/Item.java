package com.rifqy.project.ecommerce.e_commerce.item.model;

import java.math.BigDecimal;

import com.rifqy.project.ecommerce.e_commerce.item.model.dto.ItemResponseDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    private BigDecimal price;

    private Integer stock;

    private Double weight;

    public ItemResponseDTO convertToResponse() {
        return ItemResponseDTO.builder()
                .id(this.id)
                .itemName(this.itemName)
                .price(this.price)
                .stock(this.stock)
                .weight(this.weight)
                .build();
    }
}
