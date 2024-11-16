package com.rifqy.project.ecommerce.e_commerce.item;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rifqy.project.ecommerce.e_commerce.authentication.model.UserPrincipal;
import com.rifqy.project.ecommerce.e_commerce.item.model.Item;
import com.rifqy.project.ecommerce.e_commerce.item.model.dto.ItemResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<Page<ItemResponseDTO>> getAll(
            Authentication authentication,
            @RequestParam(value = "Item_name", required = false) Optional<Item> optionalItemName,
            @RequestParam(value = "sort", defaultValue = "ASC") String sortString,
            @RequestParam(value = "order_by", defaultValue = "id") String orderBy,
            @RequestParam(value = "limit", defaultValue = "5") int limit,
            @RequestParam(value = "page", defaultValue = "1") int page) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long userId = userPrincipal.getId();

        Sort sort = Sort.by(Sort.Direction.valueOf(sortString), orderBy);
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Item> pageItem = this.itemService.getAll(userId, optionalItemName, pageable);
        Page<ItemResponseDTO> itemResponseDTOs = pageItem.map(Item::convertToResponse);

        return ResponseEntity.ok(itemResponseDTOs);
    }

}
