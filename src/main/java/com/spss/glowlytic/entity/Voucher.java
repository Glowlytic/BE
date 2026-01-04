package com.spss.glowlytic.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "vouchers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Voucher extends BaseEntity {
    @Column(length = 50)
    private String code;

    @Lob
    private String description;

    @Column(name = "discount_amount")
    private BigDecimal discountAmount;

    @Column(name = "discount_percent")
    private Double discountPercent;

    @Column(name = "max_discount_amount")
    private BigDecimal maxDiscountAmount;

    @Column(name = "min_order_value")
    private BigDecimal minOrderValue;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime expirationDate;

    private Integer quantity;

    @Column(name = "exchange_point")
    private Integer exchangePoint;

    @OneToMany(mappedBy = "voucher", cascade = CascadeType.ALL)
    private List<CustomerVoucher> customerVouchers;
}
