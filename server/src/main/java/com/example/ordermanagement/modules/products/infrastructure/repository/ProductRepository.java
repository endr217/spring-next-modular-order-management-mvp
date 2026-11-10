package com.example.ordermanagement.modules.products.infrastructure.repository;

import com.example.ordermanagement.modules.products.domain.Product;
import com.example.ordermanagement.shared.repository.BaseRepository;
import jakarta.persistence.LockModeType;
import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends BaseRepository<Product, Long> {

  @EntityGraph(attributePaths = "category")
  @Query(
      """
      SELECT p FROM Product p
      WHERE (:search = '' OR LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%')))
        AND (:categoryId IS NULL OR p.category.id = :categoryId)
      """)
  Page<Product> search(
      @Param("search") String search, @Param("categoryId") Long categoryId, Pageable pageable);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("SELECT p FROM Product p WHERE p.id IN :ids ORDER BY p.id")
  List<Product> findAllForUpdate(@Param("ids") Collection<Long> ids);
}
