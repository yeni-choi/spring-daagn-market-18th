package com.ceos18.springboot.chatting.entity;

import com.ceos18.springboot.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User sender;  //보낸 이

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MessageType type;

    @Column(length = 1000)
    private String message; //메시지 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private ChatRoom chatRoom;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime sendDate;

    @Builder
    public ChatMessage(User sender, ChatRoom chatRoom, String message, String messageType) {
        this.sender = sender;
        this.chatRoom = chatRoom;
        this.message = message;
        this.type = MessageType.valueOf(messageType);
    }

    public enum MessageType {
        ENTER, EXIT, TALK
    }
}
