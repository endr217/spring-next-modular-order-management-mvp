package com.example.ordermanagement.modules.orders.api.response;

import com.example.ordermanagement.modules.clients.api.response.ClientResponse;
import com.example.ordermanagement.modules.orders.domain.Order;
import com.example.ordermanagement.modules.orders.domain.OrderItem;
import com.example.ordermanagement.modules.orders.domain.OrderStatus;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderResponse(
    Long id,
    ClientResponse client,
    OrderStatus status,
    List<Item> items,
    BigDecimal totalAmount,
    Instant createdAt) {

  public record Item(
      Long productId, String productName, int quantity, BigDecimal unitPrice, BigDecimal subtotal) {

    static Item from(OrderItem item) {
      return new Item(
          item.getProduct().getId(),
          item.getProduct().getName(),
          item.getQuantity(),
          item.getUnitPrice(),
          item.getSubtotal());
    }
  }

  public static OrderResponse from(Order order) {
    return new OrderResponse(
        order.getId(),
        ClientResponse.from(order.getClient()),
        order.getStatus(),
        order.getItems().stream().map(Item::from).toList(),
        order.getTotalAmount(),
        order.getCreatedAt());
  }
}
