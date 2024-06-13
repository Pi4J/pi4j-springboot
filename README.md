# Pi4J Spring Boot Starter

![GitHub Actions build state](https://github.com/Pi4J/pi4j-springboot/actions/workflows/build.yml/badge.svg) [![License](https://img.shields.io/github/license/pi4j/pi4j-v2)](http://www.apache.org/licenses/LICENSE-2.0)

[![Chat on Slack](https://img.shields.io/badge/Chat-on%20Slack-blue)](https://join.slack.com/t/pi4j/shared_invite/zt-1ttqt8wgj-E6t69qaLrNuCMPLiYnBCsg)
[![Site](https://img.shields.io/badge/Website-pi4j.com-green)](https://pi4j.com)
[![Twitter Follow](https://img.shields.io/twitter/follow/pi4j?label=Pi4J&style=social)](https://twitter.com/pi4j)

---

This project provides a Spring Boot Starter to use the Pi4J V2 library in your Spring Boot project.

## About this project

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

## Running the sample application on a Raspberry Pi

* Start from Raspberry Pi OS or [Pi4J CrowPi OS 0.2.0](https://pi4j.com/getting-started/crowpi/crowpi-os/).
* Check the Java version, should be 17 or newer.

```shell
$ java -version
openjdk version "17.0.4" 2022-07-19
OpenJDK Runtime Environment (build 17.0.4+8-Debian-1deb11u1)
OpenJDK 64-Bit Server VM (build 17.0.4+8-Debian-1deb11u1, mixed mode, sharing)
```

* If Java is not installed yet, use SDKMAN.

```shell
$ sudo apt install zip
$ curl -s "https://get.sdkman.io" | bash 
# Open new terminal
$ sdk install java 17.0.7-zulu
```

* Get this project from GitHub and package it.

```shell
$ git clone https://github.com/Pi4J/pi4j-springboot.git
$ cd pi4j-springboot
$ mvn package
```

* Run it as sudo to make sure Pi4J starts correctly.

```shell
$ sudo java -jar pi4j-spring-boot-starter-sample-app/target/pi4j-spring-boot-starter-sample-app-0.0.1.ea.jar 

# Or when installed with SDKMAN
$ sudo ~/.sdkman/candidates/java/19.0.2-zulu/bin/java -jar pi4j-spring-boot-starter-sample-app/target/pi4j-spring-boot-starter-sample-app-0.0.1.ea.jar
```

* Check the output of the application:

```shell

```

* Open browser with http://{RASPBERRY_PI_IP_ADDRESS}:8080/actuator/info
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

This project was started by Daniel Frey and got further improved by DaShaun Carter during a few live-coding sessions:

1. TODO link to first Twitch
2. [Working on Pi4J Spring Boot](https://www.twitch.tv/videos/1828406758)
3. [Spring Boot Pi4J Starter :: Testing my PR](https://www.twitch.tv/videos/1829189803)