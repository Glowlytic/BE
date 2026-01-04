package com.spss.glowlytic.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "quiz_sessions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizSession extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_set_id")
    private QuizSet quizSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "quizSession", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuizResponse> quizResponses;

    @OneToMany(mappedBy = "quizSession", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SkinType> skinTypes;

    @Column(name = "total_score")
    private Integer totalScore;

    @Lob
    @Column(name = "ai_analysis_summary")
    private String aiAnalysisSummary;


}
