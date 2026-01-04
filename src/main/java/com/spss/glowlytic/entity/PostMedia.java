package com.spss.glowlytic.entity;

import com.spss.glowlytic.enums.MediaType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post_media")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostMedia extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "media_url", length = 500)
    private String mediaUrl;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private MediaType type;

    @Column(length = 255)
    private String caption;

    @Column(nullable = true, name = "sort_order")
    private int sortOrder;

}
