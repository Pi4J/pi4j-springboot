package com.pi4j.springboot;

import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.io.gpio.digital.PullResistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MinimalExampleGpio {

    private final int PIN_BUTTON = 24; // PIN 18 = BCM 24
    private final int PIN_LED = 22; // PIN 15 = BCM 22

    /**
     * Based on https://pi4j.com/getting-started/minimal-example-application/
     * @param context
     */
    public MinimalExampleGpio(@Autowired Context context) {
        var ledConfig = DigitalOutput.newConfigBuilder(context)
                .id("led")
                .name("LED Flasher")
                .address(PIN_LED)
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.LOW)
                .provider("pigpio-digital-output");
        var led = context.create(ledConfig);
        led.on();

        var buttonConfig = DigitalInput.newConfigBuilder(context)
                .id("button")
                .name("Press button")
                .address(PIN_BUTTON)
                .pull(PullResistance.PULL_DOWN)
                .debounce(3000L)
                .provider("pigpio-digital-input");
        var button = context.create(buttonConfig);
        button.addListener(e -> {
            if (e.state() == DigitalState.LOW) {
                //console.println("Button was pressed for the " + pressCount + "th time");
            }
        });
    }
}
