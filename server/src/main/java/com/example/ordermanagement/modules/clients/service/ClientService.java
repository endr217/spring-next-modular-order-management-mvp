package com.example.ordermanagement.modules.clients.service;

import com.example.ordermanagement.modules.clients.api.request.ClientRequest;
import com.example.ordermanagement.modules.clients.api.response.ClientResponse;
import com.example.ordermanagement.modules.clients.domain.Client;
import com.example.ordermanagement.modules.clients.infrastructure.repository.ClientRepository;
import com.example.ordermanagement.modules.orders.infrastructure.repository.OrderRepository;
import com.example.ordermanagement.shared.exception.ConflictException;
import com.example.ordermanagement.shared.exception.ResourceNotFoundException;
import java.util.Locale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ClientService {

  private final ClientRepository clientRepository;
  private final OrderRepository orderRepository;

  public ClientService(ClientRepository clientRepository, OrderRepository orderRepository) {
    this.clientRepository = clientRepository;
    this.orderRepository = orderRepository;
  }

  public Page<ClientResponse> search(String search, Pageable pageable) {
    String query = search == null ? "" : search.trim();
    return clientRepository.search(query, pageable).map(ClientResponse::from);
  }

  public ClientResponse get(Long id) {
    return ClientResponse.from(find(id));
  }

  @Transactional
  public ClientResponse create(ClientRequest request) {
    String email = normalizeEmail(request.email());

    if (clientRepository.existsByEmail(email)) {
      throw new ConflictException("client.email_taken");
    }

    Client client =
        new Client(
            request.name().trim(),
            email,
            blankToNull(request.phone()),
            blankToNull(request.address()));

    return ClientResponse.from(clientRepository.save(client));
  }

  @Transactional
  public ClientResponse update(Long id, ClientRequest request) {
    Client client = find(id);
    String email = normalizeEmail(request.email());

    if (clientRepository.existsByEmailAndIdNot(email, id)) {
      throw new ConflictException("client.email_taken");
    }

    client.update(
        request.name().trim(), email, blankToNull(request.phone()), blankToNull(request.address()));

    return ClientResponse.from(client);
  }

  @Transactional
  public void delete(Long id) {
    Client client = find(id);

    if (orderRepository.existsByClientId(id)) {
      throw new ConflictException("client.has_orders");
    }

    clientRepository.delete(client);
  }

  public Client find(Long id) {
    return clientRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("client.not_found"));
  }

  private static String normalizeEmail(String email) {
    return email.trim().toLowerCase(Locale.ROOT);
  }

  private static String blankToNull(String value) {
    return value == null || value.isBlank() ? null : value.trim();
  }
}
