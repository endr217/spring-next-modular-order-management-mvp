package com.example.ordermanagement.modules.orders.service;

import com.example.ordermanagement.modules.clients.domain.Client;
import com.example.ordermanagement.modules.clients.service.ClientService;
import com.example.ordermanagement.modules.orders.api.request.CreateOrderRequest;
import com.example.ordermanagement.modules.orders.api.request.OrderItemRequest;
import com.example.ordermanagement.modules.orders.api.response.OrderResponse;
import com.example.ordermanagement.modules.orders.api.response.OrderSummaryResponse;
import com.example.ordermanagement.modules.orders.domain.Order;
import com.example.ordermanagement.modules.orders.domain.OrderStatus;
import com.example.ordermanagement.modules.orders.infrastructure.repository.OrderItemRepository;
import com.example.ordermanagement.modules.orders.infrastructure.repository.OrderRepository;
import com.example.ordermanagement.modules.products.domain.Product;
import com.example.ordermanagement.modules.products.service.ProductService;
import com.example.ordermanagement.shared.exception.ResourceNotFoundException;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OrderService {

  private final OrderRepository orderRepository;
  private final OrderItemRepository orderItemRepository;
  private final ProductService productService;
  private final ClientService clientService;

  public OrderService(
      OrderRepository orderRepository,
      OrderItemRepository orderItemRepository,
      ProductService productService,
      ClientService clientService) {
    this.orderRepository = orderRepository;
    this.orderItemRepository = orderItemRepository;
    this.productService = productService;
    this.clientService = clientService;
  }

  public Page<OrderSummaryResponse> search(OrderStatus status, Long clientId, Pageable pageable) {
    return orderRepository.search(status, clientId, pageable).map(OrderSummaryResponse::from);
  }

  public OrderResponse get(Long id) {
    return OrderResponse.from(find(id));
  }

  @Transactional
  public OrderResponse create(CreateOrderRequest request) {
    Client client = clientService.find(request.clientId());

    Map<Long, Integer> quantities =
        request.items().stream()
            .collect(
                Collectors.toMap(
                    OrderItemRequest::productId,
                    OrderItemRequest::quantity,
                    Integer::sum,
                    TreeMap::new));

    Map<Long, Product> products =
        productService.lockAll(quantities.keySet()).stream()
            .collect(Collectors.toMap(Product::getId, Function.identity()));

    Order order = new Order(client);
    quantities.forEach((productId, quantity) -> {
      Product product = products.get(productId);
      if (product == null) {
        throw new ResourceNotFoundException("product.not_found_id", String.valueOf(productId));
      }
      order.addItem(product, quantity);
    });

    return OrderResponse.from(orderRepository.save(order));
  }

  @Transactional
  public OrderResponse changeStatus(Long id, OrderStatus status) {
    if (status == OrderStatus.CANCELLED) {
      productService.lockAll(orderItemRepository.findProductIdsByOrderId(id));
    }

    Order order = find(id);
    order.changeStatus(status);

    return OrderResponse.from(order);
  }

  private Order find(Long id) {
    return orderRepository
        .findWithItems(id)
        .orElseThrow(() -> new ResourceNotFoundException("order.not_found"));
  }
}
