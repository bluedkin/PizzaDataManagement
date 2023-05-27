package com.example.pizzadatamanagementapi.service;

import com.example.pizzadatamanagementapi.model.Order;
import com.example.pizzadatamanagementapi.payload.OrderDTO;
import com.example.pizzadatamanagementapi.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    public Page<OrderDTO> getAllOrders(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        return orders.map(this::convertToDTO);
    }

    public Optional<OrderDTO> getOrderById(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        return orderOptional.map(this::convertToDTO);
    }

    public Optional<OrderDTO> createOrder(OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        Order createdOrder = orderRepository.save(order);
        return Optional.ofNullable(convertToDTO(createdOrder));
    }

    public Optional<OrderDTO> updateOrder(Long orderId, OrderDTO orderDTO) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
             order.setDate(orderDTO.getDate());
             order.setTime(orderDTO.getTime());

            Order updatedOrder = orderRepository.save(order);
            return Optional.ofNullable(convertToDTO(updatedOrder));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteOrder(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            orderRepository.delete(optionalOrder.get());
            return true;
        } else {
            return false;
        }
    }

    private OrderDTO convertToDTO(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }

    private Order convertToEntity(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, Order.class);
    }
}
