package com.example.pizzadatamanagementapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PizzaTypes")
public class PizzaType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "pizza_type_id")
    private String pizzaTypeId;
    @Column(name = "name")
    private String name;
    @Column(name = "category")
    private String category;
    @Column(name = "ingredients")
    private String ingredients;
    public PizzaType() {
    }
    public PizzaType(String pizzaTypeId, String name, String category, String ingredients) {
        this.pizzaTypeId = pizzaTypeId;
        this.name = name;
        this.category = category;
        this.ingredients = ingredients;
    }
}
