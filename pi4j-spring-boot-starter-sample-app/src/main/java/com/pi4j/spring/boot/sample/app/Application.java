package com.pi4j.spring.boot.sample.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    // Expose with ngrok: `ngrok http --domain=pi4j.ngrok.dev 8080`

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}