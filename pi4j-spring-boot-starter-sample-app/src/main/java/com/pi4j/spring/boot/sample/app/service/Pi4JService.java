package com.pi4j.spring.boot.sample.app.service;

import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.*;
import com.pi4j.spring.boot.sample.app.lcd.LcdDisplay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Service;

@Service
public class Pi4JService implements InfoContributor {

    private static final Logger logger = LoggerFactory.getLogger(Pi4JService.class);
    private static final int PIN_LED = 22; // PIN 15 = BCM 22
    private static final int PIN_BUTTON = 24; // PIN 18 = BCM 24
    private DigitalOutput led = null;
    private DigitalInput button = null;
    private LcdDisplay lcd = null;

    public Pi4JService(@Autowired Context pi4j) {
        try {
            // LED example is based on https://www.pi4j.com/getting-started/minimal-example-application/
            led = pi4j.digitalOutput().create(PIN_LED);
            logger.info("LED initialized on pin {}", PIN_LED);
        } catch (Exception e) {
            logger.error("Error while initializing the LED: {}", e.getMessage());
        }

        try {
            // Button example is based on https://www.pi4j.com/getting-started/minimal-example-application/
            var buttonConfig = DigitalInput.newConfigBuilder(pi4j)
                    .id("button")
                    .name("Press button")
                    .address(PIN_BUTTON)
                    .pull(PullResistance.PULL_DOWN)
                    .debounce(3000L);
            button = pi4j.create(buttonConfig);
            button.addListener(e -> handleButtonChange(e));
            logger.info("Button initialized on pin {}", PIN_BUTTON);
        } catch (Exception e) {
            logger.error("Error while initializing the button: {}", e.getMessage());
        }

        try {
            // LCD example is based on https://www.pi4j.com/examples/components/lcddisplay/
            // Make sure to enable I2C with `sudo raspi-config` > `Interface Options`
            lcd = new LcdDisplay(pi4j, 2, 16);
        } catch (Exception e) {
            logger.error("Error while initializing the LCD: {}", e.getMessage());
        }
    }

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("pi4j.status.led", led.state());
        builder.withDetail("pi4j.status.button", button.state());
    }

    private void handleButtonChange(DigitalStateChangeEvent e) {
        logger.info("Button state changed to {}", e.state());
    }

    public boolean setLedState(Boolean state) {
        if (led == null) {
            logger.error("LED is not initialized");
            return false;
        }

        try {
            led.state(state ? DigitalState.HIGH : DigitalState.LOW);
            logger.info("LED state is set to {}", state);
            return true;
        } catch (Exception e) {
            logger.error("Error while changing the LED status: {}", e.getMessage());
        }
        return false;
    }

    public DigitalState getButtonState() {
        if (button == null) {
            logger.error("Button is not initialized");
            return DigitalState.LOW;
        }

        return button.state();
    }

    public boolean setLcdText(Integer line, String text) {
        if (lcd == null) {
            logger.error("LCD is not initialized");
            return false;
        }

        try {
            lcd.displayLineOfText(text, line);
            logger.info("LCD text on line {} is set to {}", line, text);
            return true;
        } catch (Exception e) {
            logger.error("Error while changing the LCD text: {}", e.getMessage());
        }
        return false;
    }
}
