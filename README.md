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
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.1.0)

2023-05-26T15:11:12.614+02:00  INFO 2148 --- [           main] c.p.spring.boot.sample.app.Application   : Starting Application v0.0.1.ea using Java 17.0.6 with PID 2148 (/home/pi/pi4j-springboot/pi4j-spring-boot-starter-sample-app/target/pi4j-spring-boot-starter-sample-app-0.0.1.ea.jar started by root in /home/pi/pi4j-springboot)
2023-05-26T15:11:12.630+02:00  INFO 2148 --- [           main] c.p.spring.boot.sample.app.Application   : No active profile set, falling back to 1 default profile: "default"
2023-05-26T15:11:20.607+02:00  INFO 2148 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2023-05-26T15:11:20.665+02:00  INFO 2148 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2023-05-26T15:11:20.668+02:00  INFO 2148 --- [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.8]
2023-05-26T15:11:21.078+02:00  INFO 2148 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2023-05-26T15:11:21.090+02:00  INFO 2148 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 7946 ms
2023-05-26T15:11:23.861+02:00  INFO 2148 --- [           main] com.pi4j.Pi4J                            : New auto context
2023-05-26T15:11:23.862+02:00  INFO 2148 --- [           main] com.pi4j.Pi4J                            : New context builder
2023-05-26T15:11:24.486+02:00  INFO 2148 --- [           main] c.p.p.impl.DefaultRuntimePlatforms       : adding platform to managed platform map [id=raspberrypi; name=RaspberryPi Platform; priority=5; class=com.pi4j.plugin.raspberrypi.platform.RaspberryPiPlatform]
2023-05-26T15:11:26.134+02:00  INFO 2148 --- [           main] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 14 endpoint(s) beneath base path '/actuator'
2023-05-26T15:11:26.723+02:00  INFO 2148 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-05-26T15:11:26.940+02:00  INFO 2148 --- [           main] c.p.spring.boot.sample.app.Application   : Started Application in 16.876 seconds (process running for 19.337)
2023-05-26T15:11:39.543+02:00  INFO 2148 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2023-05-26T15:11:39.544+02:00  INFO 2148 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2023-05-26T15:11:39.550+02:00  INFO 2148 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 4 ms
```

* Open browser with http://{RASPBERRY_PI_IP_ADDRESS}:8080/actuator/info
* Actual output on a Raspberry Pi 4 (but truncated the pins-section):

```json
{
  "os": {
    "name": "Linux",
    "version": "6.1.21-v8+",
    "architecture": "aarch64"
  },
  "board": {
    "label": "Raspberry Pi 4 Model B",
    "boardType": {},
    "boardCodes": [
      "a03111",
      "b03111",
      "b03112",
      "b03114",
      "b03115",
      "c03111",
      "c03112",
      "c03114",
      "c03115",
      "d03114",
      "d03115"
    ],
    "model": {
      "label": "Model B",
      "description": "With ethernet connector"
    },
    "headerVersion": {
      "label": "Type 3",
      "description": "Used on Model A+, B+, Pi Zero, Pi Zero W, Pi2B, Pi3B, Pi4B",
      "headerPins": [
        {
          "label": "40pin header",
          "pins": [
            {
              "pinNumber": 1,
              "pinType": {
                "label": "Power",
                "color": 10027008
              },
              "name": "3.3 VDC",
              "remark": ""
            },
            ...
          ]
        }
      ]
    },
    "releaseDate": "2019-06-24",
    "soc": {
      "instructionSet": {
        "label": "ARMv8"
      }
    },
    "cpu": {
      "label": "Cortex-A72"
    },
    "numberOfCpu": 4,
    "versionsProcessorSpeedInMhz": [
      1500,
      1800
    ],
    "versionsMemoryInKb": [
      1048576,
      2097152,
      4194304,
      8388608
    ],
    "remarks": [],
    "name": "MODEL_4_B",
    "versionsMemoryInMb": [
      1024,
      2048,
      4096,
      8192
    ],
    "versionsMemoryInGb": [
      1,
      2,
      4,
      8
    ]
  },
  "java": {
    "version": "17.0.6",
    "runtime": "17.0.6+10-Debian-1deb11u1",
    "vendor": "Debian"
  },
  "pi4jRegistry": {}
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