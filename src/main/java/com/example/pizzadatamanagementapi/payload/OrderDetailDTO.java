package com.example.pizzadatamanagementapi.payload;

import com.example.pizzadatamanagementapi.model.Order;
import com.example.pizzadatamanagementapi.model.OrderDetail;
import com.example.pizzadatamanagementapi.model.Pizza;

public class OrderDetailDTO {
    private Long id;
    private Order order;
    private PizzaDTO pizza;
    private Integer quantity;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(Long id, Order order, PizzaDTO pizza, Integer quantity) {
        this.id = id;
        this.order = order;
        this.pizza = pizza;
        this.quantity = quantity;
    }
    public static OrderDetailDTO convertToDTO(OrderDetail orderDetail) {
        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setId(orderDetail.getId());
        dto.setOrder(orderDetail.getOrder());
        if (orderDetail.getPizza() != null) {
            Pizza pizza = orderDetail.getPizza();
            PizzaDTO pizzaDTO = new PizzaDTO(
                    pizza.getPizzaId(),
                    PizzaTypeDTO.convertToDTO(pizza.getType()),
                    pizza.getSize() != null ? pizza.getSize().toString() : "",
                    pizza.getPrice()
            );
            dto.setPizza(pizzaDTO);
        }
        dto.setQuantity(orderDetail.getQuantity());
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PizzaDTO getPizza() {
        return pizza;
    }

    public void setPizza(PizzaDTO pizza) {
        this.pizza = pizza;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
