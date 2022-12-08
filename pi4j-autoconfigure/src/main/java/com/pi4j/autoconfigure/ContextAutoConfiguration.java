package com.pi4j.autoconfigure;

import com.pi4j.context.Context;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Import;

@ConditionalOnClass( Context.class )
@Import( ContextConfiguration.class )
public class ContextAutoConfiguration {
}
