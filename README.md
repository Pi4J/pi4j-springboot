# Pi4J Spring Boot Starter

![GitHub Actions build state](https://github.com/Pi4J/pi4j-springboot/actions/workflows/build.yml/badge.svg) [![License](https://img.shields.io/github/license/pi4j/pi4j-v2)](http://www.apache.org/licenses/LICENSE-2.0)

[![Chat on Slack](https://img.shields.io/badge/Chat-on%20Slack-blue)](https://join.slack.com/t/pi4j/shared_invite/zt-1ttqt8wgj-E6t69qaLrNuCMPLiYnBCsg)
[![Site](https://img.shields.io/badge/Website-pi4j.com-green)](https://pi4j.com)
[![Twitter Follow](https://img.shields.io/twitter/follow/pi4j?label=Pi4J&style=social)](https://twitter.com/pi4j)

---

This project provides a Spring Boot Starter to use the Pi4J library in your Spring Boot project and control electronic
components connected to the GPIO pins of a Raspberry Pi.

## About this project

* This starter requires **Java 21 or higher**.
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
    * Change the connected components:
        * LED on: http://localhost:8080/api/pi4j/led/true
        * LED off: http://localhost:8080/api/pi4j/led/false
        * Get the button state: http://localhost:8080/api/pi4j/button
        * Set text on the LCD: http://localhost:8080/api/pi4j/lcd/0/test

## Running the sample application on a Raspberry Pi

* Start from Raspberry Pi OS
  and install Java and Maven, or [follow the instructions on the Pi4J website](https://www.pi4j.com/prepare/).
* Check the Java version, should be 21 or newer.

```shell
$ java -version
openjdk version "21.0.6" 2025-01-21 LTS
OpenJDK Runtime Environment Zulu21.40+17-CA (build 21.0.6+7-LTS)
OpenJDK 64-Bit Server VM Zulu21.40+17-CA (build 21.0.6+7-LTS, mixed mode, sharing)
```

* If Java is not installed yet, use SDKMAN.

```shell
$ sudo apt install zip
$ curl -s "https://get.sdkman.io" | bash 
# Open new terminal
$ sdk install java 21.0.6-zulu
```

* Get this project from GitHub and package it.

```shell
$ git clone https://github.com/Pi4J/pi4j-springboot.git
$ cd pi4j-springboot
$ chmod +x mvnw
$ ./mvnw package
```

* Depending on the type of board, you may need to start the application with `sudo`. This depends on which type of I/Os
  you use and the Pi4J providers which are used.
  See [Choosing an I/O Provider](https://www.pi4j.com/documentation/providers/) for more info
  about the providers. All the providers are included in this Spring Boot Starter and during startup of the application,
  you can see in the logging which get loaded based on the type of board.

```shell
$ sudo java -jar target/pi4j-spring-boot-starter-sample-app-0.0.1.jar 

# Or when installed with SDKMAN
$ sudo ~/.sdkman/candidates/java/19.0.2-zulu/bin/java -jar target/pi4j-spring-boot-starter-sample-app-0.0.1.jar
```

* Check the output of the application:

```shell

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.5)

[pi4j-spring-boot-sample-app] [           main] c.p.spring.boot.sample.app.Application   : Starting Application using Java 22.0.2 with PID 62220 (/Users/frank/Documents/GitHub/pi4j-springboot/pi4j-spring-boot-starter-sample-app/target/classes started by frank in /Users/frank/Documents/GitHub/pi4j-springboot)
[pi4j-spring-boot-sample-app] [           main] c.p.spring.boot.sample.app.Application   : No active profile set, falling back to 1 default profile: "default"
[pi4j-spring-boot-sample-app] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
[pi4j-spring-boot-sample-app] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
[pi4j-spring-boot-sample-app] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.20]
[pi4j-spring-boot-sample-app] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
[pi4j-spring-boot-sample-app] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 375 ms
[pi4j-spring-boot-sample-app] [           main] com.pi4j.Pi4J                            : New auto context
[pi4j-spring-boot-sample-app] [           main] com.pi4j.Pi4J                            : New context builder
[pi4j-spring-boot-sample-app] [           main] c.pi4j.boardinfo.util.BoardInfoHelper    : Detected OS: Name: Mac OS X, version: 15.0, architecture: aarch64
[pi4j-spring-boot-sample-app] [           main] c.pi4j.boardinfo.util.BoardInfoHelper    : Detected Java: Version: 22.0.2, runtime: 22.0.2+9, vendor: Azul Systems, Inc., vendor version: Zulu22.32+15-CA
...
```

* Open browser with `http://{RASPBERRY_PI_IP_ADDRESS}:8080/actuator/info`
* Output on a Raspberry Pi 4 (but truncated the pins-section):
    ```json
    {
      "pi4j.status.led": "HIGH",
      "pi4j.status.button": "LOW",
      "os.name": "Linux",
      "os.architecture": "aarch64",
      "os.version": "6.6.31+rpt-rpi-2712",
      "board.name": "MODEL_5_B",
      "board.description": "Raspberry Pi 5 Model B",
      "board.model.label": "Model B",
      "board.cpu.label": "Cortex-A76",
      "board.soc": "BCM2712",
      "java.version": "17.0.11",
      "java.runtime": "17.0.11+9",
      "java.vendor": "Eclipse Adoptium",
      "java.vendor.version": "Temurin-17.0.11+9",
      "reading.volt.value": 0.72,
      "reading.temperature.celsius": 85.1,
      "reading.temperature.fahrenheit": 185.18,
      "reading.uptime": "18:26:04 up 33 min,  2 users,  load average: 2.31, 1.54, 1.09",
      "platform.current": "RaspberryPi Platform",
      "platform.raspberrypi.name": "RaspberryPi Platform",
      "platform.raspberrypi.description": "Pi4J Platform for the RaspberryPi series of products.",
      "provider.pigpio-spi.name": "PiGpio SPI Provider",
      "provider.pigpio-spi.description": "com.pi4j.plugin.pigpio.provider.spi.PiGpioSpiProviderImpl",
      "provider.pigpio-spi.type.name": "SPI",
      "provider.gpiod-digital-output.name": "GpioD Digital Output (GPIO) Provider",
      "provider.gpiod-digital-output.description": "com.pi4j.plugin.gpiod.provider.gpio.digital.GpioDDigitalOutputProviderImpl",
      "provider.gpiod-digital-output.type.name": "DIGITAL_OUTPUT",
      "provider.pigpio-serial.name": "PiGpio Serial Provider",
      "provider.pigpio-serial.description": "com.pi4j.plugin.pigpio.provider.serial.PiGpioSerialProviderImpl",
      "provider.pigpio-serial.type.name": "SERIAL",
      "provider.linuxfs-pwm.name": "LinuxFS PWM Provider",
      "provider.linuxfs-pwm.description": "com.pi4j.plugin.linuxfs.provider.pwm.LinuxFsPwmProviderImpl",
      "provider.linuxfs-pwm.type.name": "PWM",
      "provider.gpiod-digital-input.name": "GpioD Digital Input (GPIO) Provider",
      "provider.gpiod-digital-input.description": "com.pi4j.plugin.gpiod.provider.gpio.digital.GpioDDigitalInputProviderImpl",
      "provider.gpiod-digital-input.type.name": "DIGITAL_INPUT",
      "provider.linuxfs-i2c.name": "LinuxFS I2C Provider",
      "provider.linuxfs-i2c.description": "com.pi4j.plugin.linuxfs.provider.i2c.LinuxFsI2CProviderImpl",
      "provider.linuxfs-i2c.type.name": "I2C",
      "registry.button.name": "Press button",
      "registry.button.type.name": "DIGITAL_INPUT",
      "registry.button.description": "DIN-24",
      "registry.dout-22.name": "DOUT-22",
      "registry.dout-22.type.name": "DIGITAL_OUTPUT",
      "registry.dout-22.description": "DOUT-22",
      "registry.i2c-1@39.name": "PCF8574AT backed LCD@39",
      "registry.i2c-1@39.type.name": "I2C",
      "registry.i2c-1@39.description": "I2C-1.39"
    }
    ```

## Notes

* All info about the Pi4J project can be found on [pi4j.com](https://pi4j.com/).
* TODO: describe this Spring Boot Starter on the Pi4J website.

## Thanks to

This project was started by [Daniel Frey](https://github.com/dmfrey) and got further improved
by [DaShaun Carter](https://github.com/dashaun) and
[Frank Delporte](https://github.com/FDelporte) during a few
live-coding sessions. The Maven wrapper was added by [Dariusz Zbyrad](https://github.com/dariuszzbyrad).

1. [2023-05-24 - Setting up pi4j-springboot (DaShaun)](https://www.youtube.com/watch?v=RH80giE7FKI)
1. [2023-05-25 - Spring Boot Pi4J Starter :: Testing my PR (DaShaun)](https://www.youtube.com/watch?v=1bCyGvdmRvI)
2. [2024-06-11 - Pi4J Spring Boot Starter (DaShaun and Frank)](https://www.youtube.com/watch?v=I62IviQLNts)

More about Java on Raspberry Pi and embedded
in [this Foojay Podcast: Embedded Java, Part 2: Java on Raspberry Pi, ARM, Risc-V, from small computers to the cloud (#55)](https://www.youtube.com/watch?v=FoTyfWogspI)
