package com.pi4j.spring.boot;

import com.pi4j.boardinfo.model.DetectedBoard;
import com.pi4j.boardinfo.util.BoardModelDetection;
import com.pi4j.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(InfoContributor.class)
@ConditionalOnBean(Context.class)
public class Pi4jActuatorConfiguration implements InfoContributor {

    private final Context context;
    private final DetectedBoard detectedBoard;
    private final Logger logger = LoggerFactory.getLogger(Pi4jActuatorConfiguration.class);

    /**
     * Here the Pi4J Context is initialized
     *
     * @param context {@link Context}
     */
    public Pi4jActuatorConfiguration(Context context) {
        this.context = context;
        this.detectedBoard = BoardModelDetection.getDetectedBoard();
    }

    @Override
    public void contribute(Builder builder) {
        builder.withDetail("os", detectedBoard.getOperatingSystem());
        builder.withDetail("board", detectedBoard.getBoardModel());
        builder.withDetail("java", detectedBoard.getJavaInfo());
        try {
            // TODO https://github.com/Pi4J/pi4j-springboot/issues/11
            //builder.withDetail("pi4jPlatforms", context.platforms().all());
        } catch (Exception ex) {
            logger.error("Could not return the Pi4J Platforms: {}", ex.getMessage());
        }
        try {
            // TODO https://github.com/Pi4J/pi4j-springboot/issues/11
            //builder.withDetail("pi4jDefaultPlatform", context.platform());
        } catch (Exception ex) {
            logger.error("Could not return the Pi4J Default Platform: {}", ex.getMessage());
        }
        try {
            // TODO https://github.com/Pi4J/pi4j-springboot/issues/11
            //builder.withDetail("pi4jProviders", context.providers().all());
        } catch (Exception ex) {
            logger.error("Could not return the Pi4J Providers: {}", ex.getMessage());
        }
        try {
            builder.withDetail("pi4jRegistry", context.registry().all());
        } catch (Exception ex) {
            logger.error("Could not return the Pi4J Registry: {}", ex.getMessage());
        }
    }
}
