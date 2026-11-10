package com.example.ordermanagement.modules.products.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
    @NotBlank(message = "{category.name_required}")
        @Size(max = 100, message = "{category.name_size}")
        String name) {}
