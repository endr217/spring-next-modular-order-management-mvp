package com.example.ordermanagement.modules.products.api.response;

import com.example.ordermanagement.modules.products.domain.Category;

public record CategoryResponse(Long id, String name) {

  public static CategoryResponse from(Category category) {
    return new CategoryResponse(category.getId(), category.getName());
  }
}
