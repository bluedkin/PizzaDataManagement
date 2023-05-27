package com.example.pizzadatamanagementapi.controller;

import com.example.pizzadatamanagementapi.payload.PizzaTypeDTO;
import com.example.pizzadatamanagementapi.service.PizzaTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pizzaTypes")
public class PizzaTypeController {

    private final PizzaTypeService pizzaTypeService;

    public PizzaTypeController(PizzaTypeService pizzaTypeService) {
        this.pizzaTypeService = pizzaTypeService;
    }

    @GetMapping
    public ResponseEntity<List<PizzaTypeDTO>> getAllPizzaTypes(Pageable pageable) {
        Page<PizzaTypeDTO> pizzaTypeDTOs = pizzaTypeService.getAllPizzaTypes(pageable);
        return ResponseEntity.ok(pizzaTypeDTOs.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaTypeDTO> getPizzaTypeById(@PathVariable Long id) {
        Optional<PizzaTypeDTO> pizzaTypeDTOOptional = pizzaTypeService.getPizzaTypeById(id);
        return pizzaTypeDTOOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PizzaTypeDTO> createPizzaType(@RequestBody PizzaTypeDTO pizzaTypeDTO) {
        Optional<PizzaTypeDTO> createdPizzaTypeDTOOptional = pizzaTypeService.createPizzaType(pizzaTypeDTO);
        return createdPizzaTypeDTOOptional.map(createdPizzaTypeDTO -> ResponseEntity.status(HttpStatus.CREATED).body(createdPizzaTypeDTO)).orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PizzaTypeDTO> updatePizzaType(@PathVariable Long id, @RequestBody PizzaTypeDTO updatedPizzaTypeDTO) {
        Optional<PizzaTypeDTO> updatedPizzaTypeDTOOptional = pizzaTypeService.updatePizzaType(id, updatedPizzaTypeDTO);
        return updatedPizzaTypeDTOOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePizzaType(@PathVariable Long id) {
        boolean deletionSuccessful = pizzaTypeService.deletePizzaType(id);
        return deletionSuccessful ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
