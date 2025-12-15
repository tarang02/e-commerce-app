package com.korber.order.service;

import com.korber.order.dto.InventoryResponse;
import com.korber.order.dto.OrderRequest;
import com.korber.order.dto.OrderResponse;
import com.korber.order.entity.Order;
import com.korber.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final WebClient inventoryWebClient;

    @Transactional
    public OrderResponse placeOrder(OrderRequest request) {


        InventoryResponse inventory =
                inventoryWebClient.get()
                        .uri("/inventory/{productId}", request.getProductId())
                        .retrieve()
                        .bodyToMono(InventoryResponse.class)
                        .block();

        if (inventory == null || inventory.getProductName() == null) {
            throw new IllegalStateException("Product not found in inventory");
        }


        List<Long> batchIds =
                inventoryWebClient.post()
                        .uri("/inventory/update")
                        .bodyValue(request)
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<List<Long>>() {})
                        .block();


        Order order = new Order();
        order.setProductId(request.getProductId());
        order.setProductName(inventory.getProductName());
        order.setQuantity(request.getQuantity());
        order.setStatus("PLACED");
        order.setOrderDate(LocalDate.now());

        repository.save(order);


        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .productId(order.getProductId())
                .productName(order.getProductName())
                .quantity(order.getQuantity())
                .status(order.getStatus())
                .reservedFromBatchIds(batchIds)
                .message("Order placed. Inventory reserved.")
                .build();
    }
}
