package com.example.ordermanagement.modules.clients.api.response;

import com.example.ordermanagement.modules.clients.domain.Client;
import java.time.Instant;

public record ClientResponse(
    Long id, String name, String email, String phone, String address, Instant createdAt) {

  public static ClientResponse from(Client client) {
    return new ClientResponse(
        client.getId(),
        client.getName(),
        client.getEmail(),
        client.getPhone(),
        client.getAddress(),
        client.getCreatedAt());
  }
}
