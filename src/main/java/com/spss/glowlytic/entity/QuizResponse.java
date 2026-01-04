package com.spss.glowlytic.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "quiz_response")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizResponse extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_question_id")
    private QuizQuestion quizQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_answer_id")
    private QuizAnswer quizAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_set_id")
    private QuizSet quizSet;
}
