package com.korber.inventory.service;

import com.korber.inventory.dto.InventoryResponse;
import com.korber.inventory.factory.InventoryHandlerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryHandlerFactory factory;

    public InventoryResponse getInventory(Long productId) {
        return InventoryResponse.from(
                factory.getHandler("DEFAULT").getBatches(productId)
        );
    }

    public List<Long> updateInventory(Long productId, int quantity) {
        return factory.getHandler("DEFAULT")
                .reserveStock(productId, quantity);
    }
}
