package com.pi4j.autoconfigure;

import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

public class ContextConditions extends AnyNestedCondition {

    public ContextConditions() {
        super( ConfigurationPhase.REGISTER_BEAN );
    }

    @ConditionalOnProperty( prefix = "os", name = "arch", havingValue = "armv6l" )
    static class Armv6l { }

    @ConditionalOnProperty( prefix = "os", name = "arch", havingValue = "armv7l" )
    static class Armv7l { }

    @ConditionalOnProperty( prefix = "os", name = "arch", havingValue = "armv8l" )
    static class Armv8l { }

    @ConditionalOnProperty( prefix = "os", name = "arch", havingValue = "aarch64" )
    static class Aarch64 { }

}
