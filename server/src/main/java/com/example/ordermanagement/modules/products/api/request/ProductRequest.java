package com.example.ordermanagement.modules.products.api.request;

import com.example.ordermanagement.modules.products.domain.Product;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record ProductRequest(
    @NotBlank(message = "{product.name_required}") @Size(max = 150, message = "{product.name_size}")
        String name,
    @NotNull(message = "{product.price_required}")
        @DecimalMin(value = "0.00", message = "{product.price_min}")
        @DecimalMax(value = "99999999.99", message = "{product.price_max}")
        @Digits(integer = 8, fraction = 2, message = "{product.price_digits}")
        BigDecimal price,
    @NotNull(message = "{product.stock_required}")
        @Min(value = 0, message = "{product.stock_min}")
        @Max(value = Product.MAX_STOCK, message = "{product.stock_max}")
        Integer stock,
    @NotNull(message = "{product.category_required}") Long categoryId) {}
