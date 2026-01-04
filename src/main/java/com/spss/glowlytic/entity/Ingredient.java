package com.spss.glowlytic.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "key_ingredient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingredient extends BaseEntity {
    private String name;
    @Column(length = 500)
    private String description;
    @Column(length = 20)
    private String slug;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductKeyIngredient> productKeyIngredients;
}
