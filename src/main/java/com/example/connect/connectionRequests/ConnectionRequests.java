package com.example.connect.connectionRequests;

import jakarta.persistence.*;

@Entity
@Table(name = "connection_requests")
public class ConnectionRequests {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "sender_id", nullable = false)
    private Long sender_id;

    @Column(name = "receiver_id", nullable = false)
    private Long receiver_id;

    public Long getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(Long receiver_id) {
        this.receiver_id = receiver_id;
    }

    public Long getSender_id() {
        return sender_id;
    }

    public void setSender_id(Long sender_id) {
        this.sender_id = sender_id;
    }

    public Long getId() {
        return id;
    }
}
