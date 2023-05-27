package com.example.pizzadatamanagementapi.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Pizzas")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "pizza_id")
    private String pizzaId;
    @ManyToOne
    @JoinColumn(name = "pizza_type_id")
    private PizzaType type;
    @Enumerated(EnumType.STRING)
    @Column(name = "size")
    private PizzaSize size;
    @Column(name = "price")
    private BigDecimal price;

    public Pizza() {
    }
    public Pizza(String pizzaId, PizzaType type, PizzaSize size, BigDecimal price) {
        this.pizzaId = pizzaId;
        this.type = type;
        this.size = size;
        this.price = price;
    }
}
