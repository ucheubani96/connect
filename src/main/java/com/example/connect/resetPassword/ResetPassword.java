package com.example.connect.resetPassword;

import com.example.connect.shared.models.Auditable;
import com.example.connect.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "password_reset_token")
@Setter
@Getter
public class ResetPassword extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "token", nullable = false, unique = true)
    private Integer token;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
