package com.example.ordermanagement.modules.orders.api.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record CreateOrderRequest(
    @NotNull(message = "{order.client_required}") Long clientId,
    @NotEmpty(message = "{order.items_required}")
        List<@Valid @NotNull(message = "{order.item_empty}") OrderItemRequest> items) {}
