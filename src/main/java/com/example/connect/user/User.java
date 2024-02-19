package com.example.connect.user;

import com.example.connect.shared.models.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "local_users")
@Setter
@Getter
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 100)
    private String username;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 1000)
    @JsonIgnore
    private String password;

    @Column(name = "dp")
    private String dp;

    @Column(name = "is_verified", nullable = false, columnDefinition = "boolean default false")
    private boolean isVerified = false;

    public boolean comparePasswords (String oldPasswordHash, String newPasswordHash) {
        return (oldPasswordHash.equals(newPasswordHash));
    }
}
