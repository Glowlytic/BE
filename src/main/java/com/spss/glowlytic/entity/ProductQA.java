package com.spss.glowlytic.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_qa")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductQA extends BaseEntity {

    @Column(length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ProductQA parent;


}