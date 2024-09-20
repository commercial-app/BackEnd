package com.example.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column
    private Long point;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Member(Long userId, String email, String password, String name, Long point, Role role) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.point = point;
        this.role = role;
    }

    public Member() {
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Long getPoint() {
        return point;
    }

    public Role getRole() {
        return role;
    }
}
