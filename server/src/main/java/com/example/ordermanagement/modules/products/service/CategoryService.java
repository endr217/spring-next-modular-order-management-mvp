package com.example.ordermanagement.modules.products.service;

import com.example.ordermanagement.modules.products.api.request.CategoryRequest;
import com.example.ordermanagement.modules.products.api.response.CategoryResponse;
import com.example.ordermanagement.modules.products.domain.Category;
import com.example.ordermanagement.modules.products.infrastructure.repository.CategoryRepository;
import com.example.ordermanagement.shared.exception.ConflictException;
import com.example.ordermanagement.shared.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<CategoryResponse> list() {
    return categoryRepository.findAll(Sort.by("name")).stream()
        .map(CategoryResponse::from)
        .toList();
  }

  @Transactional
  public CategoryResponse create(CategoryRequest request) {
    String name = request.name().trim();
    if (categoryRepository.existsByNameIgnoreCase(name)) {
      throw new ConflictException("category.name_taken");
    }
    return CategoryResponse.from(categoryRepository.save(new Category(name)));
  }

  public Category find(Long id) {
    return categoryRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("category.not_found"));
  }
}
