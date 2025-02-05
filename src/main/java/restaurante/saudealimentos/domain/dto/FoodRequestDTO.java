package restaurante.saudealimentos.domain.dto;

import restaurante.saudealimentos.domain.Food;

import java.util.UUID;

public record FoodRequestDTO(UUID id, String title, String image, Integer price) {
    public FoodRequestDTO(Food food) {
        this(food.getId(), food.getTitle(), food.getImage(), food.getPrice());
    }
}
