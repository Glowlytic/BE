package com.spss.glowlytic.entity;

import com.spss.glowlytic.enums.MediaType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "news_media")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsMedia extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id")
    private News news;

    @Column(name = "media_url", length = 500)
    private String mediaUrl;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, name = "media_type")
    private MediaType mediaType;

    @Column(length = 500)
    private String caption;

    @Column(nullable = true, name = "sort_order")
    private int sortOrder;

}
