package com.example.pizzadatamanagementapi.repository;

import com.example.pizzadatamanagementapi.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetail, Long> {
}