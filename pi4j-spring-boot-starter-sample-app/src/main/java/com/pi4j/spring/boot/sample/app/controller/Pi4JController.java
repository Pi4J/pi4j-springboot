package com.pi4j.spring.boot.sample.app.controller;

import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.spring.boot.sample.app.service.Pi4JService;
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
    private final Pi4JService pi4JService;

    public Pi4JController(@Autowired Pi4JService pi4JService) {
        this.pi4JService = pi4JService;
    }

    @GetMapping("/led/{state}")
    public Boolean setLedStatus(@PathVariable Boolean state) {
        return pi4JService.setLedState(state);
    }

    @GetMapping("/button")
    public DigitalState setLedStatus() {
        return pi4JService.getButtonState();
    }

    @GetMapping("/lcd/{line}/{text}")
    public Boolean setLcdText(@PathVariable Integer line, @PathVariable String text) {
        return pi4JService.setLcdText(line, text);
    }
}
