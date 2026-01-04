package com.spss.glowlytic.entity;

import com.spss.glowlytic.enums.QuestionType;
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
public class QuizQuestion extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_set_id")
    private QuizSet quizSet;

    private String content;

    @Column(name = "sort_order")
    private int sortOrder;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_type", length = 20)
    private QuestionType questionType;

    @OneToMany(mappedBy = "quizQuestion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuizAnswer> quizAnswers;

    @OneToMany(mappedBy = "quizQuestion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuizResponse> responses;

}
