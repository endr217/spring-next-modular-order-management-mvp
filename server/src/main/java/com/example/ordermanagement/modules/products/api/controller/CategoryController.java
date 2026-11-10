package com.example.ordermanagement.modules.products.api.controller;

import com.example.ordermanagement.modules.products.api.request.CategoryRequest;
import com.example.ordermanagement.modules.products.api.response.CategoryResponse;
import com.example.ordermanagement.modules.products.service.CategoryService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public List<CategoryResponse> list() {
    return categoryService.list();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CategoryResponse create(@Valid @RequestBody CategoryRequest request) {
    return categoryService.create(request);
  }
}
