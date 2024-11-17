package com.rifqy.project.ecommerce.e_commerce.item;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rifqy.project.ecommerce.e_commerce.item.model.Item;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Page<Item> getAll(Optional<Item> optionalItemName, Pageable pageable) {
        if (optionalItemName.isPresent()) {
            return this.itemRepository.findAllByItemNameContainsIgnoreCase(optionalItemName.get(), pageable);
        }

        return this.itemRepository.findAll(pageable);
    }

    public Item getOne(Long id) {
        return this.itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Item not found"));
    }

    public Item create(Item newItem) {
        Optional<Item> existingItem = this.itemRepository.findByItemName(newItem.getItemName());
        if (existingItem.isPresent()) {
            throw new ItemAlreadyExistException("Item already exist");
        }

        return this.itemRepository.save(newItem);
    }

    public Item update(Item updatedItem) {
        Item existingItem = this.getOne(updatedItem.getId());
        updatedItem.setId(existingItem.getId());

        return this.itemRepository.save(updatedItem);
    }

    public void delete(Long id) {
        Item existingItem = this.getOne(id);
        this.itemRepository.deleteById(existingItem.getId());
    }
}