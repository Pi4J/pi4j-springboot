package com.pi4j.spring.boot.sample.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class InfoController {

    @GetMapping()
    public String getInfo() {
        return """
                Available URLS: 
                <ul>
                    <li><a href="/actuator/health" target="_blank">Actuator Health</a></li>
                    <li><a href="/actuator/info" target="_blank">Actuator Info</a></li>
                    <li><a href="/api/pi4j/led/true" target="_blank">LED on</a></li>
                    <li><a href="/api/pi4j/led/false" target="_blank">LED off</a></li>
                    <li><a href="/api/pi4j/button" target="_blank">Button state</a></li>
                    <li><a href="/api/pi4j/lcd/0/hello" target="_blank">LCD text on upper row</a></li>
                    <li><a href="/api/pi4j/lcd/1/world" target="_blank">LCD text on lower row</a></li>
                </ul>
                """;
    }
}
