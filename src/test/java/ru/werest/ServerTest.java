package ru.werest;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static ru.werest.Constants.*;

class ServerTest {

    @Test
    void testServerProps() {
        int expectedPort = 8081;
        int port;

        String pathLog;
        String exPathLog = "serverLog.log";

        try (FileReader fileReader = new FileReader(PROPERTIES_FILE)) {
            Properties properties = new Properties();
            properties.load(fileReader);
            port = Integer.parseInt(properties.getProperty(PORT));
            pathLog = properties.getProperty(SERVER_LOG);
        } catch (IOException e) {
            System.out.println(ERROR_LOAD_PROPERTIES_FILE);
            return;
        }
        assertEquals(expectedPort, port);
        assertEquals(exPathLog, pathLog);
    }

}