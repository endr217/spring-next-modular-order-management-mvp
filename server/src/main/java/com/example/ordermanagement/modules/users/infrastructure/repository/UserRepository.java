package com.example.ordermanagement.modules.users.infrastructure.repository;

import com.example.ordermanagement.modules.users.domain.User;
import com.example.ordermanagement.shared.repository.BaseRepository;
import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {
  Optional<User> findByUsername(String username);

  boolean existsByUsername(String username);
}
