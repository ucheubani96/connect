package com.example.connect.connectionRequests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionRequestService {
    @Autowired
    private ConnectionRequestRepo connectionRequestRepo;
}
