package restaurante.saudealimentos.domain;

import jakarta.persistence.*;
import lombok.*;
import restaurante.saudealimentos.domain.dto.FoodRequestDTO;

import java.util.UUID;

@Table(name = "food")
@Entity(name = "food")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String image;
    private Integer price;

    public Food(FoodRequestDTO data)  {
    this.image = data.image();
    this.price = data.price();
    this.title = data.title();
    }
}
