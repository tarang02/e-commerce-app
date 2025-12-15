package com.korber.inventory.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "inventory_batch")
@Data
public class InventoryBatch {

    @Id
    @Column(name = "batch_id")
    private Long batchId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    private Integer quantity;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;
}
