package com.spss.glowlytic.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Product extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    private String name;

    private String slug;

    @Lob
    private String description;

    @Lob
    @Column(name = "full_ingredients")
    private String fullIngredients;

    @Lob
    @Column(name = "usage_instructions")
    private String usageInstructions;

    @Column(name = "manufacturing_place", length = 100)
    private String manufacturingPlace;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "sold_count")
    private Integer soldCount = 0;

    @Column(name = "average_rating")
    private Double averageRating = 0.0;

    @Column(name = "review_count")
    private Integer reviewCount = 0;

    private BigDecimal price;

    @Column(name = "discounted_price")
    private BigDecimal discountedPrice;

    @Column(name = "percent_sale")
    private BigDecimal percentSale;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductVariant> variants;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductQA> productQas;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductImage> images;
}