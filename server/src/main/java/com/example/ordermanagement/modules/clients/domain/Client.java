package com.example.ordermanagement.modules.clients.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "clients")
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 150)
  private String name;

  @Column(nullable = false, unique = true, length = 150)
  private String email;

  @Column(length = 30)
  private String phone;

  private String address;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

  protected Client() {}

  public Client(String name, String email, String phone, String address) {
    update(name, email, phone, address);
  }

  public void update(String name, String email, String phone, String address) {
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.address = address;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public String getAddress() {
    return address;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }
}
