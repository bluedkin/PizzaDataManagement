package com.example.pizzadatamanagementapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "OrderDetails")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;
    @Column(name = "quantity")
    private Integer quantity;
    public OrderDetail() {
    }
    public OrderDetail(Long id,Order order, Pizza pizza, Integer quantity) {
        this.id = id;
        this.order = order;
        this.pizza = pizza;
        this.quantity = quantity;
    }

}
