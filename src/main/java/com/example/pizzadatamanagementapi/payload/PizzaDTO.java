package com.example.pizzadatamanagementapi.payload;

import com.example.pizzadatamanagementapi.model.Pizza;
import com.example.pizzadatamanagementapi.model.PizzaSize;

import java.math.BigDecimal;

public class PizzaDTO {
    private Long id;
    private String pizzaId;
    private PizzaTypeDTO type;
    private String size;
    private BigDecimal price;

    public PizzaDTO() {
    }

    public PizzaDTO(String pizzaId, PizzaTypeDTO type, String size, BigDecimal price) {
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

    public PizzaTypeDTO getType() {
        return type;
    }

    public void setType(PizzaTypeDTO type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static PizzaSize getSizeEnum(String size) {
        switch (size) {
            case "S":
                return PizzaSize.S;
            case "M":
                return PizzaSize.M;
            case "L":
                return PizzaSize.L;
            case "XL":
                return PizzaSize.XL;
            case "XXL":
                return PizzaSize.XXL;
            default:
                throw new IllegalArgumentException("Invalid pizza size: " + size);
        }
    }

    public Pizza convertToPizza() {
        Pizza pizza = new Pizza();
        pizza.setPizzaId(this.getPizzaId());
        pizza.setType(this.getType().convertToPizzaType());
        pizza.setSize(getSizeEnum(this.getSize()));
        pizza.setPrice(this.getPrice());
        return pizza;
    }

    public static PizzaDTO convertToDTO(Pizza pizza) {
        PizzaDTO dto = new PizzaDTO();
        dto.setId(pizza.getId());
        dto.setPizzaId(pizza.getPizzaId());

        if (pizza.getType() != null) {
            dto.setType(PizzaTypeDTO.convertToDTO(pizza.getType()));
        }

        dto.setSize(pizza.getSize().toString());
        dto.setPrice(pizza.getPrice());
        return dto;
    }
}
