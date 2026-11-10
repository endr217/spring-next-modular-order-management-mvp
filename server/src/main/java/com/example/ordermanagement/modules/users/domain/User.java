package com.example.ordermanagement.modules.users.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(name = "password_hash", nullable = false)
  private String passwordHash;

  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  @OneToMany(
      mappedBy = "user",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.EAGER)
  private Set<UserRole> userRoles = new HashSet<>();

  protected User() {}

  public User(String username, String passwordHash) {
    this.username = username.trim();
    this.passwordHash = passwordHash;
    this.createdAt = Instant.now();
  }

  public void addRole(Role role) {
    if (hasRole(role.getName())) {
      return;
    }
    userRoles.add(new UserRole(this, role));
  }

  public boolean hasRole(String roleName) {
    return userRoles.stream().anyMatch(userRole -> userRole.getRole().getName().equals(roleName));
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Set<UserRole> getUserRoles() {
    return userRoles;
  }
}
