package com.example.ordermanagement.modules.clients.infrastructure.repository;

import com.example.ordermanagement.modules.clients.domain.Client;
import com.example.ordermanagement.shared.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends BaseRepository<Client, Long> {

  @Query(
      """
      SELECT c FROM Client c
      WHERE :search = ''
         OR LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%'))
         OR LOWER(c.email) LIKE LOWER(CONCAT('%', :search, '%'))
         OR c.phone LIKE CONCAT('%', :search, '%')
      """)
  Page<Client> search(@Param("search") String search, Pageable pageable);

  boolean existsByEmail(String email);

  boolean existsByEmailAndIdNot(String email, Long id);
}
