package com.example.pizzadatamanagementapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "PizzaTypes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPizzaTypeId() {
        return pizzaTypeId;
    }

    public void setPizzaTypeId(String pizzaTypeId) {
        this.pizzaTypeId = pizzaTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
