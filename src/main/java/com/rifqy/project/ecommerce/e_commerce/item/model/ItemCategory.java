package com.rifqy.project.ecommerce.e_commerce.item.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.rifqy.project.ecommerce.e_commerce.item.InvalidCategoryException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ItemCategory {
    SALARY("Salary"),
    EXTRA_INCOME("Extra Income"),
    INVESTMENT("Investment");

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ItemCategory fromValue(String value) {
        for (ItemCategory category : values()) {
            if (category.getValue().equalsIgnoreCase(value)) {
                return category;
            }
        }
        throw new InvalidCategoryException("Invalid Income Category: " + value);
    }
}
