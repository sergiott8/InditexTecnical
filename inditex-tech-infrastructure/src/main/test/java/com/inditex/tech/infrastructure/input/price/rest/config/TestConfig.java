package com.inditex.tech.infrastructure.input.price.rest.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.inditex.tech"})
public class TestConfig {
    // This class is intentionally empty. It's used as a configuration class for the integration tests.
}