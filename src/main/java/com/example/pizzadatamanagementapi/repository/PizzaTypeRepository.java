package com.example.pizzadatamanagementapi.repository;

import com.example.pizzadatamanagementapi.model.PizzaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PizzaTypeRepository extends JpaRepository<PizzaType, Long> {
    Optional<PizzaType> findByPizzaTypeId(String pizzaTypeId);
    Optional<PizzaType> findById(Long id);
}