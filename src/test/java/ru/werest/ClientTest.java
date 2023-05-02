package ru.werest;

import org.junit.jupiter.api.Test;
import ru.werest.service.PropertiesService;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static ru.werest.Constants.*;

class ClientTest {

    @Test
    void testClientProps() {
        int exPort = 8081;
        String exHost = "localhost";
        String exPathClientLog = "clientLog.log";


        String pathProperties = "src/main/resources/settings.txt";

        PropertiesService propertiesService = new PropertiesService();
        Properties properties = propertiesService.get(pathProperties);

        int port = Integer.parseInt(properties.getProperty(PORT));
        String host = properties.getProperty(HOST);

        String pathClientLog = properties.getProperty(CLIENT_LOG);

        assertEquals(exPort, port);
        assertEquals(exHost, host);
        assertEquals(exPathClientLog, pathClientLog);
    }
}