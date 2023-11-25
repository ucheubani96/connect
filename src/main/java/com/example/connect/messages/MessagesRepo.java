package com.example.connect.messages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;

public interface MessagesRepo extends JpaRepository<Messages, Long> {
    List<Messages> findByChatroom_idIgnoreCase(String chatroom_id);

}
