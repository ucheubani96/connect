package com.example.connect.chatroomUsers;

import com.example.connect.chatrooms.Chatrooms;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chatroom_users")
public class ChatroomUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "chatroom_id", nullable = false)
    private Long chatroom_id;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @ManyToMany
    @JoinTable(name = "chatroom_users_relation",
            joinColumns = @JoinColumn(name = "chatroom_users_id"),
            inverseJoinColumns = @JoinColumn(name = "chatrooms_id"))
    private List<Chatrooms> chatrooms = new ArrayList<>();

    public List<Chatrooms> getChatrooms() {
        return chatrooms;
    }

    public void setChatrooms(List<Chatrooms> chatrooms) {
        this.chatrooms = chatrooms;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getChatroom_id() {
        return chatroom_id;
    }

    public void setChatroom_id(Long chatroom_id) {
        this.chatroom_id = chatroom_id;
    }

    public Long getId() {
        return id;
    }
}
