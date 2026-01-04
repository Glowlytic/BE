package com.spss.glowlytic.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skin_types_id", nullable = false)
    private SkinType skinType;

    @Column(name = "skin_types_id")
    private Long skinTypesId;

    @Column(name = "loyalty_points")
    private Integer loyaltyPoints;

    @Column(name = "membership_level")
    private String membershipLevel;

    @Column(name = "shipping_address", columnDefinition = "TEXT")
    private String shippingAddress;
}