package com.example.ordermanagement.modules.orders.api.request;

import com.example.ordermanagement.modules.orders.domain.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record ChangeStatusRequest(
    @NotNull(message = "{order.status_required}") OrderStatus status) {}
