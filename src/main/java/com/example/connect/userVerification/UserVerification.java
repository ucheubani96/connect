package com.example.connect.userVerification;

import com.example.connect.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "user_verification")
public class UserVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "token", nullable = false, length = 10)
    private String token;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long user_id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "local_user", unique = true)
    private User localUser;

    public User getLocalUser() {
        return localUser;
    }

    public void setLocalUser(User localUser) {
        this.localUser = localUser;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
