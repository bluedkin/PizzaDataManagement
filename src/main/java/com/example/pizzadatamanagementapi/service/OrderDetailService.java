package com.example.pizzadatamanagementapi.service;

import com.example.pizzadatamanagementapi.payload.OrderDetailDTO;
import com.example.pizzadatamanagementapi.model.OrderDetail;
import com.example.pizzadatamanagementapi.repository.OrderDetailsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderDetailService {
    private final OrderDetailsRepository orderDetailsRepository;

    public OrderDetailService(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public Page<OrderDetailDTO> getAllOrderDetails(Pageable pageable) {
        Page<OrderDetail> orderDetails = orderDetailsRepository.findAll(pageable);
        return orderDetails.map(OrderDetailDTO::convertToDTO);
    }
    public Optional<OrderDetail> getOrderDetailById(Long orderDetailId) {
        return orderDetailsRepository.findById(orderDetailId);
    }
    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
        return orderDetailsRepository.save(orderDetail);
    }
    public OrderDetail updateOrderDetail(Long orderDetailId, OrderDetail updatedOrderDetail) {
        Optional<OrderDetail> existingOrderDetail = orderDetailsRepository.findById(orderDetailId);
        if (existingOrderDetail.isPresent()) {
            OrderDetail orderDetail = existingOrderDetail.get();
            orderDetail.setOrder(updatedOrderDetail.getOrder());
            orderDetail.setPizza(updatedOrderDetail.getPizza());
            orderDetail.setQuantity(updatedOrderDetail.getQuantity());
            return orderDetailsRepository.save(orderDetail);
        } else {
            throw new NoSuchElementException("OrderDetail not found with ID: " + orderDetailId);
        }
    }
    public void delete(OrderDetail orderDetail) {
        orderDetailsRepository.delete(orderDetail);
    }
}
