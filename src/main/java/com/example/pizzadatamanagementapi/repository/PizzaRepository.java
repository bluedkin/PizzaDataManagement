package com.example.pizzadatamanagementapi.repository;

import com.example.pizzadatamanagementapi.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    Optional<Pizza> findByPizzaId(String pizzaId);

}