package com.example.ordermanagement.modules.orders.api.response;

import com.example.ordermanagement.modules.orders.domain.Order;
import com.example.ordermanagement.modules.orders.domain.OrderStatus;
import java.math.BigDecimal;
import java.time.Instant;

public record OrderSummaryResponse(
    Long id,
    Long clientId,
    String clientName,
    OrderStatus status,
    BigDecimal totalAmount,
    Instant createdAt) {

  public static OrderSummaryResponse from(Order order) {
    return new OrderSummaryResponse(
        order.getId(),
        order.getClient().getId(),
        order.getClient().getName(),
        order.getStatus(),
        order.getTotalAmount(),
        order.getCreatedAt());
  }
}
