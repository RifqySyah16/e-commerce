package com.rifqy.project.ecommerce.e_commerce.item.model.dto;

import java.math.BigDecimal;

import com.rifqy.project.ecommerce.e_commerce.item.model.ItemCategory;

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
public class ItemResponseDTO {
    private Long id;
    private String itemName;
    private ItemCategory itemCategory;
    private BigDecimal price;
    private Integer stock;
    private Double weight;
}
