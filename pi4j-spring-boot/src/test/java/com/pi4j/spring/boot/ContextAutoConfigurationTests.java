package com.pi4j.spring.boot;

import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.springframework.boot.autoconfigure.AutoConfigurations.of;

public class ContextAutoConfigurationTests {

    private final ApplicationContextRunner contextRunner =
            new ApplicationContextRunner()
                    .withConfiguration(
                            of(
                                    ContextAutoConfiguration.class
                            )
                    );

}
