package com.korber.inventory.repository;

import com.korber.inventory.entity.InventoryBatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryBatchRepository
        extends JpaRepository<InventoryBatch, Long> {

    List<InventoryBatch> findByProductIdOrderByExpiryDateAsc(Long productId);
}
