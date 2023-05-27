package com.example.pizzadatamanagementapi.service;

import com.example.pizzadatamanagementapi.model.Pizza;
import com.example.pizzadatamanagementapi.model.PizzaSize;
import com.example.pizzadatamanagementapi.model.PizzaType;
import com.example.pizzadatamanagementapi.payload.PizzaDTO;
import com.example.pizzadatamanagementapi.payload.PizzaTypeDTO;
import com.example.pizzadatamanagementapi.repository.PizzaRepository;
import com.example.pizzadatamanagementapi.repository.PizzaTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaTypeRepository pizzaTypeRepository;

    public PizzaService(PizzaRepository pizzaRepository, PizzaTypeRepository pizzaTypeRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaTypeRepository = pizzaTypeRepository;
    }

    public Page<PizzaDTO> getAllPizzas(Pageable pageable) {
        Page<Pizza> pizzas = pizzaRepository.findAll(pageable);
        return pizzas.map(PizzaDTO::convertToDTO);
    }

    public Optional<PizzaDTO> getPizzaById(Long pizzaId) {
        Optional<Pizza> optionalPizza = pizzaRepository.findById(pizzaId);
        return optionalPizza.map(PizzaDTO::convertToDTO);
    }

    public PizzaDTO createPizza(PizzaDTO pizzaDTO) {
        Pizza pizza = pizzaDTO.convertToPizza();

        Optional<PizzaType> optionalPizzaType = pizzaTypeRepository.findById(pizzaDTO.getType().getId());
        if (optionalPizzaType.isPresent()) {
            pizza.setType(optionalPizzaType.get());
            Pizza savedPizza = pizzaRepository.save(pizza);
            return PizzaDTO.convertToDTO(savedPizza);
        } else {
            throw new IllegalArgumentException("Invalid PizzaType ID: " + pizzaDTO.getType().getId());
        }
    }

    public Optional<PizzaDTO> updatePizza(Long pizzaId, PizzaDTO pizzaDTO) {
        Optional<Pizza> optionalPizza = pizzaRepository.findById(pizzaId);
        if (optionalPizza.isPresent()) {
            Pizza pizza = optionalPizza.get();
            pizza.setPizzaId(pizzaDTO.getPizzaId());
            pizza.setPrice(pizzaDTO.getPrice());

            Optional<PizzaType> optionalPizzaType = pizzaTypeRepository.findByPizzaTypeId(pizzaDTO.getType().getPizzaTypeId());
            optionalPizzaType.ifPresent(pizza::setType);

            // Update the size if it has changed
            PizzaSize newSize = PizzaDTO.getSizeEnum(pizzaDTO.getSize());
            if (newSize != pizza.getSize()) {
                pizza.setSize(newSize);
            }

            Pizza updatedPizza = pizzaRepository.save(pizza);
            return Optional.of(PizzaDTO.convertToDTO(updatedPizza));
        } else {
            return Optional.empty();
        }
    }


    public boolean deletePizza(Long pizzaId) {
        Optional<Pizza> optionalPizza = pizzaRepository.findById(pizzaId);
        if (optionalPizza.isPresent()) {
            Pizza pizza = optionalPizza.get();
            pizzaRepository.delete(pizza);
            return true;
        } else {
            return false;
        }
    }
}
