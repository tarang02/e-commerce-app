package com.korber.order.dto;

import lombok.Data;
import java.util.List;

@Data
public class InventoryResponse {

    private Long productId;
    private String productName;
    private List<BatchDto> batches;

    @Data
    public static class BatchDto {
        private Long batchId;
        private Integer quantity;
        private String expiryDate;
    }
}
