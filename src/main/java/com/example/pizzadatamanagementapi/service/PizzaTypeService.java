package com.example.pizzadatamanagementapi.service;

import com.example.pizzadatamanagementapi.model.PizzaType;
import com.example.pizzadatamanagementapi.payload.PizzaTypeDTO;
import com.example.pizzadatamanagementapi.repository.PizzaTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PizzaTypeService {

    private final PizzaTypeRepository pizzaTypeRepository;

    public PizzaTypeService(PizzaTypeRepository pizzaTypeRepository) {
        this.pizzaTypeRepository = pizzaTypeRepository;
    }

    public Page<PizzaTypeDTO> getAllPizzaTypes(Pageable pageable) {
        Page<PizzaType> pizzaTypes = pizzaTypeRepository.findAll(pageable);
        return pizzaTypes.map(PizzaTypeDTO::convertToDTO);
    }

    public Optional<PizzaTypeDTO> getPizzaTypeById(Long id) {
        return pizzaTypeRepository.findById(id).map(PizzaTypeDTO::convertToDTO);
    }

    public Optional<PizzaTypeDTO> createPizzaType(PizzaTypeDTO pizzaTypeDTO) {
        PizzaType pizzaType = new PizzaType();
        pizzaType.setPizzaTypeId(pizzaTypeDTO.getPizzaTypeId());
        pizzaType.setName(pizzaTypeDTO.getName());
        pizzaType.setCategory(pizzaTypeDTO.getCategory());
        pizzaType.setIngredients(pizzaTypeDTO.getIngredients());

        PizzaType createdPizzaType = pizzaTypeRepository.save(pizzaType);
        return Optional.ofNullable(PizzaTypeDTO.convertToDTO(createdPizzaType));
    }

    public Optional<PizzaTypeDTO> updatePizzaType(Long id, PizzaTypeDTO updatedPizzaTypeDTO) {
        Optional<PizzaType> existingPizzaTypeOptional = pizzaTypeRepository.findById(id);
        if (existingPizzaTypeOptional.isPresent()) {
            PizzaType existingPizzaType = existingPizzaTypeOptional.get();
            existingPizzaType.setPizzaTypeId(updatedPizzaTypeDTO.getPizzaTypeId());
            existingPizzaType.setName(updatedPizzaTypeDTO.getName());
            existingPizzaType.setCategory(updatedPizzaTypeDTO.getCategory());
            existingPizzaType.setIngredients(updatedPizzaTypeDTO.getIngredients());

            PizzaType updatedPizzaType = pizzaTypeRepository.save(existingPizzaType);
            return Optional.ofNullable(PizzaTypeDTO.convertToDTO(updatedPizzaType));
        } else {
            return Optional.empty();
        }
    }

    public boolean deletePizzaType(Long id) {
        Optional<PizzaType> pizzaTypeOptional = pizzaTypeRepository.findById(id);
        if (pizzaTypeOptional.isPresent()) {
            pizzaTypeRepository.delete(pizzaTypeOptional.get());
            return true;
        } else {
            return false;
        }
    }
}
