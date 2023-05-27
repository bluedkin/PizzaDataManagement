package com.example.pizzadatamanagementapi.payload;

import com.example.pizzadatamanagementapi.model.PizzaType;

public class PizzaTypeDTO {
    private Long id;
    private String pizzaTypeId;
    private String name;
    private String category;
    private String ingredients;

    public PizzaTypeDTO() {
    }

    public PizzaTypeDTO(Long id, String pizzaTypeId, String name, String category, String ingredients) {
        this.id = id;
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

    public PizzaType convertToPizzaType() {
        PizzaType pizzaType = new PizzaType();
        pizzaType.setPizzaTypeId(this.getPizzaTypeId());
        pizzaType.setName(this.getName());
        pizzaType.setCategory(this.getCategory());
        pizzaType.setIngredients(this.getIngredients());
        return pizzaType;
    }

    public static PizzaTypeDTO convertToDTO(PizzaType pizzaType) {
        PizzaTypeDTO dto = new PizzaTypeDTO();
        dto.setId(pizzaType.getId());
        dto.setPizzaTypeId(pizzaType.getPizzaTypeId());
        dto.setName(pizzaType.getName());
        dto.setCategory(pizzaType.getCategory());
        dto.setIngredients(pizzaType.getIngredients());
        return dto;
    }
}
