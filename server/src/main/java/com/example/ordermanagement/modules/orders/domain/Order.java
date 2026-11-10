package com.example.ordermanagement.modules.orders.domain;

import com.example.ordermanagement.modules.clients.domain.Client;
import com.example.ordermanagement.modules.products.domain.Product;
import com.example.ordermanagement.shared.exception.ConflictException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private OrderStatus status = OrderStatus.NEW;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderItem> items = new ArrayList<>();

  @Column(name = "total_amount", nullable = false, precision = 12, scale = 2)
  private BigDecimal totalAmount = BigDecimal.ZERO;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

  protected Order() {}

  public Order(Client client) {
    this.client = client;
  }

  public void addItem(Product product, int quantity) {
    product.takeFromStock(quantity);
    items.add(new OrderItem(this, product, quantity));
    recalculateTotal();
  }

  public void changeStatus(OrderStatus next) {
    if (!status.canChangeTo(next)) {
      throw new ConflictException("order.status_change_not_allowed", status.label(), next.label());
    }
    if (next == OrderStatus.CANCELLED) {
      items.forEach(item -> item.getProduct().returnToStock(item.getQuantity()));
    }
    status = next;
  }

  private void recalculateTotal() {
    totalAmount =
        items.stream().map(OrderItem::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public Long getId() {
    return id;
  }

  public Client getClient() {
    return client;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public List<OrderItem> getItems() {
    return List.copyOf(items);
  }

  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }
}
