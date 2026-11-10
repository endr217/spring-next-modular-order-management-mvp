package com.example.ordermanagement.modules.users.infrastructure.repository;

import com.example.ordermanagement.modules.users.domain.UserRole;
import com.example.ordermanagement.shared.repository.BaseRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends BaseRepository<UserRole, Long> {
  List<UserRole> findByUserId(Long userId);

  void deleteByUserIdAndRoleId(Long userId, Long roleId);
}
