package com.spss.glowlytic.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "quiz_answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizAnswer extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_question_id")
    private QuizQuestion quizQuestion;

    @Lob
    private String content;

    @Column(name = "score_value")
    private Integer scoreValue;

    @Column(name = "ai_tag", length = 100)
    private String aiTag;

    @OneToMany(mappedBy = "quizAnswer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuizAnswer> quizAnswers;
}
