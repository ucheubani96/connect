package com.example.connect.chatroomUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatroomUserService {
    @Autowired
    private ChatroomUserRepo chatroomUserRepo;
}
