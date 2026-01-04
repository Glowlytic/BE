package com.spss.glowlytic.entity;

import com.spss.glowlytic.enums.PostStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 255)
    private String title;

    @Column(length = 255)
    private String slug;

    @Lob
    private String content;

    @Column(name = "thumbnail_url", length = 500)
    private String thumbnailUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "message_type", length = 20)
    private PostStatus status;
}
