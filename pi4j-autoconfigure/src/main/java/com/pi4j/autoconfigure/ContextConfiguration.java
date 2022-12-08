package com.pi4j.autoconfigure;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfiguration {

    @Bean
    @Conditional( ContextConditions.class )
    Context context() {

        return Pi4J.newAutoContext();
    }

}
