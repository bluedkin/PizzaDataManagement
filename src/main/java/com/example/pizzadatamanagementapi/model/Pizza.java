package com.example.pizzadatamanagementapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Pizzas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(String pizzaId) {
        this.pizzaId = pizzaId;
    }

    public PizzaType getType() {
        return type;
    }

    public void setType(PizzaType type) {
        this.type = type;
    }

    public PizzaSize getSize() {
        return size;
    }

    public void setSize(PizzaSize size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
