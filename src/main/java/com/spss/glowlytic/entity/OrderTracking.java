package com.spss.glowlytic.entity;

import com.spss.glowlytic.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_trackings")
@Getter @Setter
public class OrderTracking extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(length = 500)
    private String note;
}
