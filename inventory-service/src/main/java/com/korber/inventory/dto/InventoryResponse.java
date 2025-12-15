package com.korber.inventory.dto;

import com.korber.inventory.entity.InventoryBatch;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class InventoryResponse {

    private Long productId;
    private String productName;
    private List<BatchDto> batches;

    @Data
    @Builder
    public static class BatchDto {
        private Long batchId;
        private Integer quantity;
        private String expiryDate;
    }

    public static InventoryResponse from(List<InventoryBatch> batches) {

        if (batches.isEmpty()) {
            return InventoryResponse.builder().build();
        }

        return InventoryResponse.builder()
                .productId(batches.get(0).getProductId())
                .productName(batches.get(0).getProductName())
                .batches(
                        batches.stream()
                                .map(b -> BatchDto.builder()
                                        .batchId(b.getBatchId())
                                        .quantity(b.getQuantity())
                                        .expiryDate(b.getExpiryDate().toString())
                                        .build())
                                .toList()
                )
                .build();
    }
}
