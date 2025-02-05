//https://www.youtube.com/watch?v=lUVureR5GqI

package restaurante.saudealimentos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import restaurante.saudealimentos.domain.Food;
import restaurante.saudealimentos.repository.FoodRepository;
import restaurante.saudealimentos.domain.dto.FoodRequestDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("food")
@CrossOrigin(origins = "*", allowedHeaders = "*" )
public class FoodController {
    @Autowired
    private FoodRepository repository;

    @GetMapping()
    public List<Food> getAll() {
//        List<FoodResponseDTO> foodList = repository.findAll().stream().map(FoodResponseDTO::new).toList();
        List<Food> foodList = new ArrayList<>();
        Iterable<Food> findFood = repository.findAll();

        findFood.forEach(food -> {
            foodList.add(food);
        });

        return foodList;
    }

    @PostMapping
    public void saveFood(@RequestBody FoodRequestDTO data) {
        Food foodData = new Food(data);
        repository.save(foodData);
    }

    @DeleteMapping("/{id}")
    public void deleteFoodById(@PathVariable UUID id) {
        repository.deleteById(id);
    }

//    @DeleteMapping
//    public ResponseEntity<String> deleteFoods(@RequestBody List<UUID> ids) {
//        try {
//            repository.deleteAllById(ids);
//        } catch (HttpClientErrorException.BadRequest ex) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível deletar pratos: " + ex.getMessage());
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor.");
//        }
//
//        return null;
//    }

    @PutMapping("/{id}")
    public void updateFoodById(@PathVariable UUID id, @RequestBody FoodRequestDTO updatedFood) {
        Optional<Food> food = repository.findById(id);

        Food foodData = food.get();

        if(updatedFood.image() != null) {
            foodData.setImage(updatedFood.image());
        }

        if(updatedFood.title() != null) {
            foodData.setTitle(updatedFood.title());
        }

        if(updatedFood.price() != null) {
            foodData.setPrice(updatedFood.price());
        }

        repository.save(foodData);
    }
}
