package com.example.KurGiangremake.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private LocalDateTime createdAt;
    private boolean active;

    // Constructor mặc định
    public User() {}

    // Constructor đầy đủ
    public User(Long id, String firstName, String lastName, String username, LocalDateTime createdAt, boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.createdAt = createdAt;
        this.active = active;
    }

    // Getter / Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
