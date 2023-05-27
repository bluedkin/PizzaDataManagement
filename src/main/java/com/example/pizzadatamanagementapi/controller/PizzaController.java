package com.example.pizzadatamanagementapi.controller;

import com.example.pizzadatamanagementapi.model.Pizza;
import com.example.pizzadatamanagementapi.payload.PizzaDTO;
import com.example.pizzadatamanagementapi.service.PizzaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<PizzaDTO>> getAllPizzas(Pageable pageable) {
        Page<PizzaDTO> pizzas = pizzaService.getAllPizzas(pageable);
        return ResponseEntity.ok(pizzas.getContent());
    }

    @GetMapping("/{pizzaId}")
    public ResponseEntity<PizzaDTO> getPizzaById(@PathVariable Long pizzaId) {
        Optional<PizzaDTO> pizzaDTOOptional = pizzaService.getPizzaById(pizzaId);
        return pizzaDTOOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PizzaDTO> createPizza(@RequestBody PizzaDTO pizzaDTO) {
        PizzaDTO createdPizza = pizzaService.createPizza(pizzaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPizza);
    }

    @PutMapping("/{pizzaId}")
    public ResponseEntity<PizzaDTO> updatePizza(@PathVariable Long pizzaId, @RequestBody PizzaDTO pizzaDTO) {
        Optional<PizzaDTO> updatedPizza = pizzaService.updatePizza(pizzaId, pizzaDTO);
        return updatedPizza.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{pizzaId}")
    public ResponseEntity<Void> deletePizza(@PathVariable Long pizzaId) {
        boolean deleted = pizzaService.deletePizza(pizzaId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
