package com.korber.inventory.controller;

import com.korber.inventory.dto.InventoryResponse;
import com.korber.inventory.dto.InventoryUpdateRequest;
import com.korber.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService service;

    @GetMapping("/{productId}")
    public InventoryResponse getInventory(@PathVariable Long productId) {
        return service.getInventory(productId);
    }

    @PostMapping("/update")
    public List<Long> updateInventory(@RequestBody InventoryUpdateRequest request) {
        return service.updateInventory(
                request.getProductId(),
                request.getQuantity()
        );
    }
}
