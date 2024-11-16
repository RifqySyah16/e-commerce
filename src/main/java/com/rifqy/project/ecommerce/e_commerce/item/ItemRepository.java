package com.rifqy.project.ecommerce.e_commerce.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rifqy.project.ecommerce.e_commerce.item.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
