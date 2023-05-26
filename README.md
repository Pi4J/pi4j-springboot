# Pi4J Spring Boot Starter

This project provides a Spring Boot Starter to use the Pi4J V2 library in your Spring Boot project.

## How to use

* This starter requires Java 17 or higher.
* Multi-module project:
    * pi4j-spring-boot: auto-configure module
    * pi4j-spring-boot-starter: starter module
    * pi4j-spring-boot-starter-sample-app: example application to demonstrate and test the starter
* To run the sample app:
    * In IntelliJIDEA
    * Go to com.pi4j.spring.boot.sample.app.Application
    * Hit the run button
    * After the application has launched, open a browser and check:
        * http://localhost:8080/actuator/health
        * http://localhost:8080/actuator/info

## Notes

* All info about the Pi4J project can be found on [pi4j.com](https://pi4j.com/).
* TODO: describe this Spring Boot Starter on the Pi4J website.

## Thanks to

This project was started by Daniel Frey and got further improved by DaShaun Carter during a view live-coding sessions:

1. TODO link to first Twitch
2. [Working on Pi4J Spring Boot](https://www.twitch.tv/videos/1828406758)
3. [Spring Boot Pi4J Starter :: Testing my PR](https://www.twitch.tv/videos/1829189803)