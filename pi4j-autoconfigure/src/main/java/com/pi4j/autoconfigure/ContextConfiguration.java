package com.pi4j.autoconfigure;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration( proxyBeanMethods = false )
@ConditionalOnClass( Context.class )
public class ContextConfiguration {

    @Bean
    @Conditional( ContextConditions.class )
    @ConditionalOnMissingBean
    Context context() {

        return Pi4J.newAutoContext();
    }

}
