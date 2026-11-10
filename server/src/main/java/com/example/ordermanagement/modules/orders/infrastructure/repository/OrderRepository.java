package com.example.ordermanagement.modules.orders.infrastructure.repository;

import com.example.ordermanagement.modules.orders.domain.Order;
import com.example.ordermanagement.modules.orders.domain.OrderStatus;
import com.example.ordermanagement.shared.repository.BaseRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends BaseRepository<Order, Long> {

  @EntityGraph(attributePaths = "client")
  @Query(
      """
      SELECT o FROM Order o
      WHERE (:status IS NULL OR o.status = :status)
        AND (:clientId IS NULL OR o.client.id = :clientId)
      """)
  Page<Order> search(
      @Param("status") OrderStatus status, @Param("clientId") Long clientId, Pageable pageable);

  @EntityGraph(attributePaths = {"client", "items", "items.product"})
  @Query("SELECT o FROM Order o WHERE o.id = :id")
  Optional<Order> findWithItems(@Param("id") Long id);

  boolean existsByClientId(Long clientId);
}
