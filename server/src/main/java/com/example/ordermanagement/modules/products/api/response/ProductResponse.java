package com.example.ordermanagement.modules.products.api.response;

import com.example.ordermanagement.modules.products.domain.Product;
import java.math.BigDecimal;

public record ProductResponse(
    Long id, String name, BigDecimal price, int stock, CategoryResponse category) {

  public static ProductResponse from(Product product) {
    return new ProductResponse(
        product.getId(),
        product.getName(),
        product.getPrice(),
        product.getStock(),
        CategoryResponse.from(product.getCategory()));
  }
}
