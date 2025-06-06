package com.inditex.tech.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.inditex"})
@EnableJpaRepositories(basePackages = "com.inditex.tech.infrastructure")
@EntityScan(basePackages = "com.inditex.tech.infrastructure")
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
