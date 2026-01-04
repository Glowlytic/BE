package com.spss.glowlytic.entity;

import com.spss.glowlytic.enums.NewsStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "news")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class News extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column()
    private String title;

    @Column()
    private String slug;

    @Lob
    private String content;

    @Column(name = "thumbnail_url", length = 500)
    private String thumbnailUrl;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private NewsStatus status;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<NewsMedia> newsMedias;

}
