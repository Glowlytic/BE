package com.spss.glowlytic.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "quiz_sets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizSet extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    @Lob
    private String description;

    @Column(length = 50, nullable = true)
    private String code;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "quizSet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuizSession> quizSessions;

    @OneToMany(mappedBy = "quizSet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuizQuestion> quizQuestions;
}
