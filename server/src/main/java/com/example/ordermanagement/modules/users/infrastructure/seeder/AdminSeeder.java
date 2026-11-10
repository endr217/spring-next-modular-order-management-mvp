package com.example.ordermanagement.modules.users.infrastructure.seeder;

import com.example.ordermanagement.bootstrap.Seeder;
import com.example.ordermanagement.modules.users.domain.User;
import com.example.ordermanagement.modules.users.enums.RoleEnum;
import com.example.ordermanagement.modules.users.infrastructure.repository.RoleRepository;
import com.example.ordermanagement.modules.users.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class AdminSeeder implements Seeder {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final String username;
  private final String password;

  public AdminSeeder(
      UserRepository userRepository,
      RoleRepository roleRepository,
      PasswordEncoder passwordEncoder,
      @Value("${app.admin.username}") String username,
      @Value("${app.admin.password}") String password) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
    this.username = username;
    this.password = password;
  }

  @Override
  public void seed() {
    if (userRepository.existsByUsername(username)) {
      return;
    }

    User admin = new User(username, passwordEncoder.encode(password));
    roleRepository.findByName(RoleEnum.ROLE_ADMIN.name()).ifPresent(admin::addRole);
    userRepository.save(admin);
  }
}
