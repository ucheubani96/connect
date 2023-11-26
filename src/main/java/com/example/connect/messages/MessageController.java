package com.example.connect.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;
}
