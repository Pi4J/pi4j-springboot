package com.pi4j.spring.boot;

import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.boot.autoconfigure.AutoConfigurations.of;

@Configuration
public class ContextAutoConfigurationTests {

    private final ApplicationContextRunner contextRunner =
            new ApplicationContextRunner()
                    .withConfiguration(
                            of(
                                    ContextAutoConfiguration.class
                            )
                    );

    @Bean
    public ContextConfiguration contextConfiguration() {
        return new ContextConfiguration();
    }
}
