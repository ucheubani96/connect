package com.example.connect.messages;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;

public interface MessagesRepo extends JpaRepository<Messages, Long> {
    @Query("select m from Messages m where m.chatroom_id = ?1")
    Page<Messages> findByChatroom_id(String chatroom_id, Pageable pageable);
}
