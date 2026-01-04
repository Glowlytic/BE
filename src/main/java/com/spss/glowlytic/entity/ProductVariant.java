package com.spss.glowlytic.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "variants")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ProductVariant extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(length = 50)
    private String capacity;

    private BigDecimal price;

    @Column(name = "price_sale")
    private BigDecimal priceSale;

    @Column(name = "percent_sales")
    private BigDecimal percentSales;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Column(name = "image_url", length = 500)
    private String imageUrl;
}