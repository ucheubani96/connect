package com.example.connect.connectionRequests;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "connection_requests")
public class ConnectionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "sender_id", nullable = false)
    private Long sender_id;

    @Column(name = "receiver_id", nullable = false)
    private Long receiver_id;

    public void setReceiver_id(Long receiver_id) {
        this.receiver_id = receiver_id;
    }

    public void setSender_id(Long sender_id) {
        this.sender_id = sender_id;
    }

}
