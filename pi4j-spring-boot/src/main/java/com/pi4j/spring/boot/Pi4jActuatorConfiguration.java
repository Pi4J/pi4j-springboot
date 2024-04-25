package com.pi4j.spring.boot;

import com.pi4j.boardinfo.model.BoardInfo;
import com.pi4j.boardinfo.util.BoardInfoHelper;
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
    private final BoardInfo boardInfo;
    private final Logger logger = LoggerFactory.getLogger(Pi4jActuatorConfiguration.class);

    /**
     * Here the Pi4J Context is initialized
     *
     * @param context {@link Context}
     */
    public Pi4jActuatorConfiguration(Context context) {
        this.context = context;
        this.boardInfo = BoardInfoHelper.current();
    }

    @Override
    public void contribute(Builder builder) {
        var os = boardInfo.getOperatingSystem();
        builder.withDetail("os.name", os.getName());
        builder.withDetail("os.architecture", os.getArchitecture());
        builder.withDetail("os.version", os.getVersion());
        builder.withDetail("os.architecture", os.getArchitecture());
        var boardModel = boardInfo.getBoardModel();
        builder.withDetail("board.name", boardModel.getName());
        builder.withDetail("board.description", boardModel.getLabel());
        builder.withDetail("board.model.label", boardModel.getModel().getLabel());
        builder.withDetail("board.cpu.label", boardModel.getCpu().getLabel());
        builder.withDetail("board.soc", boardModel.getSoc().name());
        var java = boardInfo.getJavaInfo();
        builder.withDetail("java.version", java.getVersion());
        builder.withDetail("java.runtime", java.getRuntime());
        builder.withDetail("java.vendor", java.getVendor());
        builder.withDetail("java.vendor.version", java.getVendorVersion());
        var boardReading = BoardInfoHelper.getBoardReading();
        builder.withDetail("reading.volt.value", boardReading.getVoltValue());
        builder.withDetail("reading.temperature.celsius", boardReading.getTemperatureInCelsius());
        builder.withDetail("reading.temperature.fahrenheit", boardReading.getTemperatureInFahrenheit());
        builder.withDetail("reading.uptime", boardReading.getUptimeInfo());

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
