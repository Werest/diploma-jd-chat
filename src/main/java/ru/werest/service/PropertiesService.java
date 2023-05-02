package ru.werest.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static ru.werest.Constants.ERROR_LOAD_PROPERTIES_FILE;
import static ru.werest.Constants.PROPERTIES_FILE;

public class PropertiesService {
    public Properties get(String path) {
        //Properties
        try (FileReader fileReader = new FileReader(path.equals("") ? PROPERTIES_FILE : path)) {
            Properties properties = new Properties();
            properties.load(fileReader);
            return properties;
        } catch (IOException e) {
            System.out.println(ERROR_LOAD_PROPERTIES_FILE);
        }
        return null;
    }
}
