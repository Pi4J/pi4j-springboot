package com.pi4j.spring.boot.sample.app.service;

import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalStateChangeEvent;
import com.pi4j.io.gpio.digital.PullResistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Pi4JService {

    private static final Logger logger = LoggerFactory.getLogger(Pi4JService.class);
    private static final int PIN_BUTTON = 24; // PIN 18 = BCM 24

    public Pi4JService(@Autowired Context pi4j) {
        // Button example is based on https://www.pi4j.com/getting-started/minimal-example-application/
        var buttonConfig = DigitalInput.newConfigBuilder(pi4j)
                .id("button")
                .name("Press button")
                .address(PIN_BUTTON)
                .pull(PullResistance.PULL_DOWN)
                .debounce(3000L);
        var button = pi4j.create(buttonConfig);
        button.addListener(e -> handleButtonChange(e));
        logger.info("Button initialized on pin {}", PIN_BUTTON);
    }

    private void handleButtonChange(DigitalStateChangeEvent e) {
        logger.info("Button state changed to {}", e.state());
    }
}
