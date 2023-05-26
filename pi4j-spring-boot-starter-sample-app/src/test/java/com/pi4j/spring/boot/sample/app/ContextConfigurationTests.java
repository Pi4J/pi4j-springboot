package com.pi4j.spring.boot.sample.app;

import com.pi4j.context.Context;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.autoconfigure.AutoConfigurations.of;

public class ContextConfigurationTests {

    private final ApplicationContextRunner contextRunner =
            new ApplicationContextRunner()
                    .withConfiguration(
                            of(
                                    TestConfiguration.class
                            )
                    );


    @ParameterizedTest
    @ValueSource(strings = {"armv6l", "armv7l", "armv8l", "aarch64"})
    void whenOsArchIsProvided_verifyContextExists(String propertyName) {

        this.contextRunner
                .withPropertyValues(String.format("os.arch:%s", propertyName))
                .run(context -> {

                    assertThat(context.getEnvironment().containsProperty("os.arch")).isTrue();
                    assertThat(context.getEnvironment().getProperty("os.arch")).isEqualTo(propertyName);
                    assertThat(context).hasSingleBean(Context.class);

                });

    }

    @Test
    void whenOsArchIsNotExpected_verifyContextDoesNotExist() {

        this.contextRunner
                .withPropertyValues("os.arch:other")
                .run(context -> {

                    assertThat(context.getEnvironment().containsProperty("os.arch")).isTrue();
                    assertThat(context.getEnvironment().getProperty("os.arch")).isEqualTo("other");
                    assertThat(context).doesNotHaveBean(Context.class);

                });

    }

    @SpringBootConfiguration
    @EnableAutoConfiguration
    static class TestConfiguration {
    }

}
