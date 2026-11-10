package com.example.ordermanagement.modules.users.infrastructure.seeder;

import com.example.ordermanagement.bootstrap.Seeder;
import com.example.ordermanagement.modules.users.domain.Role;
import com.example.ordermanagement.modules.users.enums.RoleEnum;
import com.example.ordermanagement.modules.users.infrastructure.repository.RoleRepository;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class RoleSeeder implements Seeder {

  private final RoleRepository roleRepository;

  public RoleSeeder(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public void seed() {
    for (RoleEnum roleEnum : RoleEnum.values()) {
      if (!roleRepository.existsByName(roleEnum.name())) {
        Role role = new Role(roleEnum.name());
        roleRepository.save(role);
      }
    }
  }
}
