package com.spss.glowlytic.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "coach_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coach extends BaseEntity {
    @Lob
    private String specialization;

    @Column(name = "year_of_experience")
    private int yearOfExperience;

    @Lob
    private String bio;

    @Lob
    private String certificate;

    @Column(name = "average_rating")
    private Double averageRating;

    @Column(name = "booking_count")
    private int bookingCount;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
