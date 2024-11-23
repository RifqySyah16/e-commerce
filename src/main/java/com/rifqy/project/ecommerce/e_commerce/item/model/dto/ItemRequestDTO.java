package com.rifqy.project.ecommerce.e_commerce.item.model.dto;

import java.math.BigDecimal;

import com.rifqy.project.ecommerce.e_commerce.item.model.Item;
import com.rifqy.project.ecommerce.e_commerce.item.model.ItemCategory;

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

    @NotNull(message = "Category is required")
    private ItemCategory itemCategory;

    @NotNull(message = "Price is required")
    private BigDecimal price;

    @NotNull(message = "Stock is required")
    private Integer stock;

    @NotNull(message = "Weight is required")
    private Double weight;

    public Item convertToEntity() {
        return Item.builder()
                .id(this.id)
                .itemName(this.itemName)
                .itemCategory(this.itemCategory)
                .price(this.price)
                .stock(this.stock)
                .weight(this.weight)
                .build();
    }
}
