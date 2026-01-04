package com.spss.glowlytic.entity;

import com.spss.glowlytic.enums.AttachmentType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "message_attachments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageAttachment extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    private Message message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product")
    private Product product;

    @Column(name = "attachment_type", length = 20)
    @Enumerated(EnumType.STRING)
    private AttachmentType attachmentType ;

    @Column(name = "media_url", length = 500)
    private String mediaUrl;

    @Column(name = "display_order", nullable = true)
    private int displayOrder;

}
