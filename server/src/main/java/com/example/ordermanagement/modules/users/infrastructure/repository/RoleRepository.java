package com.example.ordermanagement.modules.users.infrastructure.repository;

import com.example.ordermanagement.modules.users.domain.Role;
import com.example.ordermanagement.shared.repository.BaseRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {
  Optional<Role> findByName(String name);

  boolean existsByName(String name);
}
