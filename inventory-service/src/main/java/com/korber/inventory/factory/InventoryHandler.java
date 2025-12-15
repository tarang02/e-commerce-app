package com.korber.inventory.factory;

import com.korber.inventory.entity.InventoryBatch;

import java.util.List;

public interface InventoryHandler {

    List<InventoryBatch> getBatches(Long productId);

    List<Long> reserveStock(Long productId, int quantity);
}
