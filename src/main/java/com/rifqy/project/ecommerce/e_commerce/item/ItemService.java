package com.rifqy.project.ecommerce.e_commerce.item;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rifqy.project.ecommerce.e_commerce.item.model.Item;

public class ItemService {

    public Page<Item> getAll(Long userId, Optional<Item> optionalItemName, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

}
