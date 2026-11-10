package com.example.ordermanagement.modules.orders.api.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderItemRequest(
    @NotNull(message = "{order.item.product_required}") Long productId,
    @NotNull(message = "{quantity.required}")
        @Min(value = 1, message = "{quantity.min}")
        @Max(value = 10000, message = "{quantity.max}")
        Integer quantity) {}
