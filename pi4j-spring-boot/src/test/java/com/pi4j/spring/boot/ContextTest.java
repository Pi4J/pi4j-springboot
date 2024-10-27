package com.pi4j.spring.boot;

import com.pi4j.boardinfo.definition.BoardModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ContextAutoConfigurationTests.class)
class ContextTest {

    @Autowired
    ContextConfiguration contextConfiguration;

    @Test
    void contextLoads() {
        assertAll(
                () -> assertNotNull(contextConfiguration.context()),
                () -> assertNotNull(contextConfiguration.context().boardInfo().getBoardModel())
        );
    }

    @Test
    void boardUnknownIfNotRunningOnPi() {
        String osName = System.getProperty("os.name").toLowerCase();
        String osArch = System.getProperty("os.arch").toLowerCase();
        if (osName.contains("linux") && osArch.contains("arm")) {
            assertNotEquals(BoardModel.UNKNOWN, contextConfiguration.context().boardInfo().getBoardModel());
        } else {
            assertEquals(BoardModel.UNKNOWN, contextConfiguration.context().boardInfo().getBoardModel());
        }
    }

}
