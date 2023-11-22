package com.example.connect.chatrooms;

import com.example.connect.chatroomUsers.ChatroomUsers;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chatrooms")
public class Chatrooms {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated
    @Column(name = "chatroom_types", nullable = false)
    private ChatroomTypes chatroomTypes;

    @ManyToMany(mappedBy = "chatrooms")
    private List<ChatroomUsers> chatroom_users = new ArrayList<>();

    public List<ChatroomUsers> getChatroom_users() {
        return chatroom_users;
    }

    public void setChatroom_users(List<ChatroomUsers> chatroom_users) {
        this.chatroom_users = chatroom_users;
    }

    public ChatroomTypes getChatroomTypes() {
        return chatroomTypes;
    }

    public void setChatroomTypes(ChatroomTypes chatroomTypes) {
        this.chatroomTypes = chatroomTypes;
    }

    public Long getId() {
        return id;
    }
}
