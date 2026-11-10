package com.example.ordermanagement.modules.orders.infrastructure.repository;

import com.example.ordermanagement.modules.orders.domain.OrderItem;
import com.example.ordermanagement.shared.repository.BaseRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderItemRepository extends BaseRepository<OrderItem, Long> {

  boolean existsByProductId(Long productId);

  @Query("SELECT i.product.id FROM OrderItem i WHERE i.order.id = :orderId")
  List<Long> findProductIdsByOrderId(@Param("orderId") Long orderId);
}
