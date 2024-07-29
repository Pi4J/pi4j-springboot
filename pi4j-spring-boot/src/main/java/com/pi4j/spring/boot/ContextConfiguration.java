package com.pi4j.spring.boot;

import com.pi4j.Pi4J;
import com.pi4j.boardinfo.definition.BoardModel;
import com.pi4j.boardinfo.util.BoardInfoHelper;
import com.pi4j.context.Context;
import com.pi4j.plugin.mock.platform.MockPlatform;
import com.pi4j.plugin.mock.provider.gpio.analog.MockAnalogInputProvider;
import com.pi4j.plugin.mock.provider.gpio.analog.MockAnalogOutputProvider;
import com.pi4j.plugin.mock.provider.gpio.digital.MockDigitalInputProvider;
import com.pi4j.plugin.mock.provider.gpio.digital.MockDigitalOutputProvider;
import com.pi4j.plugin.mock.provider.i2c.MockI2CProvider;
import com.pi4j.plugin.mock.provider.pwm.MockPwmProvider;
import com.pi4j.plugin.mock.provider.serial.MockSerialProvider;
import com.pi4j.plugin.mock.provider.spi.MockSpiProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(Context.class)
public class ContextConfiguration {
    private final Logger logger = LoggerFactory.getLogger(ContextConfiguration.class);
    private Context pi4j;

    public ContextConfiguration() {
        try {
            if (BoardInfoHelper.current().getBoardModel() == BoardModel.UNKNOWN) {
                logger.warn("The Pi4J library could not detect that this system is a Raspberry Pi board.");
                logger.warn("For this reason, Mock implementations will be loaded for all I/O.");
                logger.warn("This means, you can test most functionality of the Pi4J library, but it will not try to interact with I/Os.");
                this.pi4j = Pi4J.newContextBuilder()
                        .add(new MockPlatform())
                        .add(MockAnalogInputProvider.newInstance(),
                                MockAnalogOutputProvider.newInstance(),
                                MockSpiProvider.newInstance(),
                                MockPwmProvider.newInstance(),
                                MockSerialProvider.newInstance(),
                                MockI2CProvider.newInstance(),
                                MockDigitalInputProvider.newInstance(),
                                MockDigitalOutputProvider.newInstance())
                        .build();
            } else {
                this.pi4j = Pi4J.newAutoContext();
            }
        } catch (Error e) {
            logger.error("Pi4J library failed to load: {}", e.getMessage());
        }
    }

    @Bean
    @ConditionalOnMissingBean
    Context context() {
        return pi4j;
    }
}
