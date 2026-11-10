package com.example.ordermanagement.modules.products.infrastructure.repository;

import com.example.ordermanagement.modules.products.domain.Category;
import com.example.ordermanagement.shared.repository.BaseRepository;

public interface CategoryRepository extends BaseRepository<Category, Long> {

  boolean existsByNameIgnoreCase(String name);
}
