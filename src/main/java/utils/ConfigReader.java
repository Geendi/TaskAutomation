package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader
 * -------------
 * Loads properties from src/main/resources/config.properties
 * and provides a helper to retrieve property values.
 */
public class ConfigReader {
    private static final Properties properties = new Properties();

    public static void loadProperties() {
        try (FileInputStream input = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
