package com.rifqy.project.ecommerce.e_commerce.item;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rifqy.project.ecommerce.e_commerce.item.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findAllByItemNameContainsIgnoreCase(Item item, Pageable pageable);

    Optional<Item> findByItemName(String itemName);

}
