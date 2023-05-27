package com.example.pizzadatamanagementapi.payload;

import com.example.pizzadatamanagementapi.model.Order;

import java.time.LocalDate;
import java.time.LocalTime;

public class OrderDTO {
    private Long id;
    private LocalDate date;
    private LocalTime time;

    public OrderDTO() {
    }

    public OrderDTO(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public static OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setDate(order.getDate());
        dto.setTime(order.getTime());
        return dto;
    }

    public static Order convertToOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setDate(orderDTO.getDate());
        order.setTime(orderDTO.getTime());
        return order;
    }
}
