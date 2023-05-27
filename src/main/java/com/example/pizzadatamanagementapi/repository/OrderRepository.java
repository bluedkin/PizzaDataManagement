package com.example.pizzadatamanagementapi.repository;

import com.example.pizzadatamanagementapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}