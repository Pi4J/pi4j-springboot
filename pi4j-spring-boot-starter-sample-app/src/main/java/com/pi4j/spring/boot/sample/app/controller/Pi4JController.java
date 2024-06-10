package com.pi4j.spring.boot.sample.app.controller;

import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pi4j/")
public class Pi4JController {

    private static final Logger logger = LoggerFactory.getLogger(Pi4JController.class);
    private static final int PIN_LED = 22; // PIN 15 = BCM 22
    private final DigitalOutput led;

    public Pi4JController(@Autowired Context pi4j) {
        // LED example is based on https://www.pi4j.com/getting-started/minimal-example-application/
        led = pi4j.digitalOutput().create(PIN_LED);
        logger.info("LED initialized on pin {}", PIN_LED);
    }

    @GetMapping("/led/{status}")
    public String setLedStatus(@PathVariable Boolean status) {
        try {
            led.state(status ? DigitalState.HIGH : DigitalState.LOW);
            return "LED status set to " + (status ? "ON" : "OFF");
        } catch (Exception e) {
            return "Error while changing the LED status: " + e.getMessage();
        }
    }
}
