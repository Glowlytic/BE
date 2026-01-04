package com.spss.glowlytic.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cancel_reasons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CancelReason extends BaseEntity {

    private String content;

    @Column(name = "is_other")
    private Boolean isOther;

    @OneToMany(mappedBy = "cancelReason", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;

}