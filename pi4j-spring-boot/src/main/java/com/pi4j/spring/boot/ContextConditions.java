package com.pi4j.spring.boot;

import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;

public class ContextConditions extends AnyNestedCondition {

    public ContextConditions() {
        super(ConfigurationPhase.REGISTER_BEAN);
    }

}
