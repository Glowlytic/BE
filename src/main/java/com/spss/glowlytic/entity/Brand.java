package com.spss.glowlytic.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "brands")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Brand extends BaseEntity {

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "logo_url", length = 500)
    private String logoUrl;

    @Lob
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;
}
