package com.example.connect.connectionRequests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ConnectionRequestController {
    @Autowired
    private ConnectionRequestService connectionRequestService;
}
