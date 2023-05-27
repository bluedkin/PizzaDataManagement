package com.example.pizzadatamanagementapi.controller;

import com.example.pizzadatamanagementapi.payload.OrderDetailDTO;
import com.example.pizzadatamanagementapi.model.OrderDetail;
import com.example.pizzadatamanagementapi.service.OrderDetailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orderDetails")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDetailDTO>> getAllOrderDetails(Pageable pageable) {
        Page<OrderDetailDTO> orderDetails = orderDetailService.getAllOrderDetails(pageable);
        return ResponseEntity.ok(orderDetails.getContent());
    }

    @GetMapping("/{orderDetailId}")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable Long orderDetailId) {
        Optional<OrderDetail> orderDetail = orderDetailService.getOrderDetailById(orderDetailId);
        return orderDetail.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public OrderDetail createOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderDetailService.saveOrderDetail(orderDetail);
    }

    @PutMapping("/{orderDetailId}")
    public ResponseEntity<OrderDetailDTO> updateOrderDetailById(@PathVariable Long orderDetailId, @RequestBody OrderDetail updatedOrderDetailDTO) {
        OrderDetail updatedOrderDetail = orderDetailService.updateOrderDetail(orderDetailId, updatedOrderDetailDTO);
        return ResponseEntity.ok(OrderDetailDTO.convertToDTO(updatedOrderDetail));
    }

    @DeleteMapping("/{orderDetailId}")
    public ResponseEntity<Object> deleteOrderDetail(@PathVariable Long orderDetailId) {
        Optional<OrderDetail> orderDetail = orderDetailService.getOrderDetailById(orderDetailId);
        orderDetail.ifPresent(orderDetailService::delete);
        return orderDetail.map(o -> ResponseEntity.noContent().build()).orElse(ResponseEntity.notFound().build());
    }
}