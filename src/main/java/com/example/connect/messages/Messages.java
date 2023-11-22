package com.example.connect.messages;

import jakarta.persistence.*;

@Entity
@Table(name = "messages")
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "sender_id", nullable = false)
    private Long sender_id;

    @Column(name = "chatroom_id", nullable = false)
    private String chatroom_id;

    public String getChatroom_id() {
        return chatroom_id;
    }

    public void setChatroom_id(String chatroom_id) {
        this.chatroom_id = chatroom_id;
    }

    public Long getSender_id() {
        return sender_id;
    }

    public void setSender_id(Long sender_id) {
        this.sender_id = sender_id;
    }

    public Long getId() {
        return id;
    }
}
