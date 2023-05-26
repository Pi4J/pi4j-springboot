package com.pi4j.spring.boot;

import com.pi4j.boardinfo.model.DetectedBoard;
import com.pi4j.boardinfo.util.BoardModelDetection;
import com.pi4j.context.Context;
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
        builder.withDetail("pi4jPlatforms", context.platforms());
        builder.withDetail("pi4jDefaultPlatform", context.platform());
        builder.withDetail("pi4jProviders", context.providers());
        builder.withDetail("pi4jRegistry", context.registry());
    }
}
