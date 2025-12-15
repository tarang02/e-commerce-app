package com.korber.inventory.factory;

import com.korber.inventory.entity.InventoryBatch;
import com.korber.inventory.repository.InventoryBatchRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("DEFAULT")
@RequiredArgsConstructor
public class DefaultInventoryHandler implements InventoryHandler {

    private final InventoryBatchRepository repository;

    @Override
    public List<InventoryBatch> getBatches(Long productId) {
        return repository.findByProductIdOrderByExpiryDateAsc(productId);
    }

    @Override
    @Transactional
    public List<Long> reserveStock(Long productId, int quantity) {

        List<InventoryBatch> batches =
                repository.findByProductIdOrderByExpiryDateAsc(productId);

        int remaining = quantity;
        List<Long> usedBatchIds = new ArrayList<>();

        for (InventoryBatch batch : batches) {
            if (remaining <= 0) break;

            int used = Math.min(batch.getQuantity(), remaining);
            batch.setQuantity(batch.getQuantity() - used);
            remaining -= used;

            usedBatchIds.add(batch.getBatchId());
        }

        if (remaining > 0) {
            throw new IllegalStateException("Insufficient inventory");
        }

        return usedBatchIds;
    }
}
