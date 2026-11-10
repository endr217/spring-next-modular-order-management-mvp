package com.example.ordermanagement.modules.products.infrastructure.seeder;

import com.example.ordermanagement.bootstrap.Seeder;
import com.example.ordermanagement.modules.products.domain.Category;
import com.example.ordermanagement.modules.products.infrastructure.repository.CategoryRepository;
import java.util.List;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class CategorySeeder implements Seeder {

  private static final List<String> DEFAULTS =
      List.of("Electronics", "Office supplies", "Home appliances", "Other");

  private final CategoryRepository categoryRepository;

  public CategorySeeder(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public void seed() {
    DEFAULTS.stream()
        .filter(name -> !categoryRepository.existsByNameIgnoreCase(name))
        .forEach(name -> categoryRepository.save(new Category(name)));
  }
}
