package com.spss.glowlytic.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Integer rating;

    @Lob
    private String comment;

    @Lob
    @Column(name = "media_url")
    private String mediaUrl;

    @Column(name = "is_purchased")
    private Boolean isPurchased;

}
