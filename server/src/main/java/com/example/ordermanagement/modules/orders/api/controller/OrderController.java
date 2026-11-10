package com.example.ordermanagement.modules.orders.api.controller;

import com.example.ordermanagement.modules.orders.api.request.ChangeStatusRequest;
import com.example.ordermanagement.modules.orders.api.request.CreateOrderRequest;
import com.example.ordermanagement.modules.orders.api.response.OrderResponse;
import com.example.ordermanagement.modules.orders.api.response.OrderSummaryResponse;
import com.example.ordermanagement.modules.orders.domain.OrderStatus;
import com.example.ordermanagement.modules.orders.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  public Page<OrderSummaryResponse> list(
      @RequestParam(required = false) OrderStatus status,
      @RequestParam(required = false) Long clientId,
      @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
          Pageable pageable) {
    return orderService.search(status, clientId, pageable);
  }

  @GetMapping("/{id}")
  public OrderResponse get(@PathVariable Long id) {
    return orderService.get(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OrderResponse create(@Valid @RequestBody CreateOrderRequest request) {
    return orderService.create(request);
  }

  @PatchMapping("/{id}/status")
  public OrderResponse changeStatus(
      @PathVariable Long id, @Valid @RequestBody ChangeStatusRequest request) {
    return orderService.changeStatus(id, request.status());
  }
}
