package com.spss.glowlytic.entity;

import com.spss.glowlytic.enums.MediaType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comment_media")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentMedia extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Column(name = "media_url", length = 500)
    private String mediaUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "media_type")
    private MediaType mediaType;
}
