package com.denlavor.cep.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Getter
@Setter
@Entity(name = "district")

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Distrito {
    @Id
    private UUID id;
    private String name;
//    @Column(columnDefinition = "jsonb")
//    private Object districts;
}
