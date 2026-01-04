package com.spss.glowlytic.entity;

import com.spss.glowlytic.enums.NotificationType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 300)
    private String title;

    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private NotificationType type;

    @Column(name = "deep_link_url", length = 500)
    private String deepLinkUrl;

    @Column(name = "is_read")
    private Boolean isRead;
}
