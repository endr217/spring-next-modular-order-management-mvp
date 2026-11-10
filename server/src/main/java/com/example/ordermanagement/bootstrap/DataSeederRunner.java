package com.example.ordermanagement.bootstrap;

import java.util.List;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataSeederRunner implements ApplicationRunner {

  private final List<Seeder> seeders;

  public DataSeederRunner(List<Seeder> seeders) {
    this.seeders = seeders;
  }

  @Override
  @Transactional
  public void run(ApplicationArguments args) {
    seeders.forEach(Seeder::seed);
  }
}
