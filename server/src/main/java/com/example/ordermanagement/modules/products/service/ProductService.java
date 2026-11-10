package com.example.ordermanagement.modules.products.service;

import com.example.ordermanagement.modules.orders.infrastructure.repository.OrderItemRepository;
import com.example.ordermanagement.modules.products.api.request.ProductRequest;
import com.example.ordermanagement.modules.products.api.response.ProductResponse;
import com.example.ordermanagement.modules.products.domain.Category;
import com.example.ordermanagement.modules.products.domain.Product;
import com.example.ordermanagement.modules.products.infrastructure.repository.ProductRepository;
import com.example.ordermanagement.shared.exception.ConflictException;
import com.example.ordermanagement.shared.exception.ResourceNotFoundException;
import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProductService {

  private final ProductRepository productRepository;
  private final CategoryService categoryService;
  private final OrderItemRepository orderItemRepository;

  public ProductService(
      ProductRepository productRepository,
      CategoryService categoryService,
      OrderItemRepository orderItemRepository) {
    this.productRepository = productRepository;
    this.categoryService = categoryService;
    this.orderItemRepository = orderItemRepository;
  }

  public Page<ProductResponse> search(String search, Long categoryId, Pageable pageable) {
    String query = search == null ? "" : search.trim();
    return productRepository.search(query, categoryId, pageable).map(ProductResponse::from);
  }

  public ProductResponse get(Long id) {
    return ProductResponse.from(find(id));
  }

  @Transactional
  public ProductResponse create(ProductRequest request) {
    Category category = categoryService.find(request.categoryId());
    Product product =
        new Product(request.name().trim(), request.price(), request.stock(), category);
    return ProductResponse.from(productRepository.save(product));
  }

  @Transactional
  public ProductResponse update(Long id, ProductRequest request) {
    Product product = find(id);
    Category category = categoryService.find(request.categoryId());
    product.update(request.name().trim(), request.price(), request.stock(), category);
    return ProductResponse.from(product);
  }

  @Transactional
  public void delete(Long id) {
    Product product = find(id);
    if (orderItemRepository.existsByProductId(id)) {
      throw new ConflictException("product.in_orders");
    }
    productRepository.delete(product);
  }

  @Transactional(propagation = Propagation.MANDATORY)
  public List<Product> lockAll(Collection<Long> ids) {
    return productRepository.findAllForUpdate(ids);
  }

  public Product find(Long id) {
    return productRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("product.not_found"));
  }
}
