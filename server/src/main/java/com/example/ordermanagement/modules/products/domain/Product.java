package com.example.ordermanagement.modules.products.domain;

import com.example.ordermanagement.shared.exception.ConflictException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "products")
public class Product {

  public static final int MAX_STOCK = 1_000_000;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 150)
  private String name;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal price;

  @Column(nullable = false)
  private int stock;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

  protected Product() {}

  public Product(String name, BigDecimal price, int stock, Category category) {
    update(name, price, stock, category);
  }

  public void update(String name, BigDecimal price, int stock, Category category) {
    this.name = name;
    this.price = price;
    this.stock = stock;
    this.category = category;
  }

  public void takeFromStock(int quantity) {
    if (quantity > stock) {
      throw new ConflictException("product.insufficient_stock", name, String.valueOf(stock));
    }
    stock -= quantity;
  }

  public void returnToStock(int quantity) {
    stock = Math.addExact(stock, quantity);
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public int getStock() {
    return stock;
  }

  public Category getCategory() {
    return category;
  }
}
