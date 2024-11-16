package com.rifqy.project.ecommerce.e_commerce.item.model.dto;

import java.math.BigDecimal;

import com.rifqy.project.ecommerce.e_commerce.item.model.Item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestDTO {
    private Long id;

    @NotBlank(message = "Item name is required")
    private String itemName;

    @NotNull(message = "Price is required")
    private BigDecimal price;

    @NotNull(message = "Weight is required")
    private Double weight;

    public Item convertToEntity() {
        return Item.builder()
                .id(this.id)
                .itemName(this.itemName)
                .price(this.price)
                .weight(this.weight)
                .build();
    }
}
