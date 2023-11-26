package com.example.connect.chatrooms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ChatroomController {
    @Autowired
    private ChatroomService chatroomService;
}
