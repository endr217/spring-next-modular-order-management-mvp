package com.example.ordermanagement.modules.clients.api.controller;

import com.example.ordermanagement.modules.clients.api.request.ClientRequest;
import com.example.ordermanagement.modules.clients.api.response.ClientResponse;
import com.example.ordermanagement.modules.clients.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {

  private final ClientService clientService;

  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @GetMapping
  public Page<ClientResponse> list(
      @RequestParam(required = false) String search,
      @PageableDefault(size = 20, sort = "name", direction = Sort.Direction.ASC)
          Pageable pageable) {
    return clientService.search(search, pageable);
  }

  @GetMapping("/{id}")
  public ClientResponse get(@PathVariable Long id) {
    return clientService.get(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ClientResponse create(@Valid @RequestBody ClientRequest request) {
    return clientService.create(request);
  }

  @PutMapping("/{id}")
  public ClientResponse update(@PathVariable Long id, @Valid @RequestBody ClientRequest request) {
    return clientService.update(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    clientService.delete(id);
  }
}
