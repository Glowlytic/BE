package com.spss.glowlytic.entity;
import com.spss.glowlytic.enums.TimeOfDay;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "routine_steps")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoutineStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id")
    private Routine routine;

    @Enumerated(EnumType.STRING)
    @Column(name = "time_of_day")
    private TimeOfDay timeOfDay;

    @Column(name = "step_order")
    private Integer stepOrder;

    @Column(name = "step_name")
    private String stepName;

    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "instruction_detail")
    private String instructionDetail;

    @Column(name = "wait_time_minute")
    private Integer waitTimeMinute;

    @Column(name = "frequency_text")
    private String frequencyText;

    @Lob
    @Column(name = "warning_note")
    private String warningNote;

    @Lob
    @Column(name = "reason_for_choosing")
    private String reasonForChoosing;

    @Column(name = "is_must_have")
    private Boolean isMustHave;
}