package restaurante.saudealimentos.repository;

//import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restaurante.saudealimentos.domain.Food;

import java.util.UUID;

@Repository
public interface FoodRepository extends CrudRepository<Food, UUID> {
}
