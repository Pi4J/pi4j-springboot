package com.pi4j.spring.boot.sample.app;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.springframework.boot.autoconfigure.AutoConfigurations.of;

public class ContextConfigurationTests {

    private final ApplicationContextRunner contextRunner =
            new ApplicationContextRunner()
                    .withConfiguration(
                            of(
                                    TestConfiguration.class
                            )
                    );


    @SpringBootConfiguration
    @EnableAutoConfiguration
    static class TestConfiguration {
    }

}
