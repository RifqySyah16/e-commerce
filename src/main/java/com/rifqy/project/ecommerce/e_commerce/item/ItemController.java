package com.rifqy.project.ecommerce.e_commerce.item;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rifqy.project.ecommerce.e_commerce.item.model.Item;
import com.rifqy.project.ecommerce.e_commerce.item.model.dto.ItemRequestDTO;
import com.rifqy.project.ecommerce.e_commerce.item.model.dto.ItemResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<Page<ItemResponseDTO>> getAll(
            @RequestParam(value = "Item_name", required = false) Optional<Item> optionalItemName,
            @RequestParam(value = "sort", defaultValue = "ASC") String sortString,
            @RequestParam(value = "order_by", defaultValue = "id") String orderBy,
            @RequestParam(value = "limit", defaultValue = "5") int limit,
            @RequestParam(value = "page", defaultValue = "1") int page) {

        Sort sort = Sort.by(Sort.Direction.valueOf(sortString), orderBy);
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Item> pageItem = this.itemService.getAll(optionalItemName, pageable);
        Page<ItemResponseDTO> itemResponseDTOs = pageItem.map(Item::convertToResponse);

        return ResponseEntity.ok(itemResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> getOne(@PathVariable("id") Long id) {

        Item existingItem = this.itemService.getOne(id);
        ItemResponseDTO itemResponseDTO = existingItem.convertToResponse();

        return ResponseEntity.ok(itemResponseDTO);
    }

    @PostMapping
    public ResponseEntity<ItemResponseDTO> create(@RequestBody ItemRequestDTO itemRequestDTO) {

        Item newItem = itemRequestDTO.convertToEntity();

        Item savedItem = this.itemService.create(newItem);
        ItemResponseDTO itemResponseDTO = savedItem.convertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(itemResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> update(@PathVariable("id") Long id,
            @RequestBody ItemRequestDTO itemRequestDTO) {

        Item updatedItem = itemRequestDTO.convertToEntity();
        updatedItem.setId(id);

        Item savedItem = this.itemService.update(updatedItem);
        ItemResponseDTO itemResponseDTO = savedItem.convertToResponse();

        return ResponseEntity.ok(itemResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

        this.itemService.delete(id);

        return ResponseEntity.ok().build();
    }
}