package com.example.ordermanagement.modules.users.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "user_roles")
public class UserRole {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "role_id", nullable = false)
  private Role role;

  @Column(name = "assigned_at")
  private Instant assignedAt;

  protected UserRole() {}

  public UserRole(User user, Role role) {
    this.user = user;
    this.role = role;
    this.assignedAt = Instant.now();
  }

  public Long getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public Role getRole() {
    return role;
  }

  public Instant getAssignedAt() {
    return assignedAt;
  }
}
