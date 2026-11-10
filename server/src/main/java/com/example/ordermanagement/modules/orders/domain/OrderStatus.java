package com.example.ordermanagement.modules.orders.domain;

import java.util.Set;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;

public enum OrderStatus {
  NEW,
  IN_PROGRESS,
  COMPLETED,
  CANCELLED;

  public boolean canChangeTo(OrderStatus next) {
    return allowedNext().contains(next);
  }

  public MessageSourceResolvable label() {
    return new DefaultMessageSourceResolvable(new String[] {"order.status." + name()}, name());
  }

  private Set<OrderStatus> allowedNext() {
    return switch (this) {
      case NEW -> Set.of(IN_PROGRESS, CANCELLED);
      case IN_PROGRESS -> Set.of(COMPLETED, CANCELLED);
      case COMPLETED, CANCELLED -> Set.of();
    };
  }
}
