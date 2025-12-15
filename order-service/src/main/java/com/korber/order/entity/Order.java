package com.korber.order.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    private Integer quantity;

    private String status;

    @Column(name = "order_date")
    private LocalDate orderDate;
}
