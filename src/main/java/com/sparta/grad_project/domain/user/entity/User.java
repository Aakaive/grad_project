package com.sparta.grad_project.domain.user.entity;

import com.sparta.grad_project.domain.user.enums.UserRole;
import com.sparta.grad_project.domain.user.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    public User(String email, String password, String userName, UserRole role) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.status = UserStatus.ACTIVE;
    }
}
