package com.spss.glowlytic.entity;

import com.spss.glowlytic.enums.MessageType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Lob
    private String context;

    @Enumerated(EnumType.STRING)
    @Column(name = "message_type", length = 20)
    private MessageType messageType;

    @Column(name = "is_read")
    private Boolean IsRead;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MessageAttachment> messageAttachments;




}
