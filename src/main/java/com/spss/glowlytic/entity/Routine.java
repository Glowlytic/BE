package com.spss.glowlytic.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "routines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Routine extends BaseEntity {

    private String title;

    @Column(columnDefinition = "TEXT", name = "ai_summary")
    private String aiSummary;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoutineStep> steps;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL)
    private List<ProductSuggestion> productSuggestions;
}