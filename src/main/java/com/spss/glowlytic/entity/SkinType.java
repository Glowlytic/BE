package com.spss.glowlytic.entity;

import com.spss.glowlytic.enums.SkinTypeCode;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "skin_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkinType extends BaseEntity {

    @Column(name = "name", length = 255)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "code", length = 20)
    private SkinTypeCode code;

    @Lob
    private String description;

    @OneToMany(mappedBy = "skinType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Customer> customers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skin_tye_id")
    private QuizSession quizSession;


}
