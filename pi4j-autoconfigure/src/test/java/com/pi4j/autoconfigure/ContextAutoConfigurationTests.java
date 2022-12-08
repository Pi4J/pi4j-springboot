package com.pi4j.autoconfigure;

import com.pi4j.context.Context;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.autoconfigure.AutoConfigurations.of;

public class ContextAutoConfigurationTests {

    private final ApplicationContextRunner contextRunner =
            new ApplicationContextRunner()
                    .withConfiguration(
                            of(
                                    ContextAutoConfiguration.class
                            )
                    );

    @Test
    void whenOsArchIsNotExpected_verifyContextDoesNotExist() {

        this.contextRunner
                .withPropertyValues( "os.arch:other")
                .run( context -> assertThat( context ).doesNotHaveBean( Context.class ) );
    }

    @Test
    void whenOsArchIsArmv6l_verifyContextExists() {

        this.contextRunner
                .withPropertyValues( "os.arch:armv6l" )
                .run( context -> assertThat( context ).hasSingleBean( Context.class ) );
    }

    @Test
    void whenOsArchIsArmv7l_verifyContextExists() {

        this.contextRunner
                .withPropertyValues( "os.arch:armv7l" )
                .run( context -> assertThat( context ).hasSingleBean( Context.class ) );
    }

    @Test
    void whenOsArchIsArmv8l_verifyContextExists() {

        this.contextRunner
                .withPropertyValues( "os.arch:armv8l" )
                .run( context -> assertThat( context ).hasSingleBean( Context.class ) );
    }

    /*
     * aarch64 will also match Apple M processors
     */
    @Test
    void whenOsArchIsAarch64_verifyContextExists() {

        this.contextRunner
                .withPropertyValues( "os.arch:aarch64" )
                .run( context -> assertThat( context ).hasSingleBean( Context.class ) );
    }

}
