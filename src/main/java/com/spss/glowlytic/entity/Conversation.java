package com.spss.glowlytic.entity;

import com.spss.glowlytic.enums.CoversationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "conversations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conversation extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private CoversationType type;

    @Column()
    private String name;

    @Lob
    @Column(name = "last_message_content")
    private String lastMessageContent;

    @Column(name = "last_message_at")
    private LocalDateTime lastMessageAt;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> messages;


    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ConversationParticipant> conversationParticipants;





}
