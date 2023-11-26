package com.example.connect.chatrooms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatroomService {
    @Autowired
    private ChatRoomsRepo chatRoomsRepo;
}
